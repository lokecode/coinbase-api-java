package org.example.exchanges.coinbase.dto

data class GetProductTickerResponseDto(
        val trade_id: String,
        val price: String,
        val size: String,
        val time: String,
        val bid: String,
        val ask: String,
        val volume: String
)
