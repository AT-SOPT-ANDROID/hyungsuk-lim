package org.sopt.at.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable
import org.sopt.at.home.component.CategoryButtonLazyRow
import org.sopt.at.home.component.HomeLazyRow
import org.sopt.at.home.component.HomeTopBannerLazyRow
import org.sopt.at.home.contents.BannerContents
import org.sopt.at.home.contents.CategoryList
import org.sopt.at.home.contents.LiveNowContentsList
import org.sopt.at.home.contents.Top20Contents

@Serializable
data object Home

@Composable
fun HomeScreen(
    paddingValues: PaddingValues
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(state = scrollState),
    ) {
//        Text(text = "Home", fontSize = 24.sp, color = Color.White)
        HomeTopBannerLazyRow(
            topBannerList = BannerContents
        )
        Spacer(
            modifier = Modifier.weight(1f)
        )
        CategoryButtonLazyRow(
            categoryList = CategoryList
        )
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        HomeLazyRow(
            title = "오늘의 티빙 TOP 20",
            contentsList = Top20Contents,
            withRank = true
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )
        HomeLazyRow(
            title = "지금 방영 중인 콘텐츠",
            contentsList = LiveNowContentsList,
            withRank = false
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )
    }
}