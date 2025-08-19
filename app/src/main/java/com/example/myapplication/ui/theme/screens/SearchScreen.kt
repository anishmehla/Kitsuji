package com.example.myapplication.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun SearchScreen(navController: NavHostController) {
    BasicScreen({ PageContent()},
    navController=navController)
}

@Preview(showSystemUi = true)
@Composable
private fun DefaultPreview(navController: NavHostController=rememberNavController()){
    SearchScreen(navController)
}

@Composable
private fun PageContent()
{
    Column(){
        OutlinedTextField(
            value = "Search",
            onValueChange = { },
            placeholder = { Text("Search anime...") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
        )

    }
}