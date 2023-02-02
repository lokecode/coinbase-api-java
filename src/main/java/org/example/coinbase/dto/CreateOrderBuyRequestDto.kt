package org.example.coinbase.dto

import org.example.domain.enums.OrderSide
import java.math.BigDecimal

data class CreateOrderBuyRequestDto(
    val side: String,
    val order_configuration: market_market_ioc,
    val client_order_id: String,
    val product_id: String
) {
    data class market_market_ioc(
        val quote_size: BigDecimal
    )
}
