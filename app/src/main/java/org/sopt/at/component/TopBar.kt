package org.sopt.at.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import org.sopt.at.home.Home
import org.sopt.at.my.My

@Composable
fun TopBar(
    navController: NavController,
    userId: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = Color.Black
            )
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .height(24.dp)
                .clickable { navController.navigate(Home) },
            painter = painterResource(R.drawable.tving_bi_logotype),
            contentDescription = "TVING LOGO",
            tint = Color.Unspecified
        )
        Image(
            painter = painterResource(R.drawable.tving_profile),
            contentDescription = "MY Profile",
            modifier = Modifier
                .height(24.dp)
                .clickable { navController.navigate(My(userId)) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar(rememberNavController(), "userID")
}
