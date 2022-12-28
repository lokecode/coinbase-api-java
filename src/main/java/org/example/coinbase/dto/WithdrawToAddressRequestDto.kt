package org.example.exchanges.coinbase.dto

data class WithdrawToAddressRequestDto(
        val profile_id: String,
        val amount: Double,
        val currency: String,
        val crypto_address: String,
        val destination_tag: String,
        val no_destination_tag: Boolean,
        val two_factor_code: String,
        val nonce: String,
        val fee: String,
        val network: String,
)
