package com.example.myapplication.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.AnimeDetailData
import com.example.myapplication.data.model.AnimeDetailResponse
import com.example.myapplication.data.model.AnimeEntry
import com.example.myapplication.data.model.AnimeSearchResponse
import com.example.myapplication.data.repository.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeSearchViewModel @Inject constructor(
    private val repository: AnimeRepository
) : ViewModel(){
    private val _searchResult = MutableStateFlow<List<AnimeDetailData>>(emptyList())
    val searchResult = _searchResult

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading

    fun search(query:String){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.searchAnime(query)
                _searchResult.value = response.data2
            } catch (e: Exception){
                _searchResult.value = emptyList()
            }
            _isLoading.value = false
        }
    }
}