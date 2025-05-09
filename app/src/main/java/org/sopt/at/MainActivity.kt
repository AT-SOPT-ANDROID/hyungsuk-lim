package org.sopt.at

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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import org.sopt.at.component.BottomNavBar
import org.sopt.at.component.TopBar
import org.sopt.at.ui.theme.TvingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val mainViewModel: MainViewModel = viewModel()
            val isLogin = mainViewModel.isLogin
            val snackbarHostState = remember { SnackbarHostState() }
            TvingTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
                        ),
                    topBar = {
                        if (isLogin.value) {
                            TopBar(
                                navController = navController,
                            )
                        }
                    },
                    bottomBar = {
                        if (isLogin.value) {
                            BottomNavBar(navController = navController)
                        }
                    },
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) { innerPadding ->
                    MainNavHost(
                        navController = navController,
                        paddingValues = innerPadding
                    )
                }
            }
        }
    }
}
