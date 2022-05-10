package com.binance.client.impl;

import com.binance.client.ProxyOptions;
import com.binance.client.exception.BinanceApiException;
import com.binance.client.impl.utils.JsonWrapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

abstract class RestApiInvoker {

    private static final Logger log = LoggerFactory.getLogger(RestApiInvoker.class);

    private static OkHttpClient client;


    static void checkResponse(JsonWrapper json) {
        try {
            if (json.containKey("success")) {
                boolean success = json.getBoolean("success");
                if (!success) {
                    String err_code = json.getStringOrDefault("code", "");
                    String err_msg = json.getStringOrDefault("msg", "");
                    if ("".equals(err_code)) {
                        throw new BinanceApiException(BinanceApiException.EXEC_ERROR, "[Executing] " + err_msg);
                    } else {
                        throw new BinanceApiException(BinanceApiException.EXEC_ERROR,
                                "[Executing] " + err_code + ": " + err_msg);
                    }
                }
            } else if (json.containKey("code")) {

                int code = json.getInteger("code");
                if (code != 200) {
                    String message = json.getStringOrDefault("msg", "");
                    throw new BinanceApiException(BinanceApiException.EXEC_ERROR,
                            "[Executing] " + code + ": " + message);
                }
            }
        } catch (BinanceApiException e) {
            throw e;
        } catch (Exception e) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                    "[Invoking] Unexpected error: " + e.getMessage());
        }
    }

    static <T> T callSync(RestApiRequest<T> request) {
        try {
            initClient();
            String str;
            log.debug("Request URL " + request.request.url());
            Response response = client.newCall(request.request).execute();
            if (response != null && response.body() != null) {
                str = response.body().string();
                response.close();
            } else {
                throw new BinanceApiException(BinanceApiException.ENV_ERROR,
                        "[Invoking] Cannot get the response from server");
            }
            log.debug("Response =====> " + str);
            JsonWrapper jsonWrapper = JsonWrapper.parseFromString(str);
            checkResponse(jsonWrapper);
            return request.jsonParser.parseJson(jsonWrapper);
        } catch (BinanceApiException e) {
            throw e;
        } catch (Exception e) {
            throw new BinanceApiException(BinanceApiException.ENV_ERROR,
                    "[Invoking] Unexpected error: " + e.getMessage());
        }
    }

    static WebSocket createWebSocket(Request request, WebSocketListener listener) {
        initClient();
        return client.newWebSocket(request, listener);
    }

    private static void initClient() {
        if (client == null) {
            if (ProxyOptions.HTTP_POXY_IP == null) {
                client = new OkHttpClient();
                client.dispatcher().setMaxRequests(1024 * 5);
                client.dispatcher().setMaxRequestsPerHost(1024);
            } else {
                client = new OkHttpClient.Builder().proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ProxyOptions.HTTP_POXY_IP, ProxyOptions.HTTP_POXY_PORT))).readTimeout(5000, TimeUnit.MILLISECONDS).writeTimeout(5000, TimeUnit.MILLISECONDS).connectTimeout(5000, TimeUnit.MILLISECONDS).connectionPool(new ConnectionPool()).build();
                client.dispatcher().setMaxRequests(1024 * 5);
                client.dispatcher().setMaxRequestsPerHost(1024);
            }
        }
    }


}
