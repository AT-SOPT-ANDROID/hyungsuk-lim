package org.sopt.at.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.sopt.at.R
import org.sopt.at.home.screen.History
import org.sopt.at.home.screen.Home
import org.sopt.at.home.screen.Live
import org.sopt.at.home.screen.Search
import org.sopt.at.home.screen.Shorts

@Composable
fun BottomNavBar(navController: NavController) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BottomNavItem(
                onClick = { navController.navigate(route = Home) },
                painter = painterResource(R.drawable.baseline_home_24),
                buttonName = "HOME"
            )
            BottomNavItem(
                onClick = { navController.navigate(route = Shorts) },
                painter = painterResource(R.drawable.icons8_youtube_shorts),
                buttonName = "SHORTS"
            )
            BottomNavItem(
                onClick = { navController.navigate(route = Live) },
                painter = painterResource(R.drawable.live_svgrepo_com),
                buttonName = "LIVE"
            )
            BottomNavItem(
                onClick = { navController.navigate(route = Search) },
                painter = painterResource(R.drawable.search_svgrepo_com),
                buttonName = "SEARCH"
            )
            BottomNavItem(
                onClick = { navController.navigate(route = History) },
                painter = painterResource(R.drawable.history_svgrepo_com),
                buttonName = "HISTORY"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavBarPreview() {
    BottomNavBar(rememberNavController())
}