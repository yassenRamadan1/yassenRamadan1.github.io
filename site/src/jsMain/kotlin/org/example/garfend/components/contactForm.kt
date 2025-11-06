package org.example.garfend.components
import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.attrsModifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import org.example.garfend.models.Theme
import org.example.garfend.styles.InputStyle
import org.example.garfend.styles.MainButtonStyle
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.*

@Composable
fun contactForm(breakpoint: Breakpoint) {
    // Extract all string resources at the composable level
    val formLabelName = stringResource("form_label_name")
    val formPlaceholderName = stringResource("form_placeholder_name")
    val formLabelEmail = stringResource("form_label_email")
    val formPlaceholderEmail = stringResource("form_placeholder_email")
    val formLabelMessage = stringResource("form_label_message")
    val formPlaceholderMessage = stringResource("form_placeholder_message")
    val formButtonSubmit = stringResource("form_button_submit")

    Form(
        action = "https://formspree.io/f/xbljkgkl",
        attrs = Modifier
            .attrsModifier {
                attr("method", "POST")
            }
            .toAttrs()
    ) {
        Label(
            attrs = Modifier
                .classNames("form-label")
                .color(Theme.Primary.rgb)
                .toAttrs(),
            forId = "inputName"
        ) {
            Text(formLabelName)
        }
        Input(
            type = InputType.Text,
            attrs = InputStyle.toModifier()
                .id("inputName")
                .classNames("form-control")
                .margin(bottom = 10.px)
                .width(
                    if (breakpoint >= Breakpoint.MD) 500.px
                    else 250.px
                )
                .border(0.px)
                .boxShadow(0.px, 0.px, 0.px, 0.px, null)
                .attrsModifier {
                    attr("placeholder", formPlaceholderName)
                    attr("name", "name")
                    attr("required", "true")
                }
                .toAttrs()

        )
        Label(
            attrs = Modifier
                .classNames("form-label")
                .color(Theme.Primary.rgb)
                .toAttrs(),
            forId = "inputEmail"
        ) {
            Text(formLabelEmail)
        }
        Input(
            type = InputType.Email,
            attrs = InputStyle.toModifier()
                .id("inputEmail")
                .classNames("form-control")
                .margin(bottom = 10.px)
                .width(
                    if (breakpoint >= Breakpoint.MD) 500.px
                    else 250.px
                )
                .border(0.px)
                .boxShadow(0.px, 0.px, 0.px, 0.px, null)
                .attrsModifier {
                    attr("placeholder", formPlaceholderEmail)

                    attr("name", "email")
                    attr("required", "true")
                }
                .toAttrs()
        )
        Label(
            attrs = Modifier
                .classNames("form-label")
                .color(Theme.Primary.rgb)

                .toAttrs(),
            forId = "inputMessage"
        ) {
            Text(formLabelMessage)
        }
        TextArea (
            attrs = InputStyle.toModifier()
                .id("inputMessage")
                .classNames("form-control")
                .height(150.px)
                .margin(bottom = 20.px)
                .width(
                    if (breakpoint >= Breakpoint.MD) 500.px
                    else 250.px
                )
                .border(0.px)
                .boxShadow(0.px, 0.px, 0.px, 0.px, null)
                .attrsModifier {
                    attr("placeholder", formPlaceholderMessage)
                    attr("name", "message")
                    attr("required", "true")
                }
                .toAttrs()
        )
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                attrs = MainButtonStyle.toModifier()
                    .height(40.px)
                    .border(width = 0.px)
                    .borderRadius(r = 5.px)
                    .backgroundColor(Theme.DarkRed.rgb)
                    .color(Colors.White)
                    .cursor(Cursor.Pointer)
                    .toAttrs()
            ) {
                Text(formButtonSubmit)
            }
        }
    }
}