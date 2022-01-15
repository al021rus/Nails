package com.example.nails.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nails.domain.model.Service

@Database(
    entities = [Service::class],
    version = 1
)
abstract class ServicesDatabase : RoomDatabase() {
    abstract fun ServiceDAO(): ServiceDao
}