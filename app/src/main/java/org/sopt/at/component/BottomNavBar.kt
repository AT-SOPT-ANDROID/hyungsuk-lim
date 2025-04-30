package org.sopt.at.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.sopt.at.R
import org.sopt.at.history.History
import org.sopt.at.home.Home
import org.sopt.at.live.Live
import org.sopt.at.search.Search
import org.sopt.at.shorts.Shorts

@Composable
fun BottomNavBar(navController: NavController) {
    BottomAppBar(
        containerColor = Color.Black,
        contentColor = Color.White,
    ) {
        val navOptions = navOptions {
            launchSingleTop = true
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BottomNavItem(
                onClick = { navController.navigate(route = Home, navOptions = navOptions) },
                painter = painterResource(R.drawable.baseline_home_24),
                buttonName = stringResource(R.string.bottom_nav_home)
            )
            BottomNavItem(
                onClick = { navController.navigate(route = Shorts, navOptions = navOptions) },
                painter = painterResource(R.drawable.icons8_youtube_shorts),
                buttonName = stringResource(R.string.bottom_nav_shorts)
            )
            BottomNavItem(
                onClick = { navController.navigate(route = Live, navOptions = navOptions) },
                painter = painterResource(R.drawable.live_svgrepo_com),
                buttonName = stringResource(R.string.bottom_nav_live)
            )
            BottomNavItem(
                onClick = { navController.navigate(route = Search, navOptions = navOptions) },
                painter = painterResource(R.drawable.search_svgrepo_com),
                buttonName = stringResource(R.string.bottom_nav_search)
            )
            BottomNavItem(
                onClick = { navController.navigate(route = History, navOptions = navOptions) },
                painter = painterResource(R.drawable.history_svgrepo_com),
                buttonName = stringResource(R.string.bottom_nav_history)
            )
        }
    }
}

@Composable
fun BottomNavItem(
    onClick: () -> Unit,
    painter: Painter,
    buttonName: String
) {
    Column(
        modifier = Modifier
            .size(48.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(36.dp),
            painter = painter,
            contentDescription = buttonName
        )
        Text(
            text = buttonName,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NavBarPreview() {
    BottomNavBar(rememberNavController())
}