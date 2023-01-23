package org.example.coinbase.model

import org.example.domain.models.MainStateModel

data class AddressModel(
    val address: String,
    val blockchain: String,
    val tag: String
)