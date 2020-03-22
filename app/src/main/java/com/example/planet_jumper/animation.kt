package com.example.planet_jumper

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation

// code tiré du web pour animer le texte, voir section "sources" au haut de la page
fun View.blink(
    times: Int = Animation.INFINITE,
    duration: Long = 700L,
    offset: Long = 20L,
    minAlpha: Float = 0.0f,
    maxAlpha: Float = 1.0f,
    repeatMode: Int = Animation.REVERSE
) {
    startAnimation(AlphaAnimation(minAlpha, maxAlpha).also {
        it.duration = duration
        it.startOffset = offset
        it.repeatMode = repeatMode
        it.repeatCount = times
    })
}