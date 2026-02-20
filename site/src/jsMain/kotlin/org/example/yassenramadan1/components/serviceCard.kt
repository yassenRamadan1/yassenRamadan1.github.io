package org.example.yassenramadan1.components
import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.style.toModifier
import org.example.yassenramadan1.models.Service
import org.example.yassenramadan1.styles.ServiceCardStyle
import org.example.yassenramadan1.util.Constants.FONT_FAMILY
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgb
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Composable
fun serviceCard(service: Service) {
    Column(
        modifier = ServiceCardStyle.toModifier()
            .maxWidth(300.px)
            .margin(all = 20.px)
            .padding(all = 30.px)
    ) {
        Box(
            modifier = Modifier
                .id("iconBox")
                .margin(bottom = 20.px)
        ) {
            Image(
                modifier = Modifier.size(56.px),
                src = service.icon,
                alt = stringResource(service.imageDescKey)
            )
        }
        P(
            attrs = Modifier
                .fillMaxWidth()
                .margin(top = 0.px, bottom = 10.px)
                .fontFamily(*FONT_FAMILY)
                .fontSize(18.px)
                .fontWeight(FontWeight.Bold)
                .color(Colors.White)
                .toAttrs()
        ) {
            Text(stringResource(service.titleKey))
        }
        P(
            attrs = Modifier
                .fillMaxWidth()
                .margin(top = 0.px, bottom = 0.px)
                .fontFamily(*FONT_FAMILY)
                .fontSize(14.px)
                .fontWeight(FontWeight.Normal)
                .color(rgb(176, 181, 197))
                .toAttrs()
        ) {
            Text(stringResource(service.descriptionKey))
        }
    }
}
