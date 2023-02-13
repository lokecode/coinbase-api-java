package org.example.coinbase.dto

import java.math.BigDecimal

data class CreateOrderResponseDto(
    val success: Boolean,
    val failure_reason: String,
    val order_id: String,
    val success_response: SuccessResponse,
    val order_configuration: OrderConfiguration
) {
    data class OrderConfiguration (
        val market_market_ioc: MarketMarketIoc
    ) {
        data class MarketMarketIoc (
            val quote_size: BigDecimal?,
            val base_size: BigDecimal?
        )
    }

    data class SuccessResponse (
        val order_id: String,
        val product_id: String,
        val side: String,
        val client_order_id: String
    )
}
