package org.example.coinbase.converter;

import org.example.coinbase.dto.GetProductDto;
import org.example.coinbase.model.TickerModel;

import java.math.BigDecimal;

public class TickerModelConverter {
    public static TickerModel tickerModelConverter(GetProductDto productDto) {
        return new TickerModel(
                productDto.getProduct_id(),
                productDto.getBase_name().toUpperCase().replace(" ",""),
                new BigDecimal(productDto.getPrice()),
                new BigDecimal(productDto.getVolume_24h()),
                new BigDecimal(productDto.getBase_increment())
        );
    }
}
