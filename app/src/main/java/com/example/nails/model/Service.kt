package com.example.nails.model

import androidx.annotation.DrawableRes

data class Service(
    @DrawableRes val imageService:Int,
    val nameService : String,
    val priceCount : String,
    val placeCount : String,
)