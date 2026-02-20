package org.example.yassenramadan1.util

import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.styleModifier
import org.jetbrains.compose.web.css.CSSNumeric

/**
 * RTL-aware padding utilities using CSS logical properties
 * These automatically flip in RTL mode without manual intervention
 */

/**
 * Sets padding using logical properties (start/end instead of left/right)
 * - paddingInlineStart: padding-inline-start (left in LTR, right in RTL)
 * - paddingInlineEnd: padding-inline-end (right in LTR, left in RTL)
 */
fun Modifier.paddingInline(start: CSSNumeric? = null, end: CSSNumeric? = null) = styleModifier {
    start?.let { property("padding-inline-start", it.toString()) }
    end?.let { property("padding-inline-end", it.toString()) }
}

/**
 * Sets margin using logical properties (start/end instead of left/right)
 */
fun Modifier.marginInline(start: CSSNumeric? = null, end: CSSNumeric? = null) = styleModifier {
    start?.let { property("margin-inline-start", it.toString()) }
    end?.let { property("margin-inline-end", it.toString()) }
}

/**
 * Sets both padding-inline-start and padding-inline-end to the same value
 */
fun Modifier.paddingInline(value: CSSNumeric) = paddingInline(start = value, end = value)

/**
 * Sets both margin-inline-start and margin-inline-end to the same value
 */
fun Modifier.marginInline(value: CSSNumeric) = marginInline(start = value, end = value)

/**
 * Full padding with logical inline properties
 * block: top/bottom (same in LTR and RTL)
 * inline: start/end (flips in RTL)
 */
fun Modifier.paddingLogical(
    block: CSSNumeric? = null,
    inline: CSSNumeric? = null,
    blockStart: CSSNumeric? = null,
    blockEnd: CSSNumeric? = null,
    inlineStart: CSSNumeric? = null,
    inlineEnd: CSSNumeric? = null
) = styleModifier {
    block?.let {
        property("padding-block-start", it.toString())
        property("padding-block-end", it.toString())
    }
    inline?.let {
        property("padding-inline-start", it.toString())
        property("padding-inline-end", it.toString())
    }
    blockStart?.let { property("padding-block-start", it.toString()) }
    blockEnd?.let { property("padding-block-end", it.toString()) }
    inlineStart?.let { property("padding-inline-start", it.toString()) }
    inlineEnd?.let { property("padding-inline-end", it.toString()) }
}

/**
 * Full margin with logical inline properties
 */
fun Modifier.marginLogical(
    block: CSSNumeric? = null,
    inline: CSSNumeric? = null,
    blockStart: CSSNumeric? = null,
    blockEnd: CSSNumeric? = null,
    inlineStart: CSSNumeric? = null,
    inlineEnd: CSSNumeric? = null
) = styleModifier {
    block?.let {
        property("margin-block-start", it.toString())
        property("margin-block-end", it.toString())
    }
    inline?.let {
        property("margin-inline-start", it.toString())
        property("margin-inline-end", it.toString())
    }
    blockStart?.let { property("margin-block-start", it.toString()) }
    blockEnd?.let { property("margin-block-end", it.toString()) }
    inlineStart?.let { property("margin-inline-start", it.toString()) }
    inlineEnd?.let { property("margin-inline-end", it.toString()) }
}
