package com.example.myapplication.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: AnimeEntity)

    @Delete
    suspend fun deleteAnime(anime: AnimeEntity)

    @Query("SELECT * FROM anime_table")
    fun getAllAnime(): Flow<List<AnimeEntity>>

    @Query("SELECT * FROM anime_table WHERE title LIKE '%' || :query || '%' ORDER by title ASC ")
    fun searchList(query : String) : Flow<List<AnimeEntity>>

    @Query("SELECT * FROM anime_table WHERE watchStatus = :watchStatus ORDER by title ASC")
    fun filterList(watchStatus:String) : Flow<List<AnimeEntity>>

    @Query("SELECT * FROM anime_table WHERE title LIKE '%' || :query || '%' AND watchStatus = :watchStatus")
    fun searchAndFilterList(watchStatus: String,query: String) : Flow<List<AnimeEntity>>
}