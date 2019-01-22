package com.dudencovgmail.splashes.util

import android.graphics.Color
import kotlin.random.Random

fun Any.generateRandomColor(): Int {
    val red = Random.nextInt(0, 255)
    val green = Random.nextInt(0, 255)
    val blue = Random.nextInt(0, 255)
    return Color.rgb(red, green, blue)
}