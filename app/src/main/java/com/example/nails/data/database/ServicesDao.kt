package com.example.nails.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nails.domain.model.Service

@Dao
interface ServiceDao {
    @Query("SELECT * FROM service")
    suspend fun getAll(): List<Service>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(services: List<Service>)
}