/*
    Auteur: Patrick Lainesse
    Matricule: 740302
    Dans le cadre du cours: IFT1155, Hiver 2020

    Sources:
    Code pour le clignotement: https://stackoverflow.com/questions/47293269/text-blinking-in-android-with-most-concise-kotlin
    Arrière-plan: stocksnap.io - https://pixabay.com/illustrations/board-chalk-strokes-tic-tac-toe-2097446/
    Police 8-bit: Joiro Hatgaya - https://www.dafont.com/8bit-wonder.font
    Pixelisation des images: Gimp et imgonline: https://www.imgonline.com.ua/eng/8bit-picture.php
    <a href="https://www.vecteezy.com/free-vector/spaceship-icon">Spaceship Icon Vectors by Vecteezy</a>

    Idées:
    - Écran paramètres pour changer les unités de mensure
    - Phase décollage d'une planète coûte beaucoup de ressources, et seules certaines planètes "boss" offrent un bonus valide
    - Quand on sélectionne le vaisseau, la boîte va au bas de l'écran et s'agrandit pour faire
    apparaître le card view, la photo passe de noir et blanc à couleurs
    - Travailler sur card_vaisseau_item pour les custom view
 */

package com.example.planet_jumper

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Accueil : AppCompatActivity() {

    private lateinit var debuterTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.accueil_layout)

        debuterTv = findViewById(R.id.lancer_partie)


        clignoter()
        setListener()
    }

    private fun setListener() {

        val intent = Intent(this@Accueil, ChoixVaisseau::class.java)

        debuterTv.setOnClickListener {startActivity(intent)
        }
    }

    private fun clignoter() {
        debuterTv.blink()
    }
}