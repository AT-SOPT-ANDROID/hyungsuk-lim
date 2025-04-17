package org.sopt.at.my

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MyScreen(
    modifier: Modifier = Modifier,
    userId: String
) {
    Column(modifier = modifier) {
        Text(userId)
    }
}