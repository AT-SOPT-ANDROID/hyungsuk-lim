package org.sopt.at.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.sopt.at.R
import org.sopt.at.home.screen.Home
import org.sopt.at.my.My

@Composable
fun TopBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = Color.Black
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable { navController.navigate(Home) }
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxHeight(),
                    painter = painterResource(R.drawable.tving_bi_logotype),
                    contentDescription = "TVING LOGO",
                    tint = Color.Unspecified
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.LightGray)
                    .clickable { navController.navigate(My) }
            ) {
                Image(
                    painter = painterResource(R.drawable.tving_profile),
                    contentDescription = "MY Profile",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxHeight()
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar(rememberNavController())
}
