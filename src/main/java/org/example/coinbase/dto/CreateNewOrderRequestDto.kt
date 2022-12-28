package org.example.exchanges.coinbase.dto

import org.example.domain.enums.OrderSide
import org.example.domain.enums.OrderTypes

data class CreateNewOrderRequestDto(
        val type: OrderTypes,
        val side: OrderSide,
        val product_id: String,
        val size: Double
)
