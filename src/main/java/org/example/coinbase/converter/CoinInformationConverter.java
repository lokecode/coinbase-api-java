package org.example.coinbase.converter;

import org.example.coinbase.model.CoinInformationModel;
import org.example.exchanges.coinbase.converter.GetCryptocurrenciesDto;

import java.util.LinkedHashMap;
import java.util.Map;

public class CoinInformationConverter {
    public static Map<String, CoinInformationModel> coinInformationConverter(GetCryptocurrenciesDto response) {
        Map<String, CoinInformationModel> output = new LinkedHashMap<>();

        for (GetCryptocurrenciesDto.Currency coin : response.getData()) {
            output.put(
                    coin.getCode(),
                    new CoinInformationModel(
                            coin.getCode(),
                            coin.getName().toUpperCase().split(" ")[0],
                            coin.getAsset_id()
                    )
            );
        }

        return output;
    }
}
