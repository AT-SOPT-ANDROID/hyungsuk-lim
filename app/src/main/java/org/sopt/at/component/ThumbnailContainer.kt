package org.sopt.at.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ThumbnailContainer(
    imageId: Int
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp)),
        ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = "s"
        )
    }
}