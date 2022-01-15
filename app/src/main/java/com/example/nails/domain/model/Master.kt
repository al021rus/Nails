package com.example.nails.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Master(
    val imageMasterURL: String,
    val nameMaster: String,
    val positionName : String,
    val ExpTime: String,
    val workTime: String,
)