package org.sopt.at.home.model

import androidx.annotation.DrawableRes

data class Content(
    val title: String,
    @DrawableRes val imageId: Int,
    val genre: String,
    val rank: Int
)