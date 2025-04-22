package org.sopt.at.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.sopt.at.R
import org.sopt.at.home.Live

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
            contentDescription = "live"
        )
        Text(
            text = buttonName,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}