/*
    Auteur: Patrick Lainesse
    Matricule: 740302
    Dans le cadre du cours: IFT1155, Hiver 2020

    Sources:
    Code pour le clignotement: https://stackoverflow.com/questions/47293269/text-blinking-in-android-with-most-concise-kotlin
    Arrière-plan: stocksnap.io - https://pixabay.com/illustrations/board-chalk-strokes-tic-tac-toe-2097446/
    Police 8-bit: Joiro Hatgaya - https://www.dafont.com/8bit-wonder.font
    Pixelisation des images: Gimp et imgonline: https://www.imgonline.com.ua/eng/8bit-picture.php
 */

package com.example.planet_jumper

import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Accueil : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.accueil_layout)

        clignoter()
        // setListener
    }

    // voir section "sources" au haut de la page
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

    private fun clignoter() {
        val debuterTv = findViewById<TextView>(R.id.lancer_partie)
        debuterTv.blink()
    }
}