package org.sopt.at.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun BannerThumbnailContainer(
    imageId: Int,
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(12.dp)),
        painter = painterResource(id = imageId),
        contentDescription = "s",
        contentScale = ContentScale.Fit
    )

}