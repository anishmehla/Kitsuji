package com.example.myapplication.ui.theme.screens

import AnimeCard
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.navigation.NavGraph
import com.example.myapplication.ui.theme.AnimeSearchViewModel

@Composable
fun SearchScreen(navController: NavHostController) {
    BasicScreen(
        { PageContent(onAnimeClick= { animeId -> navController.navigate("animeDetail/${animeId}")}) },
    navController=navController)
}

@Preview(showSystemUi = true)
@Composable
private fun DefaultPreview(navController: NavHostController=rememberNavController()){
    SearchScreen(navController)
}

@Composable
private fun PageContent(
    viewModel: AnimeSearchViewModel = hiltViewModel(),
    onAnimeClick: (Int) -> Unit
) {
    val results by viewModel.searchResult.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                if (it.length >= 2) { // search after 3 chars
                    viewModel.search(it)
                }
            },
            label = { Text("Search Anime") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            },
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        val safeResults = results ?: emptyList()

        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            safeResults.isEmpty() && query.length >= 2 -> {
                Text(
                    text = "No anime found",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            else -> {
                LazyColumn {
                    items(safeResults) { anime ->
                        AnimeCard(
                            anime = anime,
                            onClick = { onAnimeClick(anime.malId) }
                        )
                        HorizontalDivider(
                            thickness = DividerDefaults.Thickness,
                            color = DividerDefaults.color
                        )
                    }
                }
            }
        }
    }
}
