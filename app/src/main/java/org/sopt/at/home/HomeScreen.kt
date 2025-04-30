package org.sopt.at.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable
import org.sopt.at.R
import org.sopt.at.home.component.CategoryButtonSection
import org.sopt.at.home.component.GenreTabHeader
import org.sopt.at.home.component.HomeContentSection
import org.sopt.at.home.component.HomeTopBannerSection
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
            HomeTopBannerSection(
                topBannerList = bannerContents
            )
        }
        item {
            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )
        }
        item {
            CategoryButtonSection(
                categoryList = categoryList
            )
        }
        item {
            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )
        }
        item {
            HomeContentSection(
                title = stringResource(R.string.section_top20),
                contentsList = top20Contents,
                withRank = true
            )
        }
        item {
            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )
        }
        item {
            HomeContentSection(
                title = stringResource(R.string.section_nowlive),
                contentsList = liveNowContentsList,
                withRank = false
            )
        }
        item {
            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )
        }
    }
}