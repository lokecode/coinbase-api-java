package org.example.coinbase.dto

data class GetCryptocurrenciesDto(
        val data: List<Currency>
) {
    data class Currency (
            val code: String,
            val name: String,
            val color: String,
            val sort_index: Long,
            val exponent: Long,
            val type: String,
            val address_regex: String,
            val asset_id: String,
            val destination_tag_name: String? = null,
            val destination_tag_regex: String? = null
    )
}
