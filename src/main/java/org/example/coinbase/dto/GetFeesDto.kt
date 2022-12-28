package org.example.exchanges.coinbase.dto

data class GetFeesDto(
        val maker_fee_rate: Double,
        val taker_fee_rate: Double,
        val usd_volume: Double
)
