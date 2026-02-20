package org.example.yassenramadan1.util

import org.w3c.dom.HTMLElement

@JsModule("lottie-web")
@JsNonModule
external object Lottie {
    fun loadAnimation(params: AnimationConfigParams): AnimationItem
}

external interface AnimationConfigParams {
    var container: HTMLElement?
    var renderer: String?
    var loop: Boolean?
    var autoplay: Boolean?
    var path: String?
    var animationData: dynamic
}

external interface AnimationItem {
    fun play()
    fun pause()
    fun stop()
    fun destroy()
    fun setSpeed(speed: Number)
    fun setDirection(direction: Number)
    fun goToAndStop(value: Number, isFrame: Boolean = definedExternally)
    fun goToAndPlay(value: Number, isFrame: Boolean = definedExternally)
    fun addEventListener(eventName: String, callback: () -> Unit)
    fun removeEventListener(eventName: String, callback: () -> Unit)
    fun playSegments(segments: Array<Number>, forceFlag: Boolean = definedExternally)
    val totalFrames: Number
    var loop: Boolean
    var currentFrame: Number
}

fun animationConfig(block: AnimationConfigParams.() -> Unit): AnimationConfigParams {
    return (js("{}") as AnimationConfigParams).apply(block)
}