package org.example.coinbase.repository;

import org.example.coinbase.CoinbaseApi;
import org.example.coinbase.converter.BalanceConverter;
import org.example.coinbase.dto.ListAccountsDto;
import org.example.coinbase.model.BalanceModel;

import java.util.LinkedHashMap;
import java.util.Map;

public class CoinbaseRepository {

    public static Map<String, BalanceModel> accounts() {
        ListAccountsDto response = CoinbaseApi.getListAccounts();
        if (response != null) {
            return BalanceConverter.balanceConverter(response);
        } else {
            return null;
        }
    }
    public static String addresses() {
        String response = CoinbaseApi.getListAddresses();
        if (response != null) {
            return response;
        } else {
            return null;
        }
    }

}
