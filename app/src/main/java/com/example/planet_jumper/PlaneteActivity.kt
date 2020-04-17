package com.example.planet_jumper

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class Planete: AppCompatActivity(), View.OnClickListener {

    private var distanceParcourue: Float = 0.0f
    private lateinit var vaisseau: String
    val KEY_ICI: String = "ici"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.planete_activity)

        // récupère le choix de vaisseau qui a été fait afin de sélectionner la liste de cartes associées à ce vaisseau en particulier
        vaisseau = intent.extras!!.getString("choix").toString()
        distanceParcourue = intent.extras!!.getFloat(KEY_ICI)
        setListener()
    }

    // abonne les boutons à la fonction onclick
    private fun setListener() {

        val boutonCarte = findViewById<AppCompatButton>(R.id.bouton_cartes)
        val boutonPlanetes = findViewById<AppCompatButton>(R.id.bouton_planetes)
        boutonCarte.setOnClickListener(this)
        boutonPlanetes.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        val CARTES_FRAG_TAG = "fragCartes"
        val PLANETES_FRAG_TAG = "fragPlanetes"

        /* identifie le bouton qui a été cliqué et crée, montre ou cache le fragment désiré selon la sélection
        Puisque faire une requête au serveur nécessite un certain temps, il est préférable de cacher le fragment que de le détruire,
        ce qui impliquerait une nouvelle requête à chaque fois */
        when (v?.id) {
            R.id.bouton_cartes -> {
                if(supportFragmentManager.findFragmentByTag(CARTES_FRAG_TAG) != null) {
                    // si le fragment existe déjà, le montrer
                    supportFragmentManager
                        .beginTransaction()
                        .show(supportFragmentManager.findFragmentByTag(CARTES_FRAG_TAG)!!)
                        .commit()
                } else {
                    // s'il n'existe pas, le créer
                    val fragCartes: FragCartes = FragCartes.newInstance()
                    supportFragmentManager
                        .beginTransaction()
                        .add(R.id.frame, fragCartes, CARTES_FRAG_TAG)
                        .addToBackStack(null)
                        .commit()
                }
                if(supportFragmentManager.findFragmentByTag(PLANETES_FRAG_TAG) != null) {
                    // si l'autre fragment est visible, le cacher
                    supportFragmentManager
                        .beginTransaction()
                        .hide(supportFragmentManager.findFragmentByTag(PLANETES_FRAG_TAG)!!)
                        .commit()
                }
            }
            R.id.bouton_planetes -> {

                if(supportFragmentManager.findFragmentByTag(PLANETES_FRAG_TAG) != null) {
                    // si le fragment existe déjà, le montrer
                    supportFragmentManager
                        .beginTransaction()
                        .show(supportFragmentManager.findFragmentByTag(PLANETES_FRAG_TAG)!!)
                        .commit()
                } else {
                    // s'il n'existe pas, le créer

                    val fragPlanetes: FragListePlanetes = FragListePlanetes.newInstance()

                    supportFragmentManager
                        .beginTransaction()
                        .add(R.id.frame, fragPlanetes, PLANETES_FRAG_TAG)
                        .addToBackStack(null)
                        .commit()
                }
                if(supportFragmentManager.findFragmentByTag(CARTES_FRAG_TAG) != null) {
                    // si l'autre fragment est visible, le cacher
                    supportFragmentManager
                        .beginTransaction()
                        .hide(supportFragmentManager.findFragmentByTag(CARTES_FRAG_TAG)!!)
                        .commit()
                }
            }
        }
    }
}