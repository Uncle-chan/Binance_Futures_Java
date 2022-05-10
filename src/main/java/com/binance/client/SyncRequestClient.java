package com.binance.client;

import com.alibaba.fastjson.JSONObject;
import com.binance.client.impl.BinanceApiInternalFactory;
import com.binance.client.model.ResponseResult;
import com.binance.client.model.enums.*;
import com.binance.client.model.market.*;
import com.binance.client.model.trade.*;

import java.util.List;

/**
 * Synchronous request interface, invoking Binance RestAPI via synchronous
 * method.<br>
 * All methods in this interface will be blocked until the RestAPI response.
 * <p>
 * If the invoking failed or timeout, the
 * {@link com.binance.client.exception.BinanceApiException} will be thrown.
 */
public interface SyncRequestClient {

    /**
     * Create the synchronous client. All interfaces defined in synchronous client
     * are implemented by synchronous mode.
     *
     * @return The instance of synchronous client.
     */
    static SyncRequestClient create() {
        return create("", "", new RequestOptions());
    }

    /**
     * Create the synchronous client. All interfaces defined in synchronous client
     * are implemented by synchronous mode.
     *
     * @param apiKey    The public key applied from binance.
     * @param secretKey The private key applied from binance.
     * @return The instance of synchronous client.
     */
    static SyncRequestClient create(String apiKey, String secretKey) {
        return BinanceApiInternalFactory.getInstance().createSyncRequestClient(apiKey, secretKey, new RequestOptions());
    }

    /**
     * Create the synchronous client. All interfaces defined in synchronous client
     * are implemented by synchronous mode.
     *
     * @param apiKey    The public key applied from binance.
     * @param secretKey The private key applied from binance.
     * @param options   The request option.
     * @return The instance of synchronous client.
     */
    static SyncRequestClient create(String apiKey, String secretKey, RequestOptions options) {
        return BinanceApiInternalFactory.getInstance().createSyncRequestClient(apiKey, secretKey, options);
    }



    ExchangeInformation getExchangeInformation();


    OrderBook getOrderBook(String symbol, Integer limit);


    List<Trade> getRecentTrades(String symbol, Integer limit);


    List<Trade> getOldTrades(String symbol, Integer limit, Long fromId);


    List<AggregateTrade> getAggregateTrades(String symbol, Long fromId, Long startTime, Long endTime, Integer limit);


    List<Candlestick> getCandlestick(String symbol, CandlestickInterval interval, Long startTime, Long endTime, Integer limit);


    List<MarkPrice> getMarkPrice(String symbol);


    List<FundingRate> getFundingRate(String symbol, Long startTime, Long endTime, Integer limit);


    List<PriceChangeTicker> get24hrTickerPriceChange(String symbol);


    List<SymbolPrice> getSymbolPriceTicker(String symbol);


    List<SymbolOrderBook> getSymbolOrderBookTicker(String symbol);


    List<LiquidationOrder> getLiquidationOrders(String symbol, Long startTime, Long endTime, Integer limit);


    List<Object> postBatchOrders(String batchOrders);
    

    Order postOrder(String symbol, OrderSide side, PositionSide positionSide, OrderType orderType,
            TimeInForce timeInForce, String quantity, String price, String reduceOnly,
            String newClientOrderId, String stopPrice, WorkingType workingType, NewOrderRespType newOrderRespType);


    Order cancelOrder(String symbol, Long orderId, String origClientOrderId);


    ResponseResult cancelAllOpenOrder(String symbol);


    List<Object> batchCancelOrders(String symbol, String orderIdList, String origClientOrderIdList);


    ResponseResult changePositionSide(boolean dual);


    ResponseResult changeMarginType(String symbolName, String marginType);


    JSONObject addIsolatedPositionMargin(String symbolName, int type, String amount, PositionSide positionSide);


    List<WalletDeltaLog> getPositionMarginHistory(String symbolName, int type, long startTime, long endTime, int limit);


    JSONObject getPositionSide();

    Order getOrder(String symbol, Long orderId, String origClientOrderId);


    List<Order> getOpenOrders(String symbol);


    List<Order> getAllOrders(String symbol, Long orderId, Long startTime, Long endTime, Integer limit);
  
    /**
     * Get account balances.
     *
     * @return Balances.
     */
    List<AccountBalance> getBalance();
  
    /**
     * Get current account information.
     *
     * @return Current account information.
     */
    AccountInformation getAccountInformation();
  

    Leverage changeInitialLeverage(String symbol, Integer leverage);

    /**
     * Get position.
     *
     * @return Position.
     */
    List<PositionRisk> getPositionRisk();


    List<MyTrade> getAccountTrades(String symbol, Long startTime, Long endTime, Long fromId, Integer limit);


    List<Income> getIncomeHistory(String symbol, IncomeType incomeType, Long startTime, Long endTime, Integer limit);

    /**
     * Start user data stream.
     *
     * @return listenKey.
     */
    String startUserDataStream();


    String keepUserDataStream(String listenKey);

    String closeUserDataStream(String listenKey);


    List<OpenInterestStat> getOpenInterestStat(String symbol, PeriodType period, Long startTime, Long endTime, Integer limit);


    List<CommonLongShortRatio> getTopTraderAccountRatio(String symbol, PeriodType period, Long startTime, Long endTime, Integer limit);


    List<CommonLongShortRatio> getTopTraderPositionRatio(String symbol, PeriodType period, Long startTime, Long endTime, Integer limit);


    List<CommonLongShortRatio> getGlobalAccountRatio(String symbol, PeriodType period, Long startTime, Long endTime, Integer limit);


    List<TakerLongShortStat> getTakerLongShortRatio(String symbol, PeriodType period, Long startTime, Long endTime, Integer limit);

}