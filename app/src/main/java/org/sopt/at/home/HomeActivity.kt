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
import androidx.navigation.compose.rememberNavController
import org.sopt.at.component.BottomNavBar
import org.sopt.at.component.TopBar
import org.sopt.at.ui.theme.TvingTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            TvingTheme {
                val userId = intent.getStringExtra("userId").orEmpty()
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
                        ),
                    topBar = {
                        TopBar(
                            navController = navController,
                            userId = userId
                        )
                    },
                    bottomBar = {
                        BottomNavBar(navController = navController)
                    }
                ) { innerPadding ->
                    BarNavHost(
                        navController = navController,
                        innerPadding = innerPadding,
                    )
                }
            }
        }
    }
}