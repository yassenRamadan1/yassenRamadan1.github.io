package org.example.yassenramadan1.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.example.yassenramadan1.components.experienceCard
import org.example.yassenramadan1.components.sectionTitle
import org.example.yassenramadan1.models.Experience
import org.example.yassenramadan1.models.Section
import org.example.yassenramadan1.models.Theme
import org.example.yassenramadan1.util.Constants.SECTION_WIDTH
import org.example.yassenramadan1.util.observeViewportEntered
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
fun experienceSection() {
    Box(
        modifier = Modifier
            .id(Section.Experience.id)
            .maxWidth(SECTION_WIDTH.px)

            .padding(topBottom = 100.px)
            .backgroundColor(Theme.LightGrayBg.rgb),
        contentAlignment = Alignment.Center
    ) {

        experienceContent()
    }
}

@Composable
fun experienceContent() {
    val breakpoint = rememberBreakpoint()
    var animatedMargin by remember { mutableStateOf(200.px) }

    observeViewportEntered(
        sectionId = Section.Experience.id,
        distanceFromTop = 500.0,
        onViewportEntered = {
            animatedMargin = 50.px
        }
    )

    Column(
        modifier = Modifier
            .fillMaxWidth(
                if (breakpoint >= Breakpoint.MD) 100.percent
                else 90.percent
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        sectionTitle(
            modifier = Modifier
                .fillMaxWidth(
                    if (breakpoint >= Breakpoint.MD) 60.percent
                    else 90.percent
                )
                .margin(bottom = 25.px),
            section = Section.Experience
        )
        Experience.entries.forEach { experience ->
            experienceCard(
                breakpoint = breakpoint,
                active = experience.active,
                experience = experience,
                animatedMargin = animatedMargin
            )
        }
    }
}
