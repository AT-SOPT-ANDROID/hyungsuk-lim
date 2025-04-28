package org.sopt.at.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.at.home.screen.History
import org.sopt.at.home.screen.HistoryScreen
import org.sopt.at.home.screen.Home
import org.sopt.at.home.screen.HomeScreen
import org.sopt.at.home.screen.Live
import org.sopt.at.home.screen.LiveScreen
import org.sopt.at.home.screen.Search
import org.sopt.at.home.screen.SearchScreen
import org.sopt.at.home.screen.Shorts
import org.sopt.at.home.screen.ShortsScreen
import org.sopt.at.my.My
import org.sopt.at.my.MyScreen

@Composable
fun BarNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(paddingValues = innerPadding)
        }
        composable<Shorts> {
            ShortsScreen(paddingValues = innerPadding)
        }
        composable<Live> {
            LiveScreen(paddingValues = innerPadding)
        }
        composable<Search> {
            SearchScreen(paddingValues = innerPadding)
        }
        composable<History> {
            HistoryScreen(paddingValues = innerPadding)
        }
        composable<My> {
            MyScreen(paddingValues = innerPadding)
        }
    }

}