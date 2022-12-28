package org.example.exchanges.coinbase.dto

data class WithdrawToAddressResponseDto(
        val id: String,
        val amount: String,
        val currency: String,
        val payout_at: String,
        val fee: String,
        val subtotal: String
)
