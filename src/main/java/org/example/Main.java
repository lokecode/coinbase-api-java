package org.example;

import lombok.SneakyThrows;
import okhttp3.*;
import org.example.coinbase.CoinbaseApi;
import org.example.exchanges.coinbase.dto.GetAllTransfersRequestDto;
import org.example.exchanges.coinbase.dto.GetProductTickerRequestDto;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {

        //1669041379
        //1669041784559

        System.out.println(CoinbaseApi.GetAllAccounts());
        System.out.println(CoinbaseApi.GetFees());
        System.out.println(CoinbaseApi.GetAllTradingPairs());
        System.out.println(CoinbaseApi.GetProductTicker(new GetProductTickerRequestDto("AUCTION-EUR")));
    }


}

