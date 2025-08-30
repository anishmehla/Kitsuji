package com.example.myapplication.ui.theme.screens

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import java.util.UUID
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.myapplication.ui.theme.screens.BasicScreen

@Composable
fun CommunityScreen(navController: NavHostController) {
    BasicScreen({ CommunityWebView("https://animeforums.net/") },navController)
}

@Composable
fun CommunityWebView(forumUrl: String) {
    // Use mutable state for the WebView reference
    val webViewState = remember { mutableStateOf<WebView?>(null) }

    // Handle back navigation
    BackHandler {
        webViewState.value?.let { webView ->
            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                // default back action (e.g., exit screen)
            }
        }
    }

    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient() // keeps navigation inside WebView
                settings.javaScriptEnabled = true
                loadUrl(forumUrl)
                webViewState.value = this // assign the actual WebView instance
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showSystemUi = true)
@Composable
private fun DefaultPreview(navController: NavHostController = rememberNavController()){
    CommunityScreen(navController)
}
