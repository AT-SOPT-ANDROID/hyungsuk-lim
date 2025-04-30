package org.sopt.at.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.at.history.History
import org.sopt.at.history.HistoryScreen
import org.sopt.at.live.Live
import org.sopt.at.live.LiveScreen
import org.sopt.at.search.Search
import org.sopt.at.search.SearchScreen
import org.sopt.at.shorts.Shorts
import org.sopt.at.shorts.ShortsScreen
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