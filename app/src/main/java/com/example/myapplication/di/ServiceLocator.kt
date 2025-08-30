package com.example.myapplication.di

import com.example.myapplication.data.api.JikanApi
import com.example.myapplication.data.repository.AnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideJikanApi(retrofit: Retrofit): JikanApi {
        return retrofit.create(JikanApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAnimeRepository(api: JikanApi): AnimeRepository {
        return AnimeRepository(api)
    }
}