package org.example.coinbase.dto

data class ListAccountsDto(
    //pagination
    val data: List<coinBalance>
) {
    data class coinBalance(
        val id: String,
        val name: String,
        val primary: Boolean,
        val type: String,
        val currency: String,
        val balance: Balance,
        val created_at: String,
        val updated_at: String,
        val resource: String,
        val resource_path: String,
        val allow_deposits: Boolean,
        val allow_withdrawals: Boolean,
        val native_balance: Balance,
        val rewards_apy: Double,
        val rewards: Rewards
    ) {
        data class Balance (
            val amount: String,
            val currency: String
        )

        data class Rewards (
            val apy: Double,
            val formatted_apy: String,
            val label: String
        )
    }
}
