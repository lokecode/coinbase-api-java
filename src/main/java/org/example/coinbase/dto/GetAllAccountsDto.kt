package org.example.exchanges.coinbase.dto

data class GetAllAccountsDto(
    val id: String,
    val currency: String,
    val balance: Double,
    val hold: Double,
    val available: Double,
    val profile_id: String,
    val trading_enabled: Boolean,
)
