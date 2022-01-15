package com.example.nails.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Service(
    val imageServiceURL : String,
    val nameService : String,
    val priceCount : String,
    val placeCount : String,
)