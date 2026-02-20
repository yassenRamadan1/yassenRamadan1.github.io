package org.example.yassenramadan1.models

import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.rgb

enum class Theme(
    val hex: String,
    val rgb: CSSColorValue
) {
    Primary(hex ="#FFFFFF", rgb = rgb(r = 255, g = 255, b = 255)),
    Secondary(hex =  "#FBFBFB", rgb = rgb(r = 251, g = 251, b = 251)),
    Gray(hex = "#AAAAAA", rgb = rgb(r = 170, g = 170, b = 170)),
    LightGray(hex = "#0D2B3E", rgb = rgb(r = 13, g = 43, b = 62)),
    LighterGray(hex = "#1A3A52", rgb = rgb(r = 26, g = 58, b = 82) ),
    LightGrayBg(hex = "#0A1F2E", rgb = rgb(r = 10, g = 31, b = 46)),
    LightGreen(hex = "#5FD4A8", rgb = rgb(r = 95, g = 212, b = 168)),
    LighterGreen(hex = "#7FDDBA", rgb = rgb(r = 127, g = 221, b = 186)),
    DarkGreen(hex = "#4BB896", rgb = rgb(r = 75, g = 184, b = 150)),
    LightBlue(hex = "#5B9BD5", rgb = rgb(r = 91, g = 155, b = 213)),
    DarkNavy(hex = "#0D2B3E", rgb = rgb(r = 13, g = 43, b = 62)),
}
