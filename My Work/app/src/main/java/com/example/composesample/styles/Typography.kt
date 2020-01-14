package com.example.composesample.styles

import androidx.ui.core.sp
import androidx.ui.material.Typography
import androidx.ui.text.TextStyle
import androidx.ui.text.font.Font
import androidx.ui.text.font.FontFamily
import androidx.ui.text.font.FontWeight

val regular = Font("opensans_regular.ttf", FontWeight.W400)
val semiBold = Font("opensans_semibold.ttf", FontWeight.W600)
val semiBoldItalic = Font("opensans_semibolditalic.ttf", FontWeight.W600)
val bold = Font("opensans_bold.ttf", FontWeight.W700)
val boldItalic = Font("opensans_bolditalic.ttf", FontWeight.W700)

val appFontFamily = FontFamily(fonts = listOf(regular, semiBold, semiBoldItalic, bold, boldItalic))

val appTypography = Typography(
        h5 = TextStyle(
                fontFamily = appFontFamily,
                fontSize = 24.sp
        ),
        h6 = TextStyle(
                fontFamily = appFontFamily,
                fontSize = 22.sp
        ),
        subtitle1 = TextStyle(
                fontFamily = appFontFamily,
                fontSize = 20.sp
        ),
        subtitle2 = TextStyle(
                fontFamily = appFontFamily,
                fontSize = 18.sp
        )
)