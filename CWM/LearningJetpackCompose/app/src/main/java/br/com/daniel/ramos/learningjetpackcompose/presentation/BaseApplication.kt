package br.com.daniel.ramos.learningjetpackcompose.presentation

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application() {
    val isDark = mutableStateOf(false)

    fun toogleLightTheme() {
        isDark.value = !isDark.value
    }
}