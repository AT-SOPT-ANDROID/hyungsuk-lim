package org.sopt.at.home.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable
import org.sopt.at.home.component.CategoryButtonLazyRow
import org.sopt.at.home.component.GenreTabHeader
import org.sopt.at.home.component.HomeLazyRow
import org.sopt.at.home.component.HomeTopBannerLazyRow
import org.sopt.at.home.model.bannerContents
import org.sopt.at.home.model.categoryList
import org.sopt.at.home.model.liveNowContentsList
import org.sopt.at.home.model.top20Contents

@Serializable
data object Home

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(Color.Black)
    ) {
        stickyHeader {
            GenreTabHeader(
                modifier = Modifier

            )
        }
        item {
            HomeTopBannerLazyRow(
                topBannerList = bannerContents
            )
        }
        item {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
            )
        }
        item {
            CategoryButtonLazyRow(
                categoryList = categoryList
            )
        }
        item {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
            )
        }
        item {
            HomeLazyRow(
                title = "오늘의 티빙 TOP 20",
                contentsList = top20Contents,
                withRank = true
            )
        }
        item {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
            )

        }
        item {
            HomeLazyRow(
                title = "지금 방영 중인 콘텐츠",
                contentsList = liveNowContentsList,
                withRank = false
            )
        }
        item {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
            )
        }
    }
}