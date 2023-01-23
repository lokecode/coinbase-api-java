package org.example.coinbase.dto

data class GetFeeEstimateRequestDto(
    val currency: String,
    val crypto_address: String,
)
