package org.example.yassenramadan1.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.example.yassenramadan1.models.Portfolio
import org.example.yassenramadan1.models.Theme
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun PortfolioDetailHeader(portfolio: Portfolio) {
    val breakpoint = rememberBreakpoint()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 30.px)
            .gap(if (breakpoint >= Breakpoint.MD) 24.px else 16.px),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // App Icon
        Image(
            modifier = Modifier
                .size(if (breakpoint >= Breakpoint.MD) 120.px else 80.px)
                .borderRadius(22.px)
                .objectFit(ObjectFit.Cover)
                .styleModifier {
                    property("border", "1px solid rgba(255, 255, 255, 0.08)")
                    property("box-shadow", "0 4px 12px rgba(0, 0, 0, 0.15)")
                },
            src = portfolio.image,
            alt = stringResource("portfolio_image_alt")
        )

        // App Info
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            // App Name
            H1(
                attrs = Modifier
                    .margin(topBottom = 0.px)
                    .fontSize(if (breakpoint >= Breakpoint.MD) 32.px else 24.px)
                    .fontWeight(FontWeight.Bold)
                    .color(Theme.Primary.rgb)
                    .fontFamily("Space Grotesk", "sans-serif")
                    .toAttrs()
            ) {
                Text(portfolio.title)
            }

            // Category
            P(
                attrs = Modifier
                    .margin(topBottom = 6.px)
                    .fontSize(16.px)
                    .color(Theme.Secondary.rgb)
                    .opacity(70.percent)
                    .fontFamily("Space Grotesk", "sans-serif")
                    .toAttrs()
            ) {
                Text(portfolio.description.title)
            }

            // Developer
            P(
                attrs = Modifier
                    .margin(topBottom = 0.px)
                    .fontSize(14.px)
                    .color(Theme.Secondary.rgb)
                    .opacity(60.percent)
                    .fontFamily("Space Grotesk", "sans-serif")
                    .toAttrs()
            ) {
                Text(portfolio.developer)
            }
        }
    }
}
