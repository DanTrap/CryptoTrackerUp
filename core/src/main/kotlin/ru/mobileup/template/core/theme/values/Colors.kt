package ru.mobileup.template.core.theme.values

import androidx.compose.ui.graphics.Color
import ru.mobileup.template.core.theme.custom.BackgroundColors
import ru.mobileup.template.core.theme.custom.ButtonColors
import ru.mobileup.template.core.theme.custom.CustomColors
import ru.mobileup.template.core.theme.custom.IconColors
import ru.mobileup.template.core.theme.custom.TextColors

val LightAppColors = CustomColors(
    isLight = true,
    background = BackgroundColors(
        screen = Color(0xFFFFFFFF),
        toast = Color(0xFF000000)
    ),
    text = TextColors(
        primary = Color(0xFF000000),
        secondary = Color(0xFF797979),
        invert = Color(0xFFFFFFFF),
        positiveChangeColor = Color(0xFF2A9D8F),
        negativeChangeColor = Color(0xFFEB5757),
    ),
    icon = IconColors(
        primary = Color(0xFF000000),
        secondary = Color(0xFF797979),
        invert = Color(0xFFFFFFFF),
    ),
    button = ButtonColors(
        primary = Color(0xFFFF9F00),
        secondary = Color.Black.copy(alpha = 0.18f),
    )
)

val DarkAppColors = LightAppColors
