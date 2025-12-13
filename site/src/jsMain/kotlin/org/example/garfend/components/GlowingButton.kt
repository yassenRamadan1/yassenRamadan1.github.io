package org.example.garfend.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.onMouseDown
import com.varabyte.kobweb.compose.ui.modifiers.onMouseEnter
import com.varabyte.kobweb.compose.ui.modifiers.onMouseLeave
import com.varabyte.kobweb.compose.ui.modifiers.onMouseUp
import com.varabyte.kobweb.compose.ui.modifiers.transform
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import org.example.garfend.styles.GlowingButtonStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Text

@Composable
fun GlowingButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    var hover by remember { mutableStateOf(false) }
    var active by remember { mutableStateOf(false) }

    val stateModifier = when {
        active -> Modifier
            .transform { translateY(2.px) }
        hover -> Modifier
            .transform { translateY((-2).px) }
        else -> Modifier
    }

    Button(
        attrs = GlowingButtonStyle.toModifier()
            .then(modifier)
            .then(stateModifier)
            .cursor(Cursor.Pointer)
            .onMouseEnter { hover = true }
            .onMouseLeave { hover = false; active = false }
            .onMouseDown { active = true }
            .onMouseUp { active = false }
            .onClick { onClick() }
            .toAttrs()
    ) {
        Text(text)
    }
}
