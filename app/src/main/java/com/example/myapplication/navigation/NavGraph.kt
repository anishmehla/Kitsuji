package com.example.myapplication.navigation

import com.example.myapplication.R
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.theme.screens.CommunityScreen
import com.example.myapplication.ui.theme.screens.HomeScreen
import com.example.myapplication.ui.theme.screens.ListScreen
import com.example.myapplication.ui.theme.screens.SearchScreen

enum class Navigation(val label: String, val icon: Int) {
    Home("Home", R.drawable.home),
    Community("Community", R.drawable.community),
    Search("Search", R.drawable.search),
    MyList("My List", R.drawable.list)
}
@Composable
fun NavGraph (navController: NavHostController){
    NavHost(navController= navController,
        startDestination = Navigation.Home.name)
    {
        composable(route = Navigation.Home.name){
            HomeScreen(navController)
        }
        composable(route = Navigation.Community.name){
            CommunityScreen(navController)
        }
        composable(route = Navigation.Search.name){
            SearchScreen(navController)
        }
        composable(route = Navigation.MyList.name){
            ListScreen(navController)

        }
    }

}