package org.example.yassenramadan1.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.Page
import org.example.yassenramadan1.components.LocalLanguage
import org.example.yassenramadan1.components.RamadanFooterDecorations
import org.example.yassenramadan1.components.RamadanHeaderDecorations
import org.example.yassenramadan1.components.backToTopButton
import org.example.yassenramadan1.components.overflowMenu
import org.example.yassenramadan1.components.rememberRamadanTheme
import org.example.yassenramadan1.models.Theme
import org.example.yassenramadan1.sections.*



@Page
@Composable
fun homePage() {
    val language = LocalLanguage.current
    var menuOpened by remember { mutableStateOf(false) }
    val ramadanThemeEnabled = rememberRamadanTheme()

    // Force recomposition when language changes using key()
    key(language) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.LightGrayBg.rgb)
                .styleModifier {
                    property("direction", if (language.isRTL) "rtl" else "ltr")
                }
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                mainSection(
                    onMenuClicked = { menuOpened = true },
                    isMenuOpen = menuOpened
                )
                aboutSection()
                serviceSection()
                portfolioSection()
                experienceSection()
                contactSection()
                footerSection()
            }
            backToTopButton()
            if (menuOpened) {
                overflowMenu(onMenuClosed = { menuOpened = false })
            }

            // Ramadan decorations
            if (ramadanThemeEnabled.value) {
                RamadanHeaderDecorations()
                //RamadanFooterDecorations() // Fixed positioned - overlays above footer
            }
        }
    }
}
