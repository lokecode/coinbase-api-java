package org.example;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.example.coinbase.CoinbaseApi;
import org.example.coinbase.dto.CreateOrderSellRequestDto;
import org.example.coinbase.dto.GetFeeEstimateRequestDto;
import org.example.coinbase.dto.GetProductDto;
import org.example.coinbase.dto.ListProductsDto;
import org.example.coinbase.model.*;
import org.example.coinbase.repository.CoinbaseRepository;
import org.example.domain.enums.OrderSide;
import org.example.domain.models.MainStateModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.example.viewmodel.CoinbaseViewModel.initialExchangeData;


public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        System.out.println(
                CoinbaseApi.test()
        );
        System.out.println(CoinbaseRepository.tickers());
    }
}

