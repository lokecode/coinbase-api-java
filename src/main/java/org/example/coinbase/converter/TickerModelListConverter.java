package org.example.coinbase.converter;

import org.example.coinbase.dto.GetProductDto;
import org.example.coinbase.dto.ListProductsDto;
import org.example.coinbase.model.TickerModel;

import java.util.LinkedHashMap;

public class TickerModelListConverter {
    public static LinkedHashMap<String, TickerModel> tickerModelListConverter(ListProductsDto productsDto) {
        LinkedHashMap<String, TickerModel> output = new LinkedHashMap<>();

        for(GetProductDto product : productsDto.getProducts()) {
            output.put(
                    product.getProduct_id(),
                    TickerModelConverter.tickerModelConverter(product)
            );
        }

        return output;
    }
}
