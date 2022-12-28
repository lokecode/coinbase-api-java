package org.example.exchanges.coinbase.dto

import org.example.domain.enums.WalletEnums

data class GetAllTransfersRequestDto(
        val profile_id: String,
        val before: String,
        val after: String,
        val limit: Int,
        val type: WalletEnums
)
