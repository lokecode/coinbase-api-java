package org.example.coinbase.model

import java.math.BigDecimal

data class OrderResponse(
    val id: String,
    val side: String,
    val amount: BigDecimal
)
