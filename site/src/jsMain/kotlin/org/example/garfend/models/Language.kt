package org.example.garfend.models

enum class Language(val code: String, val displayName: String, val isRTL: Boolean) {
    ENGLISH("en", "English", false),
    ARABIC("ar", "العربية", true)
}
