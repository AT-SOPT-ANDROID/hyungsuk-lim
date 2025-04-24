package org.sopt.at.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.sopt.at.component.BottomNavBar
import org.sopt.at.component.TopBar
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
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            ATSOPTANDROIDTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
                        ),
                    topBar = {
                        TopBar(navController = navController)
                    },
                    bottomBar = {
                        BottomNavBar(navController = navController)
                    }
                ) { innerPadding ->
                    val userId = intent.getStringExtra("userId").orEmpty()
                    NavHost(
                        navController = navController,
                        startDestination = Home
                    ) {
                        composable<Home> {
                            HomeScreen(
                                paddingValues = innerPadding
                            )
                        }
                        composable<Shorts> {
                            ShortsScreen(
                                paddingValues = innerPadding
                            )
                        }
                        composable<Live> {
                            LiveScreen(
                                paddingValues = innerPadding
                            )
                        }
                        composable<Search> {
                            SearchScreen(
                                paddingValues = innerPadding
                            )
                        }
                        composable<History> {
                            HistoryScreen(
                                paddingValues = innerPadding
                            )
                        }
                        composable<My> {
                            MyScreen(
                                paddingValues = innerPadding,
                                userId = userId
                            )
                        }
                    }
                }
            }
        }
    }
}