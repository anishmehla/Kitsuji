package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.room.AnimeDao
import com.example.myapplication.data.room.AnimeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAnimeDatabase(@ApplicationContext context : Context) : AnimeDatabase {
        return Room.databaseBuilder(
            context,
            AnimeDatabase::class.java,
            "anime_db"
        )
            .fallbackToDestructiveMigration(dropAllTables = false)
            .build()
    }

    @Provides
    fun provideAnimeDao(database: AnimeDatabase) : AnimeDao = database.animeDao()


}