package org.example.coinbase.converter;

import org.example.coinbase.dto.CreateOrderResponseDto;
import org.example.coinbase.model.OrderResponse;

public class OrderReponseConverter {
    public static OrderResponse orderReponseConverter(CreateOrderResponseDto responseDto) {
        return new OrderResponse(
                responseDto.getOrder_id(),
                responseDto.getSuccess_response().getSide(),
                (responseDto.getOrder_configuration().getMarket_market_ioc().getBase_size() != null) ?
                        responseDto.getOrder_configuration().getMarket_market_ioc().getBase_size() :
                        responseDto.getOrder_configuration().getMarket_market_ioc().getQuote_size()
        ); 
    }
}
