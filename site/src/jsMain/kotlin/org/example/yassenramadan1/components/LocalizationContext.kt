package org.example.yassenramadan1.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import com.varabyte.kobweb.silk.components.forms.Button
import org.example.yassenramadan1.models.Language
import org.example.yassenramadan1.util.Strings

// In LocalizationContext.kt
val LocalLanguageState = compositionLocalOf<MutableState<Language>> {
    error("Language state not provided")
}

val LocalLanguage = compositionLocalOf { Language.ENGLISH }

@Composable
fun LocalizationProvider(content: @Composable () -> Unit) {
    val languageState = rememberLanguage()

    CompositionLocalProvider(
        LocalLanguage provides languageState.value,
        LocalLanguageState provides languageState
    ) {
        content()
    }
}

@Composable
fun stringResource(key: String): String {
    val language = LocalLanguage.current
    return Strings.get(key, language)
}
