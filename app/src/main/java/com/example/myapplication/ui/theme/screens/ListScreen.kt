package com.example.myapplication.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.compose.rememberAsyncImagePainter

@Composable
fun ListScreen(navController: NavHostController) {
    BasicScreen({ PageContent(navController=navController) },
        navController=navController)
}

@Composable
private fun PageContent(viewModel : AnimeListViewModel = hiltViewModel(),
                        navController: NavHostController = rememberNavController(),
                        onAnimeClick: (Int) -> Unit = {navController.navigate("animeDetail/$it")}){
    val animeList by viewModel.animeListWithFilters.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var statusFilter by remember { mutableStateOf("All") }

    Column(modifier = Modifier.padding(16.dp)){
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                if(it.length <= 20){
                    viewModel.setSearchQuery(it)
                    searchText = it
                }
            },
            label = { Text("Search Anime")},
            leadingIcon = { Icon ( imageVector = Icons.Default.Search, contentDescription = "Search")},
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            shape = RoundedCornerShape(12.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))

        Box {
            Button(onClick = { expanded = !expanded }) { Text("Filter by Status") }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listOf(
                    "All",
                    "Plan to Watch",
                    "Watching",
                    "Completed",
                    "On Hold",
                    "Dropped"
                ).forEach { status ->
                    DropdownMenuItem(
                        text = { Text(status) },
                        onClick = {
                            statusFilter = status
                            expanded = false
                            viewModel.setStatusFilter(if (status == "All") null else status)
                        })
                }
            }
        }

    Spacer(modifier = Modifier.height(16.dp))
    if (animeList.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("No anime saved yet!")
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(animeList) { anime ->
                AnimeListItem(
                    anime = anime,
                    onClick = { onAnimeClick(anime.malId) },
                    onRemove = { viewModel.removeAnime(anime) }
                )
            }
        }
    }
}
}

@Composable
fun AnimeListItem(
    anime: com.example.myapplication.data.room.AnimeEntity,
    onClick: () -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onClick,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Anime image
            Image(
                painter = rememberAsyncImagePainter(anime.imageUrl),
                contentDescription = anime.title,
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 12.dp),
                contentScale = ContentScale.Crop
            )

            // Title & info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = anime.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                anime.episodes?.let {
                    Text(
                        text = "Episodes: $it",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            // Remove button
            IconButton(onClick = onRemove) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Remove",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun DefaultPreview(navController: NavHostController=rememberNavController()){
    ListScreen(navController)
}