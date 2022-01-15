package com.example.nails.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.serialization.Serializable

@Serializable
@Entity(primaryKeys = ["imageServiceURL","nameService","priceCount","placeCount"])
data class Service(
    @ColumnInfo val imageServiceURL : String,
    @ColumnInfo val nameService : String,
    @ColumnInfo val priceCount : String,
    @ColumnInfo val placeCount : String,
)