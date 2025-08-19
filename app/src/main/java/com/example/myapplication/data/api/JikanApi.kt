package com.example.myapplication.data.api

import com.example.myapplication.data.model.RecommendationResponse
import retrofit2.http.GET

interface JikanApi {
    @GET("recommendations/anime")
    suspend fun getRecommendation() : RecommendationResponse
}