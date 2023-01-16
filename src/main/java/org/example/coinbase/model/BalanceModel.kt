package org.example.coinbase.model

import java.math.BigDecimal

data class BalanceModel(
    val symbol: String,
    val amount: BigDecimal
)
