package org.example;

import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import org.example.coinbase.CoinbaseApi;
import org.example.coinbase.dto.*;
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
                CoinbaseRepository.BuyOrder("SOL-USDT", new BigDecimal("0.488"))
        );
    }
}
//{"success":true,"failure_reason":"UNKNOWN_FAILURE_REASON","order_id":"dd9d8d87-e6ff-4952-b9a7-0792e6f08911","success_response":{"order_id":"dd9d8d87-e6ff-4952-b9a7-0792e6f08911","product_id":"SOL-USDT","side":"BUY","client_order_id":"1676327097468"},"order_configuration":{"market_market_ioc":{"quote_size":"10"}}}
//{"success":true,"failure_reason":"UNKNOWN_FAILURE_REASON","order_id":"5b8995c2-2f11-40e3-b21c-4f3275fce181","success_response":{"order_id":"5b8995c2-2f11-40e3-b21c-4f3275fce181","product_id":"SOL-USDT","side":"SELL","client_order_id":"1676327206356"},"order_configuration":{"market_market_ioc":{"base_size":"0.49"}}}