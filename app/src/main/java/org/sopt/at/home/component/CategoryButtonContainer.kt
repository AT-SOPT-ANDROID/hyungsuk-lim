package org.sopt.at.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun CategoryButtonContainer(
    modifier: Modifier = Modifier,
    contentsId: Int,
    contentDescription: String
) {
    Button(
        modifier = modifier
            .width(100.dp)
            .height(60.dp),
        onClick = {},
        enabled = true,
        colors = ButtonColors(
            contentColor = Color.DarkGray,
            containerColor = Color.DarkGray,
            disabledContentColor = Color.Black,
            disabledContainerColor = Color.Black
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = contentsId),
            contentDescription = contentDescription,
            contentScale = ContentScale.Fit
        )
    }
}