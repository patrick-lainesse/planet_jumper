/*
    Auteur: Patrick Lainesse
    Matricule: 740302
    Dans le cadre du cours: IFT1155, Hiver 2020

    Sources:
    Code pour le clignotement: https://stackoverflow.com/questions/47293269/text-blinking-in-android-with-most-concise-kotlin
    Arrière-plan: stocksnap.io - https://pixabay.com/illustrations/board-chalk-strokes-tic-tac-toe-2097446/
    Police 8-bit: Joiro Hatgaya - https://www.dafont.com/8bit-wonder.font
    Pixelisation des images: Gimp et imgonline: https://www.imgonline.com.ua/eng/8bit-picture.php

    Idées:
    - Écran paramètres pour changer les unités de mensure
    - Phase décollage d'une planète coûte beaucoup de ressources, et seules certaines planètes "boss" offrent un bonus valide
    - Quand on sélectionne le vaisseau, la boîte va au bas de l'écran et s'agrandit pour faire
    apparaître le card view, la photo passe de noir et blanc à couleurs
    - Travailler sur card_vaisseau pour les custom view
 */

package com.example.planet_jumper

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Accueil : AppCompatActivity() {

    lateinit var debuterTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.accueil_layout)

        debuterTv = findViewById<TextView>(R.id.lancer_partie)


        clignoter()
        setListener()
    }

    private fun setListener() {

        val intent = Intent(this@Accueil, ChoixVaisseau::class.java)

        debuterTv.setOnClickListener {startActivity(intent)
            //Toast.makeText(applicationContext, "cliqué", Toast.LENGTH_SHORT).show()
        }
    }

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

    private fun clignoter() {
        debuterTv.blink()
    }
}