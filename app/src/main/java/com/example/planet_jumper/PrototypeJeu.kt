package com.example.planet_jumper

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.prototype_layout.*

/* Activité pratiquement vide qui sert seulement à annoncer que le jeu est en développement.
Elle récupère la distance parcourue du fragment qui l'a démarrée et la repasse à l'activité FragListePlanetes */

class PrototypeJeu: AppCompatActivity(), View.OnClickListener {

    private var distanceParcourue: Float? = null
    val KEY_ICI: String = "ici"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.prototype_layout)

        distanceParcourue = intent.extras?.getFloat(KEY_ICI, 0F)
        Log.d("PlaneteDistance", distanceParcourue.toString())


        setListener()
    }

    private fun setListener() {
        proto_gagner.blink()
        proto_gagner.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        val intent = Intent(this, Planete::class.java)
        Log.d("logProtExtra", distanceParcourue.toString())
        intent.putExtra(KEY_ICI, distanceParcourue)
        startActivity(intent)
    }
}