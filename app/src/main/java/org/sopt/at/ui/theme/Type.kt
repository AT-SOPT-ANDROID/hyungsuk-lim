package org.sopt.at.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.sopt.at.R

val tvingFontBold = FontFamily(Font(R.font.pretendard_bold))
val tvingFontSemiBold = FontFamily(Font(R.font.pretendard_semibold))
val tvingFontRegular = FontFamily(Font(R.font.pretendard_regular))

@Immutable
data class TvingTypography(
    val title: TextStyle,
    val subTitle: TextStyle,
    val body: TextStyle,
    val button: TextStyle,
    val caption: TextStyle
)

val defaultTvingTypography = TvingTypography(
    title = TextStyle(
        fontFamily = tvingFontBold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    subTitle = TextStyle(
        fontFamily = tvingFontSemiBold,
        fontSize = 18.sp,
        lineHeight = 26.sp
    ),
    body = TextStyle(
        fontFamily = tvingFontRegular,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    button = TextStyle(
        fontFamily = tvingFontBold,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    caption = TextStyle(
        fontFamily = tvingFontRegular,
        fontSize = 12.sp,
        lineHeight = 16.sp
    )
)

val LocalTvingTypographyProvider = staticCompositionLocalOf { defaultTvingTypography }

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)