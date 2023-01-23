package org.example.coinbase.model

import java.math.BigDecimal
import java.util.Currency

data class TickerModel(
    val symbol: String,
    val currencyName: String,
    val price: BigDecimal,
    val volume: BigDecimal,
    val minQty: BigDecimal
)
