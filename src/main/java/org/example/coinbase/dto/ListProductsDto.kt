package org.example.coinbase.dto

data class ListProductsDto(
    val products: List<GetProductDto>,
    val num_products: Int
)
