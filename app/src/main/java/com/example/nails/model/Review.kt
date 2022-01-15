package com.example.nails.model

import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val userName: String,
    val comments_text: String,
)