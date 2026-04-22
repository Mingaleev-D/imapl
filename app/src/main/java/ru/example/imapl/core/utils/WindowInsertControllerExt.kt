package ru.example.imapl.core.utils

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

fun WindowInsetsControllerCompat.toggleStatusBars(show: Boolean) {
    if (show) show(WindowInsetsCompat.Type.statusBars())
    else hide(WindowInsetsCompat.Type.statusBars())
}

@SuppressLint("ContextCastToActivity")
@Composable
fun rememberWindowsInsetsController(): WindowInsetsControllerCompat {
    val window = with(LocalContext.current as Activity) { return@with window }
    return remember {
        WindowCompat.getInsetsController(window, window.decorView)
    }
}