package org.example.yassenramadan1.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.navigation.OpenLinkStrategy
import com.varabyte.kobweb.silk.style.toModifier
import org.example.yassenramadan1.styles.SocialIconButtonStyle
import org.jetbrains.compose.web.css.px

@Composable
fun SocialIconButton(
    href: String,
    external: Boolean = true,
    content: @Composable () -> Unit
) {
    val linkModifier = SocialIconButtonStyle.toModifier()
        .size(46.px)

    if (external) {
        Link(
            path = href,
            openExternalLinksStrategy = OpenLinkStrategy.IN_NEW_TAB,
            modifier = linkModifier,
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { content() }
        }
    } else {
        Link(
            path = href,
            modifier = linkModifier,
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { content() }
        }
    }
}
