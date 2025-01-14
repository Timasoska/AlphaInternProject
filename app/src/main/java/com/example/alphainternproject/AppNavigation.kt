package com.example.alphainternproject

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.alphainternproject.presentation.ui.BinHistoryScreen
import com.example.alphainternproject.presentation.ui.BinInfoScreen
import com.example.alphainternproject.presentation.viewmodel.BinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(viewModel: BinViewModel) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("BIN Info") }
            )
        },
        content = { paddingValues ->
            NavigationComponent(navController = navController, viewModel = viewModel, paddingValues = paddingValues)
        },
        bottomBar = {
            BottomNavigationComponent(navController = navController)
        }
    )
}

@Composable
fun NavigationComponent(navController: NavHostController, viewModel: BinViewModel, paddingValues: PaddingValues) {
    NavHost(navController = navController, startDestination = "bin_info", Modifier.padding(paddingValues)) {
        composable("bin_info") {
            BinInfoScreen(viewModel = viewModel)
        }
        composable("bin_history") {
            BinHistoryScreen(viewModel = viewModel)
        }
    }
}

@Composable
fun BottomNavigationComponent(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Запросы") },
            selected = navController.currentDestination?.route == "bin_info",
            onClick = { navController.navigate("bin_info") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = null) },
            label = { Text("История") },
            selected = navController.currentDestination?.route == "bin_history",
            onClick = { navController.navigate("bin_history") }
        )
    }
}
