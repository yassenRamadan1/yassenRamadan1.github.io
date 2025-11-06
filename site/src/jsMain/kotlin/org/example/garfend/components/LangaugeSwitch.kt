package org.example.garfend.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlinx.browser.localStorage
import org.example.garfend.models.Language

@Composable
fun rememberLanguage(): MutableState<Language> {
    return remember {
        val stored = localStorage.getItem("language")
        val initial = if (stored == "ar") Language.ARABIC else Language.ENGLISH
        mutableStateOf(initial)
    }
}

fun saveLanguage(language: Language) {
    localStorage.setItem("language", language.code)
}
