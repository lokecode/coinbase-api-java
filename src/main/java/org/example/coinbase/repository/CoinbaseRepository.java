package org.example.coinbase.repository;


import org.example.coinbase.dto.*;
import org.example.coinbase.model.*;
import org.example.exchanges.coinbase.CoinbaseApi;
import org.example.exchanges.coinbase.converter.*;


import java.util.List;
import java.util.Map;

public class CoinbaseRepository {

    public static Map<String, BalanceModel> accounts() {
        ListAccountsDto response = CoinbaseApi.getListAccounts();
        if (response != null) {
            return BalanceConverter.balanceConverter(response);
        } else {
            return Map.of();
        }
    }
    public static AddressModel address(String symbol) {
        ListAddressesDto response = CoinbaseApi.getListAddresses(symbol);
        if (response != null) {
            return AddressConverter.addressConverter(response.getData());
        }
        return null;
    }

    public static AddressModel newAddress(String symbol) {
        CreateAddressDto response = CoinbaseApi.createAddress(symbol);
        if (response != null && response.getData() != null) {
            return AddressConverter.addressConverter(List.of(response.getData()));
        }
        return null;
    }

    public static ExchangeFeesModel exchangeFees() {
        GetTransactionsSummaryDto response = CoinbaseApi.getTransactionsSummary();
        if (response != null) {
            return ExchangeFeesConverter.exchangeFeesConverter(response);
        }
        return null;
    }

    public static WithdrawFeeModel withdrawFee(
            String symbol,
            String address
    ) {
        GetFeeEstimateResponseDto response = CoinbaseApi.getFeeEstimate(new GetFeeEstimateRequestDto(symbol,address));
        if (response != null) {
            return WithdrawFeeConverter.withdrawFeeConverter(response);
        }
        return null;
    }
    public static TickerModel ticker(String symbol) {
        GetProductDto response = CoinbaseApi.getProduct(symbol);
        if (response != null) {
            return TickerModelConverter.tickerModelConverter(response);
        }
        return null;
    }
    public static Map<String, TickerModel> tickers() {
        ListProductsDto response = CoinbaseApi.listProducts();
        if (response != null) {
            return TickerModelListConverter.tickerModelListConverter(response);
        }
        return Map.of();
    }

    public static Map<String, CoinInformationModel> coinInformation() {
        GetCryptocurrenciesDto response = CoinbaseApi.getCryptocurrencies();
        if (response != null) {
            return CoinInformationConverter.coinInformationConverter(response);
        }
        return Map.of();
    }
}
