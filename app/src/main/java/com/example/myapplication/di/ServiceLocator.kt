package com.example.myapplication.di

import com.example.myapplication.data.api.JikanApi
import com.example.myapplication.data.repository.AnimeRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceLocator {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.jikan.moe/v4/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(JikanApi::class.java)

    val animeRepository = AnimeRepository(api)

}