package org.example.exchanges.coinbase.dto

data class GetTradingPairDto(
    val id: String,
    val base_currency: String,
    val quote_currency: String,
    val quote_increment: Double,
    val base_increment: Double,
    val display_name: String,
    val min_market_funds: Double,
    val margin_enabled: Boolean,
    val post_only: Boolean,
    val limit_only: Boolean,
    val cancel_only: Boolean,
    val status: String,
    val status_message: String,
    val auction_mode: Boolean
)
