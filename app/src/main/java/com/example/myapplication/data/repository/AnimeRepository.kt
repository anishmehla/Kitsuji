package com.example.myapplication.data.repository

import com.example.myapplication.data.api.JikanApi
import com.example.myapplication.data.model.AnimeDetailResponse
import com.example.myapplication.data.model.AnimeEntry
import com.example.myapplication.data.model.AnimeSearchResponse
import javax.inject.Inject

class AnimeRepository @Inject constructor (private val api: JikanApi) {
    suspend fun getRecommendations(): List<AnimeEntry> {
        val response = api.getRecommendation()
        return response.data.flatMap { it.entry }
    }

    suspend fun getAnimeDetail(animeId: Int): AnimeDetailResponse {
        return api.getAnimeDetail(animeId)
    }

    suspend fun searchAnime(query :String) : AnimeSearchResponse {
        return api.searchAnime(query=query)
    }

}