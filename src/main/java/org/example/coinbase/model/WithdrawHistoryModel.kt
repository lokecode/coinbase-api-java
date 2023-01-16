package org.example.coinbase.model

import org.example.domain.enums.ExchangeStates

data class WithdrawHistoryModel(
    val id: String,
    val amount: Double,
    val state: ExchangeStates,
    val timestamp: Long
)
