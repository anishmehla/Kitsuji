package com.example.myapplication.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.navigation.Navigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicScreen(
    pageContent: @Composable (Modifier) -> Unit,
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier) {
    Column(modifier=Modifier
        .fillMaxSize()
        .statusBarsPadding()){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val currentAppRoute = Navigation.entries.find { it.name == currentRoute }
        Scaffold (
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = currentAppRoute?.name ?: "",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors= TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = MaterialTheme.colorScheme.onSurface,
                        actionIconContentColor = MaterialTheme.colorScheme.primary
                    )
                    )
            },
            bottomBar = {
                BottomAppBar {
                    NavigationBar {
                        Navigation.entries.toTypedArray().forEach { navigation ->
                            NavigationBarItem(
                                selected = currentRoute == navigation.name,
                                onClick = { navController.navigate(navigation.name) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop=true
                                    restoreState=true
                                }
                                          },
                                icon = {
                                    Icon(
                                        painter=painterResource(navigation.icon),
                                        contentDescription = navigation.label
                                    )
                                },
                                label = {Text(navigation.label)}
                            )


                        }

                    }
                }
            }
        ){ innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                pageContent(Modifier.padding(innerPadding))
            }
        }
    }

}