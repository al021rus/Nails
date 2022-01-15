package com.example.nails.network

import com.example.nails.model.Master
import com.example.nails.model.Review
import com.example.nails.model.Service
import retrofit2.http.GET

interface RestApi {
    @GET("masters")
    suspend fun loadMasters(): List<Master>
    @GET("services")
    suspend fun loadServices(): List<Service>
    @GET("reviews")
    suspend fun loadReviews(): List<Review>
}