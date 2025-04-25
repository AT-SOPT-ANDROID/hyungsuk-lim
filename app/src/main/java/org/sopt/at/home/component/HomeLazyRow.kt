package org.sopt.at.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.at.home.model.Content
import org.sopt.at.home.model.top20Contents

@Composable
fun HomeLazyRow(
    title: String,
    contentsList: List<Content>,
    withRank: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(start = 16.dp)
            .background(color = Color.Black),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )
        LazyRow(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(contentsList) { content ->
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Bottom
                ) {
                    if (withRank) {
                        Text(
                            text = content.rank.toString(),
                            fontSize = 64.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.Bottom)
                        )
                        Spacer(
                            modifier = Modifier
                                .width(4.dp)
                        )
                    }
                    ThumbnailContainer(
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(25f / 36f),
                        title = content.title,
                        imageId = content.imageId
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeLazyRowPreview() {
    HomeLazyRow("preview", top20Contents, true)
}