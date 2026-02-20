package org.example.yassenramadan1.styles

import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import kotlinx.browser.document
import org.w3c.dom.HTMLStyleElement

@InitSilk
fun initRamadanAnimations(ctx: InitSilkContext) {
    // Add custom CSS animations for Ramadan decorations
    val style = (document.createElement("style") as HTMLStyleElement).apply {
        textContent = """
            @keyframes float {
                0%, 100% {
                    transform: translateY(0px);
                }
                50% {
                    transform: translateY(-20px);
                }
            }

            @keyframes glow {
                0%, 100% {
                    filter: drop-shadow(0 0 5px rgba(255, 215, 0, 0.5));
                }
                50% {
                    filter: drop-shadow(0 0 20px rgba(255, 215, 0, 0.9));
                }
            }

            @keyframes twinkle {
                0%, 100% {
                    opacity: 0.5;
                    transform: scale(0.8);
                }
                50% {
                    opacity: 1;
                    transform: scale(1.2);
                }
            }

            @keyframes blink {
                0%, 100% {
                    opacity: 0.3;
                }
                50% {
                    opacity: 1;
                }
            }

            @keyframes swing {
                0%, 100% {
                    transform: rotate(-5deg);
                }
                50% {
                    transform: rotate(5deg);
                }
            }

            @keyframes fadeIn {
                from {
                    opacity: 0;
                    transform: translateY(-20px);
                }
                to {
                    opacity: 1;
                    transform: translateY(0);
                }
            }
        """.trimIndent()
    }
    document.head?.appendChild(style)
}
