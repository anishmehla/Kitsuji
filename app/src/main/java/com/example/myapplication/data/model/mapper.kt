package com.example.myapplication.data.model

import androidx.room.PrimaryKey
import com.example.myapplication.data.room.AnimeEntity
import kotlin.String

fun AnimeDetailData.toEntity(userScore: String, watchStatus: String) : AnimeEntity {
    return _root_ide_package_.com.example.myapplication.data.room.AnimeEntity(
        malId = malId,
        title = title ?: titleEnglish ?: "No Title",
        imageUrl = images?.jpg?.largeImageUrl,
        score = score,
        episodes = episodes,
        status = status,
        season = season,
        year = year,
        userScore = userScore,
        watchStatus = watchStatus
    )
}