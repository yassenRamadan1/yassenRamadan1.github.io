package org.example.garfend.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
fun RamadanStarChain() {
    // Create a horizontal chain of stars with alternating up/down positions
    Box(
        modifier = Modifier
            .position(Position.Fixed)
            .top(0.px)
            .left(0.px)
            .width(100.percent)
            .zIndex(100)
            .styleModifier {
                property("pointer-events", "none")
                property("animation", "fadeIn 1s ease-in-out")
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(leftRight = 50.px),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Top
        ) {
            repeat(15) { index ->
                Box(
                    modifier = Modifier
                        .styleModifier {
                            // Alternate positioning: even indices up, odd indices down
                            property("transform", if (index % 2 == 0) "translateY(10px)" else "translateY(30px)")
                        }
                ) {
                    RamadanStar(
                        modifier = Modifier.styleModifier {
                            property("animation-delay", "${index * 0.2}s")
                        },
                        size = if (index % 3 == 0) 25.px else if (index % 2 == 0) 20.px else 18.px
                    )
                }
            }
        }
    }
}

@Composable
fun RamadanHeaderDecorations() {
    val breakpoint = rememberBreakpoint()

    // Only show decorations on larger screens
    if (breakpoint > Breakpoint.MD) {
        // Star chain above header


        // Left side: Banner
        Box(
            modifier = Modifier
                .position(Position.Fixed)
                .left(20.px)
                .zIndex(50)
                .styleModifier {
                    property("animation", "fadeIn 1s ease-in-out")
                }
        ) {
            Column(
                modifier = Modifier.gap(20.px),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Lottie animation instead of static banner
                LottieAnimation(
                    path = "/ramadan/LIghts.json",
                    modifier = Modifier
                        .position(Position.Fixed)
                        .top(0.px)
                        .styleModifier {
                            property("left", "10px")
                            property("top", "-160px")
                        }
                        .zIndex(10),
                    width = 400.px,
                    height = 600.px,
                    loop = true,
                    autoplay = true,
                    yoyo = true
                )

                // Original banner code
                /*
                Image(
                    src = Res.Image.ramadanBanner,
                    modifier = Modifier
                        .position(Position.Fixed)
                        .styleModifier {
                            property("left", "-100px")
                        }
                        .height(600.px)
                        .zIndex(10)
                )
                */
                RamadanStarChain()
            }
        }

        // Right side: Hanging Decoration
        Box(
            modifier = Modifier
                .position(Position.Fixed)
                .top(0.px)
                .right(20.px)
                .zIndex(50)
                .styleModifier {
                    property("animation", "fadeIn 1s ease-in-out")
                    property("animation-delay", "0.3s")
                }
        ) {
            RamadanHangingDecoration(size = 180.px)
        }
    }
}