package org.sopt.at.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import kotlinx.serialization.Serializable


@Serializable
data object Shorts

@Composable
fun ShortsScreen(
    paddingValues: PaddingValues
){
    Column(
        modifier = Modifier.padding(paddingValues)
    ) {
        Text(text = "Shorts", fontSize = 24.sp)
    }
}