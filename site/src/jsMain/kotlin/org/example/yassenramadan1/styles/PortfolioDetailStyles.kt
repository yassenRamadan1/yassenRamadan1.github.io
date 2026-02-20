package org.example.yassenramadan1.styles

import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.hover
import org.example.yassenramadan1.models.Theme
import org.jetbrains.compose.web.css.px

// Back Button Style - Clean and simple
val BackButtonStyle = CssStyle {
    base {
        Modifier
            .padding(leftRight = 24.px, topBottom = 12.px)
            .borderRadius(12.px)
            .fontSize(15.px)
            .fontWeight(FontWeight.Medium)
            .color(Theme.Primary.rgb)
            .fontFamily("Space Grotesk", "sans-serif")
            .cursor(Cursor.Pointer)
            .styleModifier {
                property("background", "rgba(255, 255, 255, 0.06)")
                property("border", "1px solid rgba(255, 255, 255, 0.1)")
                property("text-decoration", "none")
                property("transition", "all 0.2s ease")
            }
    }

    hover {
        Modifier
            .styleModifier {
                property("background", "rgba(255, 255, 255, 0.1)")
                property("border-color", "rgba(255, 255, 255, 0.15)")
            }
    }
}

// App Icon Style - Rounded square like real app stores
val AppIconStyle = CssStyle {
    base {
        Modifier
            .borderRadius(22.px)
            .styleModifier {
                property("border", "1px solid rgba(255, 255, 255, 0.08)")
                property("box-shadow", "0 4px 12px rgba(0, 0, 0, 0.15)")
            }
    }
}

// Download/Store Button Style - Clean button like app stores
val AppStoreButtonStyle = CssStyle {
    base {
        Modifier
            .padding(leftRight = 20.px, topBottom = 14.px)
            .borderRadius(12.px)
            .cursor(Cursor.Pointer)
            .styleModifier {
                property("background", "rgba(255, 255, 255, 0.06)")
                property("border", "1px solid rgba(255, 255, 255, 0.12)")
                property("text-decoration", "none")
                property("transition", "all 0.2s ease")
            }
    }

    hover {
        Modifier
            .styleModifier {
                property("background", "rgba(255, 255, 255, 0.1)")
                property("border-color", "rgba(255, 255, 255, 0.2)")
                property("transform", "translateY(-1px)")
            }
    }
}

// Info Section Style - Subtle container for content sections
val InfoSectionStyle = CssStyle {
    base {
        Modifier
            .padding(topBottom = 20.px)
            .styleModifier {
                property("border-bottom", "1px solid rgba(255, 255, 255, 0.06)")
            }
    }
}
