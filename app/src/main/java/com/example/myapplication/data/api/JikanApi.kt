package com.example.myapplication.data.api

import com.example.myapplication.data.model.AnimeDetailData
import com.example.myapplication.data.model.AnimeDetailResponse
import com.example.myapplication.data.model.AnimeSearchResponse
import com.example.myapplication.data.model.RecommendationResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JikanApi {
    @GET("recommendations/anime")
    suspend fun getRecommendation() : RecommendationResponse

    @GET(value = "anime/{id}")
    suspend fun getAnimeDetail(@Path("id") animeId: Int): AnimeDetailResponse

    @GET(value = "anime")
    suspend fun searchAnime(@Query("q") query : String) : AnimeSearchResponse
}