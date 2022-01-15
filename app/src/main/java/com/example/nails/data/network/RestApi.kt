package com.example.nails.data.network

import com.example.nails.domain.model.Master
import com.example.nails.domain.model.Review
import com.example.nails.domain.model.Service
import retrofit2.http.GET

interface RestApi {
    @GET("masters")
    suspend fun loadMasters(): List<Master>
    @GET("services")
    suspend fun loadServices(): List<Service>
    @GET("reviews")
    suspend fun loadReviews(): List<Review>
}