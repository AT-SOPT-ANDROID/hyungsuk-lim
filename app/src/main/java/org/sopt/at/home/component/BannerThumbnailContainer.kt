package org.sopt.at.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun BannerThumbnailContainer(
    modifier: Modifier = Modifier,
    imageId: Int
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp)),
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = imageId),
            contentDescription = "s",
            contentScale = ContentScale.Fit
        )
    }
}