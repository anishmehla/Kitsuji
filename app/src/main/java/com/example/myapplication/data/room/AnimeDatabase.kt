package com.example.myapplication.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AnimeEntity::class], version = 3, exportSchema = false)
abstract class AnimeDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
}