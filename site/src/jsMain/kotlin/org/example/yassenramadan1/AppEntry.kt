package org.example.yassenramadan1

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.style.toModifier
import org.example.yassenramadan1.components.LocalizationProvider
import org.example.yassenramadan1.components.rememberLanguage
import org.jetbrains.compose.web.css.*
import kotlinx.browser.document
import org.w3c.dom.HTMLLinkElement

@InitSilk
fun updateTheme(ctx: InitSilkContext) {

}
@App
@Composable
fun MyApp(content: @Composable () -> Unit) {
    EnsureGlobalFonts()
    val languageState = rememberLanguage()

    SilkApp {
        LocalizationProvider() {
            Surface(SmoothColorStyle.toModifier().minHeight(100.vh)) {
                content()
            }
        }

    }
}

@Composable
private fun EnsureGlobalFonts() {
    DisposableEffect(Unit) {
        val head = document.head
        val existing = head?.querySelector("#global-fonts") as? HTMLLinkElement
        if (existing == null) {
            val link = document.createElement("link") as HTMLLinkElement
            link.id = "global-fonts"
            link.rel = "stylesheet"
            link.href =
                "https://fonts.googleapis.com/css2?family=Cairo:wght@400;500;600;700&family=Space+Grotesk:wght@400;500;600;700&display=swap"
            head?.appendChild(link)
        }
        onDispose { }
    }
}
