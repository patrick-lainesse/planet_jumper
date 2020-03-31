package com.example.planet_jumper

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class Planete: AppCompatActivity(), View.OnClickListener {

    private lateinit var vaisseau: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.planete)

        vaisseau = intent.extras!!.getString("choix").toString()
        setListener()
    }

    private fun setListener() {

        val boutonCarte = findViewById<AppCompatButton>(R.id.bouton_cartes)
        boutonCarte.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        val carte = findViewById<View>(R.id.carte_vaisseau)

        // afficher l'emplacement où injecter les statistiques propres au vaisseau sélectionné
        carte?.visibility = View.VISIBLE
    }
}