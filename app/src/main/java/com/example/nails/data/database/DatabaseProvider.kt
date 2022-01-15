package com.example.nails.data.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var db: ServicesDatabase? = null

    fun provideDatabase(context: Context): ServicesDatabase {
        return db ?: Room.databaseBuilder(
            context.applicationContext,
            ServicesDatabase::class.java, "services_app_database"
        )
            .build()
            .also{ db = it }
    }
}