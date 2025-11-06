package org.example.garfend.components
import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.Visibility
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowUp
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import kotlinx.browser.document
import kotlinx.browser.window
import org.example.garfend.components.LocalLanguage
import org.example.garfend.models.Language
import org.example.garfend.models.Theme
import org.example.garfend.styles.BackToTopButtonStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.w3c.dom.SMOOTH
import org.w3c.dom.ScrollToOptions

@Composable
fun backToTopButton() {
    val breakpoint = rememberBreakpoint()
    val language = LocalLanguage.current
    var scroll: Double? by remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        window.addEventListener(type = "scroll", callback = {
            scroll = document.documentElement?.scrollTop
        })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .position(Position.Fixed)
            .zIndex(1)
            .styleModifier {
                property("pointer-events", "none")
            },
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        Box(
            modifier = BackToTopButtonStyle.toModifier()
                .size(50.px)
                .visibility(
                    if (scroll != null && scroll!! > 400.0) Visibility.Visible
                    else Visibility.Hidden
                )
                .borderRadius(20.percent)
                .margin(
                    left = if (language == Language.ARABIC) {
                        if (breakpoint <= Breakpoint.SM) 30.px else 40.px
                    } else 0.px,
                    right = if (language == Language.ARABIC) 0.px else {
                        if (breakpoint <= Breakpoint.SM) 30.px else 40.px
                    },
                    bottom = if(breakpoint <= Breakpoint.SM) 30.px else 40.px
                )
                .backgroundColor(Theme.DarkRed.rgb)
                .cursor(Cursor.Pointer)
                .onClick {
                    window.scrollTo(
                        ScrollToOptions(
                            top = 0.0,
                            behavior = org.w3c.dom.ScrollBehavior.SMOOTH
                        )
                    )
                }
                .styleModifier {
                    property("pointer-events", "auto")
                },
            contentAlignment = Alignment.Center
        ) {
            FaArrowUp(
                modifier = Modifier.color(Theme.Primary.rgb),
                size = IconSize.LG
            )
        }
    }
}