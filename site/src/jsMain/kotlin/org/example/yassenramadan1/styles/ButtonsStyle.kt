package org.example.yassenramadan1.styles

import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.transform
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.hover
import org.example.yassenramadan1.util.Constants.FONT_FAMILY
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgb

val GlowingButtonStyle = CssStyle {
    base {
        Modifier
            .backgroundColor(rgb(95, 212, 168))
            .borderRadius(12.px)
            .border(0.px)
            .padding(topBottom = 12.px, leftRight = 24.px)
            .fontFamily(*FONT_FAMILY)
            .fontSize(16.px)
            .fontWeight(600)
            .color(rgb(255, 255, 255))
            .transition(
                Transition.of(property = "background-color", duration = 180.ms),
                Transition.of(property = "transform", duration = 180.ms)
            )
    }
    hover {
        Modifier
            .backgroundColor(rgb(75, 184, 150))
            .transform { translateY((-2).px) }
    }
}
