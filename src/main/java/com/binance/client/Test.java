//package com.binance.client;
//
//import com.binance.client.exception.BinanceApiException;
//import com.binance.client.model.enums.CandlestickInterval;
//import com.binance.client.model.market.ExchangeInfoEntry;
//import com.binance.client.model.market.ExchangeInformation;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//public class Test {
//    public static void main(String[] args) {
//        RequestOptions options = new RequestOptions();
//        options.setHttpProxy("127.0.0.1", 7890);
//        SyncRequestClient syncRequestClient = SyncRequestClient.create("", "", options);
//        ExchangeInformation exchangeInformation = syncRequestClient.getExchangeInformation();
//
//        Map<String, Integer> receive = new HashMap<>();
//
//        SubscriptionOptions subscriptionOptions = new SubscriptionOptions();
//        subscriptionOptions.setHttpProxy("127.0.0.1", 7890);
//        SubscriptionClient client = SubscriptionClient.create(subscriptionOptions);
//        List<String> symbols = new ArrayList<>();
//
////        for (int i=0;i<20;i++){
////            symbols.add(exchangeInformation.getSymbols().get(i).getSymbol().toLowerCase());
////            receive.put(exchangeInformation.getSymbols().get(i).getSymbol(), 0);
////        }
//        for (ExchangeInfoEntry entry : exchangeInformation.getSymbols()) {
//
//            symbols.add(entry.getSymbol().toLowerCase());
//        }
//        client.subscribeCandlestickEvent(symbols, CandlestickInterval.ONE_MINUTE, ((event) -> {
//            receive.put(event.getSymbol(), 1);
//        }), exception -> System.out.println(exception.getMessage()));
//
//        Thread t = new Thread() {
//            @Override
//            public void run() {
//                while (true) {
//                    int size = 0;
//                    for (Map.Entry<String, Integer> entry : receive.entrySet()) {
//                        if (entry.getValue() == 1) {
//                            size++;
//                        }
//                    }
//                    System.out.println("总订阅数：" + exchangeInformation.getSymbols().size() + ",收到订阅数：" + size);
//                    try {
//                        Thread.sleep(1000 * 3);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        t.start();
//        new ThreadLocal<DateFormat>(){
//            @Override
//            protected DateFormat initialValue() {
//                return super.initialValue();
//            }
//        };
//
//        List<String> list=new ArrayList<>();
//        list.iterator();
//
////        }
//
////        client.subscribeCandlestickEvent("btcusdt", CandlestickInterval.ONE_MINUTE, ((event) -> {
////            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////            Date date = new Date(event.getStartTime());
////            Date edate = new Date(event.getCloseTime());
////            System.out.println(sdf.format(date) + ":" + event.getSymbol() + ":" + sdf.format(edate));
////        }), null);
////
////        client.subscribeCandlestickEvent("ethusdt", CandlestickInterval.ONE_MINUTE, ((event) -> {
////            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////            Date date = new Date(event.getStartTime());
////            Date edate = new Date(event.getCloseTime());
////            System.out.println(sdf.format(date) + ":" + event.getSymbol() + ":" + sdf.format(edate));
////        }), null);
//
//
//    }
//
//}
