package com.gowittgroup.mystiqflix.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.gowittgroup.mystiqflix.android.components.AppBar
import com.gowittgroup.mystiqflix.android.desitnation.Home
import com.gowittgroup.mystiqflix.android.desitnation.movieDestinations
import com.gowittgroup.mystiqflix.android.home.HomeScreen
import com.gowittgroup.mystiqflix.android.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MystiqFlixApp() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()


    val isSystemDark = isSystemInDarkTheme()
    val statusBarColor = if (isSystemDark) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        Color.Transparent
    }

    SideEffect {
        systemUiController.setStatusBarColor(statusBarColor, darkIcons = !isSystemDark)
    }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = movieDestinations.find {
        backStackEntry?.destination?.route == it.route || backStackEntry?.destination?.route == it.routWithArgs
    } ?: Home

    Scaffold(
        topBar = {
            AppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = currentScreen
            ) {

            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            startDestination = Home.routWithArgs
        ) {
            composable(
                Home.routWithArgs
            ) {
                val homeViewModel: HomeViewModel = koinViewModel()

                HomeScreen(uiState = homeViewModel.uiState, loadNextPage = {
                    homeViewModel.loadMovies(forceReload = it)
                }, navigateToDetail = {})
            }
        }

    }
}