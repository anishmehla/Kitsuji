package com.example.myapplication.ui.theme.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.AnimeDetailResponse
import com.example.myapplication.data.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeDetailViewModel @Inject constructor(private val repository: AnimeRepository) : ViewModel(){
    private val _animeDetail = MutableStateFlow<AnimeDetailResponse?>(null)
    val animeDetail = _animeDetail
    fun fetchAnimeDetail(animeId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getAnimeDetail(animeId)
                _animeDetail.value = response
            } catch (e: Exception) {
                println("Error fetching anime detail: ${e.message}")
            }
        }
    }
}
