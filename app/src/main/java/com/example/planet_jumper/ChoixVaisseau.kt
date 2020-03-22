package com.example.planet_jumper

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout

class ChoixVaisseau: AppCompatActivity(), View.OnClickListener {

    // utilisation d'un ArrayList pour faciliter les manipulations répétitives sur les ImageView
    lateinit var vaisseau1: ImageView
    lateinit var vaisseau2: ImageView
    lateinit var vaisseau3: ImageView
    lateinit var vaisseau4: ImageView
    var table_vaisseau: MutableList<ImageView> = ArrayList()
    lateinit var dBHelper_Vaisseau: DBHelper_Vaisseau   // peut-être enelever ici et mettre plus bas???

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choix_vaisseau_layout)

        afficher_choix()
    }

    private fun afficher_choix() {
        vaisseau1 = findViewById<ImageView>(R.id.vaisseau1)
        vaisseau2 = findViewById<ImageView>(R.id.vaisseau2)
        vaisseau3 = findViewById<ImageView>(R.id.vaisseau3)
        vaisseau4 = findViewById<ImageView>(R.id.vaisseau4)

        table_vaisseau.add(vaisseau1)
        table_vaisseau.add(vaisseau2)
        table_vaisseau.add(vaisseau3)
        table_vaisseau.add(vaisseau4)

        // voir si on peut faire un onClick pour la vue en entier, et mettre les case des titres.
        // si oui, on peut probablement enlever le arraylist et simplifier le code ????
        for (objet in table_vaisseau) {
            objet.setOnClickListener(this)
        }
    }

    override fun onClick(v: View?) {

        /* récupération des emplacements où seront affichés les données propres au vaisseau sélectionné
        et des éléments à faire apparaître après une sélection */
        val confirmer_choix = findViewById<TextView>(R.id.confirmer)
        // ??? pourquoi en rouge??? retourTV
        val retour = findViewById<TextView>(R.id.retourTV)
        val carte = findViewById<View>(R.id.carte_vaisseau)
        var vaisseau_choisi = ModeleVaisseau("", "", "", "", "")
        dBHelper_Vaisseau = DBHelper_Vaisseau(this)


        val nomTV: AppCompatTextView
        val vitesseTV: AppCompatTextView
        val capaciteTV: AppCompatTextView
        val consommationTV: AppCompatTextView
        val poidsTV: AppCompatTextView

        nomTV = carte.findViewById<AppCompatTextView>(R.id.card_nom_vaisseau)
        vitesseTV = carte.findViewById<AppCompatTextView>(R.id.card_vitesse)
        capaciteTV = carte.findViewById<AppCompatTextView>(R.id.card_capacite)
        consommationTV = carte.findViewById<AppCompatTextView>(R.id.card_consommation)
        poidsTV = carte.findViewById<AppCompatTextView>(R.id.card_poids)

        val test: String

        if (v != null) {
            when(v.id) {
                R.id.vaisseau1 -> {
                    vaisseau_choisi = dBHelper_Vaisseau.lireVaisseau("Nina")
                }
                R.id.vaisseau2 -> {
                    vaisseau_choisi = dBHelper_Vaisseau.lireVaisseau("Pinta")
                }
                R.id.vaisseau3 -> {
                    vaisseau_choisi = dBHelper_Vaisseau.lireVaisseau("Santa Maria")
                }
                R.id.vaisseau4 -> {
                    vaisseau_choisi = dBHelper_Vaisseau.lireVaisseau("Victoria")
                }
            }

            nomTV.setText(vaisseau_choisi.nom)

            var txtOriginal = vitesseTV.text.toString()
            var pos = txtOriginal.indexOf(':')
            var txt = txtOriginal.substring(0, pos+2) + vaisseau_choisi.vitesse + txtOriginal.substring(pos+1, txtOriginal.length)
            vitesseTV.setText(txt)

            txtOriginal = capaciteTV.text.toString()
            capaciteTV.setText(txtOriginal + vaisseau_choisi.capacite)

            txtOriginal = consommationTV.text.toString()
            pos = txtOriginal.indexOf(':')
            txt = txtOriginal.substring(0, pos+2) + vaisseau_choisi.consommation + txtOriginal.substring(pos+1, txtOriginal.length)
            consommationTV.setText(txt)

            txtOriginal = poidsTV.text.toString()
            pos = txtOriginal.indexOf(':')
            txt = txtOriginal.substring(0, pos+2) + vaisseau_choisi.poids + txtOriginal.substring(pos+1, txtOriginal.length)
            poidsTV.setText(txt)

            // cacher les images des vaisseaux lorsqu'un de ceux-ci est sélectionné
            for (objet in table_vaisseau) {
                objet.visibility = View.GONE
            }

            val layoutACacher = findViewById<ConstraintLayout>(R.id.layout_choix)
            layoutACacher.visibility = View.GONE

            // afficher l'emplacement où injecter les statistiques propres au vaisseau sélectionné
            carte?.visibility = View.VISIBLE

            // afficher le texte pour confirmer le choix et le bouton pour retourner à l'écran choix de vaisseau????
            confirmer_choix?.visibility = View.VISIBLE
            confirmer_choix?.blink()
            retour?.visibility = View.VISIBLE
        }
    }
}