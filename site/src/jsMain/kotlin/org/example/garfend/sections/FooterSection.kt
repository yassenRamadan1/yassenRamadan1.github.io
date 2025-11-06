package org.example.garfend.sections


import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.example.garfend.components.socialBar
import org.example.garfend.components.stringResource
import org.example.garfend.models.Section
import org.example.garfend.models.Theme
import org.example.garfend.styles.NavigationItemStyle
import org.example.garfend.styles.LinkOverrideStyle
import org.example.garfend.util.Constants.FONT_FAMILY
import org.example.garfend.util.Constants.SECTION_WIDTH
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
fun footerSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .maxWidth(SECTION_WIDTH.px)
            .padding(topBottom = 100.px)
            .backgroundColor(Theme.LightGrayBg.rgb),
        contentAlignment = Alignment.Center
    ) {
        footerContent()
    }
}

@Composable
fun footerContent() {
    val breakpoint = rememberBreakpoint()
    Column(
        modifier = Modifier
            .fillMaxWidth(
                if (breakpoint >= Breakpoint.MD) 100.percent
                else 90.percent
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (breakpoint > Breakpoint.SM) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                footerMenu()
            }
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                footerMenu(row = false)
            }
        }
        socialBar(row = true, breakpoint = breakpoint)
    }
}

@Composable
fun footerMenu(row: Boolean = true) {
    Section.entries.toTypedArray().take(6).forEach { section ->
        Link(
            modifier = NavigationItemStyle.toModifier()
                .then(LinkOverrideStyle.toModifier())
                .fontFamily(FONT_FAMILY)
                .padding(
                    right = if (row) 20.px else 0.px,
                    bottom = if (row) 0.px else 20.px
                )
                .fontSize(12.px)
                .fontWeight(FontWeight.Normal)
                .textDecorationLine(TextDecorationLine.None),
            path = section.path,
            text = stringResource(section.titleKey)
        )
    }
}
