package com.example.myapplication.ui.theme.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.DaoRepository
import com.example.myapplication.data.room.AnimeEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(private val repository: DaoRepository) : ViewModel() {
    val animeList = repository.getAllAnime()
        .stateIn(viewModelScope, SharingStarted.Lazily , emptyList())

    fun addAnime(anime: AnimeEntity) {
        viewModelScope.launch {
            repository.insertAnime(anime)
        }
    }

    fun removeAnime(anime: AnimeEntity) {
        viewModelScope.launch {
            repository.deleteAnime(anime)
        }
    }
    private val _searchQuery = MutableStateFlow("")
    private val _statusFilter = MutableStateFlow<String?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val animeListWithFilters = combine(_searchQuery, _statusFilter) { query, statusFilter ->
        query to statusFilter}.flatMapLatest { (query, statusFilter) ->
        when {
            query.isNotEmpty() && statusFilter != null -> repository.searchAndFilterList(query, statusFilter)
            query.isNotEmpty() -> repository.searchList(query)
            statusFilter != null -> repository.filterList(statusFilter)
            else -> repository.getAllAnime()
        }
    }.stateIn(viewModelScope , SharingStarted.WhileSubscribed(5000) , emptyList())

    fun setSearchQuery(query : String){
        _searchQuery.value = query
    }

    fun setStatusFilter(statusFilter: String?){
        _statusFilter.value = statusFilter
    }
}
