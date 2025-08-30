import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import coil3.compose.rememberAsyncImagePainter
import com.example.myapplication.data.model.AnimeDetailData
import com.example.myapplication.data.model.AnimeEntry

@Composable
fun AnimeCard(
    anime: AnimeDetailData,       // your data class for a single anime
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Anime image
            Image(
                painter = rememberAsyncImagePainter(anime.images?.jpg?.imageUrl?:""),
                contentDescription = anime.title,
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Anime title
            Column {
                Text(text = anime.title?:"", style = MaterialTheme.typography.titleMedium)
                Text(text = anime.type?: "", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAnimeDialog(Title : String = "Add",animeTitle : String , onConfirm : (score : Float , status : String) -> Unit, onDismiss : () -> Unit) {
    var score by remember { mutableFloatStateOf(0f) }
    var status by remember { mutableStateOf("Plan to Watch") }
    var expanded by remember { mutableStateOf(false) }
    val statuses = listOf("Watching", "Completed", "On Hold", "Dropped", "Plan to Watch")

    BasicAlertDialog(
        onDismissRequest = { onDismiss },
        properties = DialogProperties(),
        modifier = Modifier.wrapContentSize(),
        content = {
            Surface(
                shape = MaterialTheme.shapes.medium,
                tonalElevation = 6.dp
            ) {
                Column(modifier= Modifier.wrapContentSize()) {
                    Text(
                        text = "$Title $animeTitle",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(4.dp).align(Alignment.CenterHorizontally)
                    )
                    Spacer(Modifier.height(16.dp))
                    //Score Slider
                    Text("Score : ${score.toInt()}",
                        modifier = Modifier.padding(8.dp))
                    Slider(
                        value = score,
                        onValueChange = { score = it },
                        valueRange = 0f..10f,
                        steps = 9,
                        modifier = Modifier.padding(8.dp)
                    )
                    Spacer(Modifier.height(16.dp))

                    // Status Dropdown
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded },
                        modifier = Modifier.align(Alignment.CenterHorizontally).padding(8.dp)
                    ) {
                        TextField(
                            value = status,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Status") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            },
                            modifier = Modifier.menuAnchor()
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            statuses.forEach { selectStatus ->
                                DropdownMenuItem(
                                    text = { Text(selectStatus) },
                                    onClick = {
                                        status = selectStatus
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(24.dp))

                    // Buttons
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(onClick = { onDismiss() }) {
                            Text("Cancel")
                        }
                        TextButton(
                            onClick = {
                                onConfirm(score, status)
                                onDismiss()
                            }
                        ) {
                            Text("Add")
                        }
                    }
                }
            }
        }
    )
}
