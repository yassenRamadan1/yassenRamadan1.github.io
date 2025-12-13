package org.example.garfend.models

import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.rgb

enum class Theme(
    val hex: String,
    val rgb: CSSColorValue
) {
    Primary(hex ="#FFFFFF", rgb = rgb(r = 255, g = 255, b = 255)),
    Secondary(hex =  "#FBFBFB", rgb = rgb(r = 251, g = 251, b = 251)),
    Gray(hex = "#AAAAAA", rgb = rgb(r = 170, g = 170, b = 170)),
    LightGray(hex = "#0F121A", rgb = rgb(r = 15, g = 18, b = 26)),
    LighterGray(hex = "#1A1F2B", rgb = rgb(r = 26, g = 31, b = 43) ),
    LightGrayBg(hex = "#05080F", rgb = rgb(r = 5, g = 8, b = 15)),
    LightRed(hex = "#FF3B5C", rgb = rgb(r = 255, g = 59, b = 92)),
    LighterRed(hex = "#FF6B86", rgb = rgb(r = 255, g = 107, b = 134)),
    DarkRed(hex = "#D62F4B", rgb = rgb(r = 214, g = 47, b = 75)),
}
