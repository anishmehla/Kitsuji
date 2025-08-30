package com.example.myapplication.ui.theme.screens

import AddAnimeDialog
import android.R.attr.content
import android.R.attr.title
import android.content.Intent
import android.graphics.drawable.Icon
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.rememberAsyncImagePainter
import com.example.myapplication.ui.theme.HomeViewModel
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import com.example.myapplication.data.model.AnimeDetailData
import com.example.myapplication.data.model.AnimeDetailResponse
import com.example.myapplication.data.model.toEntity
import com.example.myapplication.data.room.AnimeEntity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun AnimeDetailScreen(
    animeId : Int,
    viewModel: AnimeDetailViewModel = hiltViewModel(),
    listViewModel: AnimeListViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {    val snackbarHostState = remember { SnackbarHostState() }
    var showDialog by remember {mutableStateOf(false)}
    val context = LocalContext.current
    val animeDetail by viewModel.animeDetail.collectAsState()
    LaunchedEffect(animeId) {
        viewModel.fetchAnimeDetail(animeId)
    }
    val anime = animeDetail?.data
    val scope = rememberCoroutineScope()

    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // --- IMAGE + TITLE ---
            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    AsyncImage(
                        model = anime?.images?.jpg?.largeImageUrl,
                        contentDescription = anime?.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = anime?.title ?: anime?.titleEnglish ?: "No Title",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    anime?.titleJapanese?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                            fontStyle = FontStyle.Italic,
                            color = Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            // --- STATS SECTION ---
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Stats", style = MaterialTheme.typography.titleMedium)

                        Spacer(modifier = Modifier.height(8.dp))

                        DetailRow("Type", anime?.type ?: "")
                        DetailRow("Episodes", anime?.episodes?.toString() ?: "")
                        DetailRow("Status", anime?.status ?: "")
                        DetailRow("Duration", anime?.duration ?: "")
                        DetailRow("Rating", anime?.rating ?: "")
                        DetailRow("Season", "${anime?.season ?: ""} ${anime?.year ?: ""}")
                        DetailRow("Score", anime?.score?.toString() ?: "")
                        DetailRow("Rank", "#${anime?.rank ?: "-"}")
                        DetailRow("Popularity", "#${anime?.popularity ?: "-"}")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            // --- GENRES + STUDIOS ---
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Genres", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(anime?.genres?.joinToString { it.name } ?: "No genres")

                        Spacer(modifier = Modifier.height(12.dp))

                        Text("Studios", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(anime?.studios?.joinToString { it.name } ?: "No studios")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            // --- SYNOPSIS ---
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Synopsis", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(anime?.synopsis ?: "No synopsis available")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            // --- TRAILER BUTTON ---
            item {
                if (!anime?.trailer?.url.isNullOrBlank()) {
                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, anime.trailer.url.toUri())
                            context.startActivity(intent)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = "Watch Trailer")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Watch Trailer")
                    }
                }

                Spacer(modifier = Modifier.height(80.dp)) // space for FAB
            }
        }

        SnackbarHost(hostState = snackbarHostState,modifier=Modifier.align(Alignment.BottomCenter).padding(16.dp))

        // --- Floating Add Button ---
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingActionButton(
                onClick = {showDialog = true},
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add to List")
            }
        }
        if (showDialog) {
        AddAnimeDialog(animeTitle = anime?.titleEnglish?:"",
            onConfirm = {score,status ->
                scope.launch {
                    listViewModel.addAnime(
                            anime = anime?.toEntity(score.toString(),status)!!)
                    showDialog = false
                    snackbarHostState.showSnackbar("Added to My List")
                }
            },
            onDismiss = {showDialog = false}
        )
        }
    }
}


@Composable
fun DetailRow(label: String, value: String?) {
    if (value != null && value.isNotBlank()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(label, fontWeight = FontWeight.SemiBold)
            Text(value)
        }
        Spacer(modifier = Modifier.height(6.dp))
    }
}

