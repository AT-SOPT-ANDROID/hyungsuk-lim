package org.sopt.at.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.R

@Composable
fun GenreTabHeader(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Black)
            .padding(top = 8.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.genre_tab_drama),
            color = Color.White,
            fontSize = 16.sp
        )
        Text(
            text = stringResource(R.string.genre_tab_entertainment),
            color = Color.White,
            fontSize = 16.sp
        )
        Text(
            text = stringResource(R.string.genre_tab_movie),
            color = Color.White,
            fontSize = 16.sp
        )
        Text(
            text = stringResource(R.string.genre_tab_sports),
            color = Color.White,
            fontSize = 16.sp
        )
        Text(
            text = stringResource(R.string.genre_tab_animation),
            color = Color.White,
            fontSize = 16.sp
        )

        Text(
            text = stringResource(R.string.genre_tab_news),
            color = Color.White,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GenreTabHeaderPreview() {
    GenreTabHeader()
}