package org.sopt.at

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.at.history.History
import org.sopt.at.history.HistoryScreen
import org.sopt.at.home.Home
import org.sopt.at.home.HomeScreen
import org.sopt.at.live.Live
import org.sopt.at.live.LiveScreen
import org.sopt.at.my.My
import org.sopt.at.my.MyScreen
import org.sopt.at.search.Search
import org.sopt.at.search.SearchScreen
import org.sopt.at.shorts.Shorts
import org.sopt.at.shorts.ShortsScreen
import org.sopt.at.signin.SignIn
import org.sopt.at.signin.SignInScreen
import org.sopt.at.signup.SignUp
import org.sopt.at.signup.SignUpScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = SignIn("", "")
    ) {
        composable<SignIn> {
            SignInScreen(
                navigateToHome = { navController.navigate(Home) },
                navigateToSignUp = { navController.navigate(SignUp) }
            )
        }
        composable<SignUp> {
            SignUpScreen(navController = navController)
        }
        composable<Home> {
            HomeScreen(paddingValues = paddingValues)
        }
        composable<Shorts> {
            ShortsScreen(paddingValues = paddingValues)
        }
        composable<Live> {
            LiveScreen(paddingValues = paddingValues)
        }
        composable<Search> {
            SearchScreen(paddingValues = paddingValues)
        }
        composable<History> {
            HistoryScreen(paddingValues = paddingValues)
        }
        composable<My> {
            MyScreen(
                paddingValues = paddingValues,
                navigateToSignIn = { id: String, password: String ->
                    navController.navigate(
                        SignIn(
                            id,
                            password
                        )
                    )
                })
        }
    }
}