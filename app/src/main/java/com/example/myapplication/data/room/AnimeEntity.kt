package com.example.myapplication.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_table")
data class AnimeEntity(
    @PrimaryKey val malId : Int,
    val title: String,
    val imageUrl: String?,
    val score: Float?,
    val episodes: Int?,
    val status: String?,
    val season: String?,
    val year: Int?,
    val userScore : String = "0",
    val watchStatus : String = "Plan to Watch"
)

