package org.sopt.at.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import kotlinx.serialization.Serializable
import org.sopt.at.component.HomeLazyRow
import org.sopt.at.home.contents.Top20Contents

@Serializable
data object Home

@Composable
fun HomeScreen(
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(Color.Black),
    ) {
        Text(text = "Home", fontSize = 24.sp, color = Color.White)
        HomeLazyRow(
            title = "오늘의 티빙 TOP 20",
            contentsList = Top20Contents,
            withRank = true
        )
        Spacer(
            modifier = Modifier
                .weight(3f)
        )
    }
}