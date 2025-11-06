package org.example.garfend

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.style.toModifier
import org.example.garfend.components.LocalizationProvider
import org.example.garfend.components.rememberLanguage
import org.jetbrains.compose.web.css.*

@InitSilk
fun updateTheme(ctx: InitSilkContext) {

}
@App
@Composable
fun MyApp(content: @Composable () -> Unit) {
    val languageState = rememberLanguage()

    SilkApp {
        LocalizationProvider() {
            Surface(SmoothColorStyle.toModifier().minHeight(100.vh)) {
                content()
            }
        }

    }
}
