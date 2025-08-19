package com.example.myapplication.data.repository

import com.example.myapplication.data.api.JikanApi
import com.example.myapplication.data.model.AnimeEntry

class AnimeRepository(private val api: JikanApi) {
    suspend fun getRecommendations(): List<AnimeEntry> {
        val response = api.getRecommendation()
        return response.data.flatMap { it.entry }
    }

}