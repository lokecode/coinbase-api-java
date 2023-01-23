package org.example;

import lombok.SneakyThrows;
import org.example.coinbase.CoinbaseApi;
import org.example.coinbase.dto.GetFeeEstimateRequestDto;
import org.example.coinbase.dto.GetProductDto;
import org.example.coinbase.dto.ListProductsDto;
import org.example.coinbase.model.*;
import org.example.coinbase.repository.CoinbaseRepository;
import org.example.domain.models.MainStateModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.example.viewmodel.CoinbaseViewModel.initialCoinData;
import static org.example.viewmodel.CoinbaseViewModel.initialExchangeData;


public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        MainStateModel.ExchangeData ss = initialExchangeData();

        ss.getCoinsData().values().stream().toList().forEach(System.out::println);
    }
}

