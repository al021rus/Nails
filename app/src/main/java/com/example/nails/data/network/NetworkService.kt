package com.example.nails.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

object NetworkService {
    @ExperimentalSerializationApi
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://demo5687226.mockable.io/")
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .build()

    @ExperimentalSerializationApi
    private val restApi = retrofit.create(RestApi::class.java)
    @ExperimentalSerializationApi
    suspend fun loadMasters() = restApi.loadMasters()
    @ExperimentalSerializationApi
    suspend fun loadServices() = restApi.loadServices()
    @ExperimentalSerializationApi
    suspend fun loadReviews() = restApi.loadReviews()
}