package com.example.myapplication.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.AnimeEntry
import com.example.myapplication.data.repository.AnimeRepository
import com.example.myapplication.di.NetworkModule.provideAnimeRepository
import com.example.myapplication.di.NetworkModule.provideJikanApi
import com.example.myapplication.di.NetworkModule.provideRetrofit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel (
    private val repository : AnimeRepository = provideAnimeRepository(api=provideJikanApi(retrofit=provideRetrofit()))
) : ViewModel() {

    private val _recommendations = MutableStateFlow<List<AnimeEntry>>(emptyList())
    val recommendations = _recommendations

    init {
        loadRecommendations()
    }

    private fun loadRecommendations(){
        viewModelScope.launch {
            try {
                _recommendations.value = repository.getRecommendations()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}