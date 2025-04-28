package org.sopt.at.home.model

import org.sopt.at.R

data class Category(
    val name: String,
    val imageId: Int
)

val categoryList = listOf<Category>(
    Category(
        name = "KBO",
        imageId = R.drawable.kbo_button
    ),
    Category(
        name = "Apple TV+",
        imageId = R.drawable.appletvplus_button
    ),
    Category(
        name = "KBL",
        imageId = R.drawable.kbl_button
    ),
    Category(
        name = "Kids",
        imageId = R.drawable.kids_button
    ),
    Category(
        name = "UFC",
        imageId = R.drawable.ufc_button
    ),
    Category(
        name = "AFC",
        imageId = R.drawable.afc_button
    ),
    Category(
        name = "Tennis",
        imageId = R.drawable.tennis_button
    ),
    Category(
        name = "Style Collection",
        imageId = R.drawable.styleconnection_button
    ),
)