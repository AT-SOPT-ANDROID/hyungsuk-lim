package org.sopt.at.home.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import kotlinx.serialization.Serializable
import org.sopt.at.R


@Serializable
data object Live

@Composable
fun LiveScreen(
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier.padding(paddingValues),
    ) {
        Text(text = stringResource(R.string.bottom_nav_live), fontSize = 24.sp)
    }
}