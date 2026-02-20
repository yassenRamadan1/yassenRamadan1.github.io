package org.example.garfend.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import org.example.garfend.util.AnimationItem
import org.example.garfend.util.Lottie
import org.example.garfend.util.animationConfig
import org.jetbrains.compose.web.css.CSSSizeValue
import org.jetbrains.compose.web.css.CSSUnit
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLElement

@Composable
fun LottieAnimation(
    path: String,
    modifier: Modifier = Modifier,
    loop: Boolean = true,
    autoplay: Boolean = true,
    speed: Double = 1.0,
    width: CSSSizeValue<CSSUnit.px> = 300.px,
    height: CSSSizeValue<CSSUnit.px> = 300.px,
    yoyo: Boolean = true
) {
    var animationItem by remember { mutableStateOf<AnimationItem?>(null) }
    var currentDirection by remember { mutableStateOf(1) }

    DisposableEffect(path) {
        onDispose {
            animationItem?.destroy()
        }
    }

    Div(
        attrs = modifier
            .width(width)
            .height(height)
            .toAttrs {
                ref { element ->
                    val htmlElement = element as HTMLElement

                    // Load the Lottie animation
                    val config = animationConfig {
                        this.container = htmlElement
                        this.renderer = "svg"
                        this.loop = false
                        this.autoplay = autoplay
                        this.path = path
                    }

                    val animation = Lottie.loadAnimation(config)
                    animation.setSpeed(speed)
                    animationItem = animation

                    // Implement yoyo (ping-pong) effect
                    if (yoyo) {
                        val completeHandler: () -> Unit = {

                            currentDirection = -currentDirection
                            animation.setDirection(currentDirection)
                            animation.play()
                        }
                        animation.addEventListener("complete", completeHandler)
                    }

                    onDispose {
                        animation.destroy()
                    }
                }
            }
    )
}