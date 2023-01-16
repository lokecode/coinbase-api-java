package org.example.coinbase.model

import java.math.BigDecimal

data class TickerModel(
    val symbol: String,
    val price: BigDecimal,
    val volume: BigDecimal
)
