package com.example.nails.model

import androidx.annotation.DrawableRes

data class Master(
    @DrawableRes val imageMaster: Int,
    val nameMaster: String,
    val positionName : String,
    val ExpTime: String,
    val workTime: String,
)