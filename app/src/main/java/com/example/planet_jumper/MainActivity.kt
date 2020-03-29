/*
Voir Accueil.kt pour commentaires sur le projet
MainActivity, espace réservé pour une éventuelle connexion à Google Play et autres potentielles
manoeuvre à effectuer avant le lancement du jeu, inutilisé pour l'instant.
 */
package com.example.planet_jumper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setClic()
    }

    private fun setClic() {

        val tv = findViewById<TextView>(R.id.mainTV)
        val intent = Intent(this@MainActivity, Accueil::class.java)
        tv.setOnClickListener { startActivity(intent) }

    }
}
