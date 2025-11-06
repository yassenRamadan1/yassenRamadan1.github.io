package org.example.garfend.sections


import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.garfend.components.contactForm
import org.example.garfend.components.sectionTitle
import org.example.garfend.components.socialBar
import org.example.garfend.models.Section
import org.example.garfend.models.Theme
import org.example.garfend.util.Constants.SECTION_WIDTH
import org.example.garfend.util.observeViewportEntered
import org.jetbrains.compose.web.css.deg
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
fun contactSection() {
    Box(
        modifier = Modifier
            .id(Section.Contact.id)
            .maxWidth(SECTION_WIDTH.px)
            .padding(topBottom = 100.px)
            .backgroundColor(Theme.LightGrayBg.rgb),
        contentAlignment = Alignment.Center
    ) {
        contactContent()
    }
}

@Composable
fun contactContent() {
    val breakpoint = rememberBreakpoint()
    val scope = rememberCoroutineScope()
    var animatedRotation by remember { mutableStateOf(0.deg) }

    observeViewportEntered(
        sectionId = Section.Contact.id,
        distanceFromTop = 500.0,
        onViewportEntered = {
            animatedRotation = 10.deg
            scope.launch {
                delay(500)
                animatedRotation = 0.deg
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxWidth(
                if (breakpoint >= Breakpoint.MD) 100.percent
                else 90.percent
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        sectionTitle(
            modifier = Modifier
                .fillMaxWidth()
                .margin(bottom = 25.px)
                .transform { rotate(animatedRotation) }
                .transition(Transition.of(property = "transform", duration = 500.ms)),
            section = Section.Contact,
            alignment = Alignment.CenterHorizontally
        )
        socialBar(title = true, breakpoint = breakpoint)
        contactForm(breakpoint = breakpoint)
    }
}