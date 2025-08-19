package com.example.myapplication.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun ListScreen(navController: NavHostController) {
    BasicScreen({ PageContent() },
        navController=navController)
}

@Composable
private fun PageContent(){
    Column(){
        Text("My List")
    }

}

@Preview(showSystemUi = true)
@Composable
private fun DefaultPreview(navController: NavHostController=rememberNavController()){
    ListScreen(navController)
}