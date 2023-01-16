package org.example.coinbase.converter;

import org.example.coinbase.dto.ListAccountsDto;
import org.example.coinbase.model.BalanceModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;

public class BalanceConverter {
    public static LinkedHashMap<String, BalanceModel> balanceConverter(ListAccountsDto accountsDto) {
        LinkedHashMap<String, BalanceModel> output = new LinkedHashMap<>();

        for(ListAccountsDto.coinBalance balance : accountsDto.getData()) {
            output.put(
                    balance.getCurrency(),
                    new BalanceModel(
                            balance.getCurrency(),
                            new BigDecimal(balance.getBalance().getAmount()).setScale(6, RoundingMode.DOWN)
                    )
            );
        }

        return output;
    }
}
