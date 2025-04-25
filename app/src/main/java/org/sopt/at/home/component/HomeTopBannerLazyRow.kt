package org.sopt.at.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.home.model.Content
import org.sopt.at.home.model.bannerContents

@Composable
fun HomeTopBannerLazyRow(
    topBannerList: List<Content>
) {
    LazyRow(
        modifier = Modifier
            .background(Color.Black),
        contentPadding = PaddingValues(start = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(topBannerList) { banner ->
            BannerThumbnailContainer(
                modifier = Modifier
                    .height(540.dp)
                    .width(380.dp),
                imageId = banner.imageId
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopBannerLazyRowPreview() {
    HomeTopBannerLazyRow(
        topBannerList = bannerContents
    )
}