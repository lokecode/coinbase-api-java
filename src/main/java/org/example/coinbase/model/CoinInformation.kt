package org.example.coinbase.model

import java.math.BigDecimal

data class CoinInformationModel(
    val symbol: String,
    val baseSymbol: String,
    val quoteSymbol: String,
    val minQty: BigDecimal
)
