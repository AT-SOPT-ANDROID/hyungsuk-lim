package org.sopt.at.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.at.home.contents.Category
import org.sopt.at.home.contents.CategoryList

@Composable
fun CategoryButtonLazyRow(
    categoryList: List<Category>
) {
    LazyRow(
        modifier = Modifier
            .background(Color.Black),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categoryList) { category ->
            CategoryButtonContainer(
                contentsId = category.imageId,
                contentDescription = category.name
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonLazyRowPreview() {
    CategoryButtonLazyRow(categoryList = CategoryList)
}