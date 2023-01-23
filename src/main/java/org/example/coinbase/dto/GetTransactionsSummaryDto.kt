package org.example.coinbase.dto

data class GetTransactionsSummaryDto(
    val total_volume: Long,
    val total_fees: Long,
    val fee_tier: FeeTier,
    val margin_rate: String,
    val goods_and_services_tax: String,
    val advanced_trade_only_volume: Long,
    val advanced_trade_only_fees: Long,
    val coinbase_pro_volume: Long,
    val coinbase_pro_fees: Long
) {
    data class FeeTier (
        val pricing_tier: String,
        val usd_from: String,
        val usd_to: String,
        val taker_fee_rate: Double,
        val maker_fee_rate: Double
    )
}
