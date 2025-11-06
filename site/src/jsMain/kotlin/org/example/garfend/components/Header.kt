package org.example.garfend.components

import androidx.compose.runtime.Composable
import org.example.garfend.util.Constants.FONT_FAMILY
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.icons.fa.FaBars
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.example.garfend.models.Section
import org.example.garfend.models.Theme
import org.example.garfend.styles.NavigationItemStyle
import org.example.garfend.styles.LinkOverrideStyle
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.ui.toAttrs
import org.example.garfend.models.Language
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Text

@Composable
fun LanguageSwitchButton(
    modifier: Modifier = Modifier,
    showBackground: Boolean = true
) {
    val languageState = LocalLanguageState.current

    Button(
        attrs = modifier
            .padding(left = if (showBackground) 20.px else 0.px)
            .color(Colors.White)
            .cursor(Cursor.Pointer)
            .fontFamily(FONT_FAMILY)
            .border(width = 0.px, color = Colors.Transparent)
            .backgroundColor(color = Colors.Transparent)
            .fontSize(14.px)
            .onClick {
                val newLanguage = if (languageState.value == Language.ENGLISH) {
                    Language.ARABIC
                } else {
                    Language.ENGLISH
                }
                languageState.value = newLanguage
                saveLanguage(newLanguage)
            }
            .toAttrs()
    ) {
        Text(languageState.value.displayName)
    }
}

@Composable
fun header(onMenuClicked: () -> Unit) {
    val breakpoint = rememberBreakpoint()
    Row(
        modifier = Modifier
            .fillMaxWidth(if (breakpoint > Breakpoint.MD) 80.percent else 90.percent)
            .margin(topBottom = 50.px),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        leftSide(
            breakpoint = breakpoint,
            onMenuClicked = onMenuClicked
        )
        if (breakpoint > Breakpoint.MD) {
            rightSide()
        }
    }
}

@Composable
fun leftSide(
    breakpoint: Breakpoint,
    onMenuClicked: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (breakpoint <= Breakpoint.MD) {
            FaBars(
                modifier = Modifier
                    .margin(right = 15.px)
                    .color(Colors.White)
                    .onClick {
                        onMenuClicked()
                    },
                size = IconSize.XL
            )
        }

    }
}

@Composable
fun rightSide() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .borderRadius(r = 50.px)
            .backgroundColor(Theme.LightGrayBg.rgb)
            .padding(all = 20.px),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        LanguageSwitchButton(showBackground = true)
        Row(
            horizontalArrangement = Arrangement.End,
        ) {
            Section.entries.toTypedArray().take(6).forEach { section ->
                Link(
                    modifier = NavigationItemStyle.toModifier()
                        .then(LinkOverrideStyle.toModifier())
                        .padding(right = 30.px)
                        .fontFamily(FONT_FAMILY)
                        .fontSize(18.px)
                        .fontWeight(FontWeight.Normal)
                        .textDecorationLine(TextDecorationLine.None),
                    path = section.path,
                    text = stringResource(section.titleKey)
                )
            }
        }


    }
}