package org.example.exchanges.coinbase.dto

data class CreateNewOrderResponseDto(
        val id: String,
        val price: Double,
        val size: Double,
        val product_id: String,
        val profile_id: String,
        val side: String,
        val type: String,
        val time_in_force: String,
        val post_only: Boolean,
        val created_at: String,
        val fill_fees: Double,
        val filled_size: Double,
        val executed_value: Double,
        val status: String,
        val settled: Boolean,
)
