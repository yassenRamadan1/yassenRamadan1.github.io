package org.example.yassenramadan1.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import org.example.yassenramadan1.util.Res
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Img

// Ramadan color palette
object RamadanColors {
    const val GOLD = "rgb(255, 215, 0)"
    const val DARK_GOLD = "rgb(218, 165, 32)"
    const val LIGHT_GOLD = "rgb(255, 239, 170)"
}

@Composable
fun RamadanLantern(
    modifier: Modifier = Modifier,
    size: CSSpxValue = 60.px
) {
    Img(
        // TODO: put lantern lottie here
        src = "",
        alt = "Ramadan Lantern",
        attrs = modifier
            .width(size)
            .height(size * 1.5)
            .styleModifier {
                property("animation", "float 3s ease-in-out infinite, glow 2s ease-in-out infinite")
                property("filter", "drop-shadow(0 0 10px rgba(255, 215, 0, 0.6))")
            }
            .toAttrs()
    )
}

@Composable
fun RamadanStar(
    modifier: Modifier = Modifier,
    size: CSSpxValue = 30.px
) {
    Img(
        src = "/ramadan/star.svg",
        alt = "Islamic Star",
        attrs = modifier
            .width(size)
            .height(size)
            .styleModifier {
                property("animation", "twinkle 2s ease-in-out infinite")
                property("filter", "drop-shadow(0 0 8px rgba(255, 215, 0, 0.7))")
            }
            .toAttrs()
    )
}

@Composable
fun RamadanMoon(
    modifier: Modifier = Modifier,
    size: CSSpxValue = 80.px
) {
    //TODO: put the moon lottie here
    Img(
        src = "",
        alt = "Crescent Moon",
        attrs = modifier
            .width(size)
            .height(size)
            .styleModifier {
                property("animation", "glow 3s ease-in-out infinite")
                property("filter", "drop-shadow(0 0 15px rgba(255, 215, 0, 0.8))")
            }
            .toAttrs()
    )
}

@Composable
fun RamadanMosque(
    modifier: Modifier = Modifier,
    size: CSSpxValue = 120.px
) {
    Img(
        // TODO: put mosque here
        src = "",
        alt = "Mosque",
        attrs = modifier
            .width(size)
            .height(size * 0.67)
            .styleModifier {
                property("filter", "drop-shadow(0 0 12px rgba(255, 215, 0, 0.5))")
            }
            .toAttrs()
    )
}

@Composable
fun RamadanHangingDecoration(
    modifier: Modifier = Modifier,
    size: CSSpxValue = 150.px
) {
    Image(
        src = Res.Image.ramadanMoon,
        modifier = Modifier
            .position(Position.Fixed)
            .styleModifier {
                property("right", "0px")
            }
            .height(400.px)
            .zIndex(10)
    )
}

@Composable
fun RamadanDetailedMosque(
    modifier: Modifier = Modifier,
    size: CSSpxValue = 200.px
) {
    Image(
        src = Res.Image.ramadanMosque,
        modifier = modifier
            .height(size)
            .styleModifier {
                property("filter", "drop-shadow(0 0 12px rgba(255, 215, 0, 0.5))")
            }
    )
}

@Composable
fun RamadanLights(
    modifier: Modifier = Modifier,
    count: Int = 5
) {
    org.jetbrains.compose.web.dom.Div(
        attrs = modifier
            .display(DisplayStyle.Flex)
            .gap(10.px)
            .toAttrs()
    ) {
        repeat(count) { index ->
            org.jetbrains.compose.web.dom.Div(
                attrs = Modifier
                    .width(8.px)
                    .height(8.px)
                    .borderRadius(50.percent)
                    .styleModifier {
                        property("background-color", if (index % 2 == 0) RamadanColors.GOLD else RamadanColors.LIGHT_GOLD)
                        property("box-shadow", "0 0 10px ${if (index % 2 == 0) RamadanColors.GOLD else RamadanColors.LIGHT_GOLD}")
                        property("animation", "blink ${1 + index * 0.2}s ease-in-out infinite")
                        property("animation-delay", "${index * 0.2}s")
                    }
                    .toAttrs()
            )
        }
    }
}
