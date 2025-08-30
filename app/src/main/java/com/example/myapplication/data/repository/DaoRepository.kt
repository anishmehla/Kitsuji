package com.example.myapplication.data.repository

import com.example.myapplication.data.room.AnimeDao
import com.example.myapplication.data.room.AnimeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DaoRepository @Inject constructor( private val animeDao: AnimeDao) {
    fun getAllAnime() : Flow<List<AnimeEntity>> = animeDao.getAllAnime()
    suspend fun insertAnime(anime: AnimeEntity) = animeDao.insertAnime(anime)
    suspend fun deleteAnime(anime: AnimeEntity) = animeDao.deleteAnime(anime)

    fun searchList(query : String) : Flow<List<AnimeEntity>> = animeDao.searchList(query)

    fun filterList(watchStatus:String) : Flow<List<AnimeEntity>> = animeDao.filterList(watchStatus)

    fun searchAndFilterList(watchStatus: String,query: String) : Flow<List<AnimeEntity>> =animeDao.searchAndFilterList(watchStatus,query)
}