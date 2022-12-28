package org.example.exchanges.coinbase.dto

data class GetAllTransfersResponseDto(
        val description: String,
        val schema: Schema
) {
    data class Schema(
            val type: String,
            val items: Item
    ) {
        data class Item(
                val type: String,
                val example: Example,
                val required: List<String>
        ) {
            data class Example(
                    val id: String,
                    val type: String,
                    val created_at: Double,
                    val completed_at: Double,
                    val canceled_at: String,
                    val processed_at: String,
                    val user_nonce: String,
                    val amount: Double,
                    val details: Details
            ) {
                data class Details(
                        val coinbase_account_id: String,
                        val coinbase_transaction_id: String,
                        val coinbase_payment_method_id: String
                )
            }
        }
    }
}

