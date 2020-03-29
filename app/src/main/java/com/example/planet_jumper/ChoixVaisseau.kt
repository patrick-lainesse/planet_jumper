package com.example.planet_jumper

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout

class ChoixVaisseau: AppCompatActivity(), View.OnClickListener {

    // utilisation d'un ArrayList pour faciliter les manipulations répétitives sur les ImageView
    private lateinit var vaisseau1: ImageView
    private lateinit var vaisseau2: ImageView
    private lateinit var vaisseau3: ImageView
    private lateinit var vaisseau4: ImageView
    private var tableVaisseau: MutableList<ImageView> = ArrayList()
    private lateinit var dbhelperVaisseau: DBHelperVaisseau

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choix_vaisseau_layout)

        setListener()
    }

    private fun setListener() {
        val retour = findViewById<TextView>(R.id.retourTV)
        val ninaTV = findViewById<TextView>(R.id.ninaTV)
        val pintaTV = findViewById<TextView>(R.id.pintaTV)
        val santaTV = findViewById<TextView>(R.id.santaTV)
        val victoTV = findViewById<TextView>(R.id.victoriaTV)

        vaisseau1 = findViewById(R.id.vaisseau1)
        vaisseau2 = findViewById(R.id.vaisseau2)
        vaisseau3 = findViewById(R.id.vaisseau3)
        vaisseau4 = findViewById(R.id.vaisseau4)

        tableVaisseau.add(vaisseau1)
        tableVaisseau.add(vaisseau2)
        tableVaisseau.add(vaisseau3)
        tableVaisseau.add(vaisseau4)

        for (objet in tableVaisseau) {
            objet.setOnClickListener(this)
        }
        retour.setOnClickListener(this)
        ninaTV.setOnClickListener(this)
        pintaTV.setOnClickListener(this)
        santaTV.setOnClickListener(this)
        victoTV.setOnClickListener(this)

    }

    // fonction qui réagit aux différents clics possibles dans cette activité
    override fun onClick(v: View?) {

        /* récupération des emplacements où seront affichés les données propres au vaisseau sélectionné
        et des éléments à faire apparaître après une sélection */
        val confirmerChoix = findViewById<TextView>(R.id.confirmer)
        val retour = findViewById<TextView>(R.id.retourTV)
        val carte = findViewById<View>(R.id.carte_vaisseau)
        var vaisseauChoisi = ModeleVaisseau("", "", "", "", "")
        dbhelperVaisseau = DBHelperVaisseau(this)


        val imageIV = carte.findViewById<AppCompatImageView>(R.id.card_image)
        val nomTV: AppCompatTextView
        val vitesseTV: AppCompatTextView
        val capaciteTV: AppCompatTextView
        val consommationTV: AppCompatTextView
        val poidsTV: AppCompatTextView

        nomTV = carte.findViewById(R.id.card_nom_vaisseau)
        vitesseTV = carte.findViewById(R.id.card_vitesse)
        capaciteTV = carte.findViewById(R.id.card_capacite)
        consommationTV = carte.findViewById(R.id.card_consommation)
        poidsTV = carte.findViewById(R.id.card_poids)

        if (v != null) {
            when(v.id) {
                R.id.vaisseau1, R.id.ninaTV -> {
                    vaisseauChoisi = dbhelperVaisseau.lireVaisseau("Nina")
                    imageIV.setBackgroundResource(R.drawable.nina)
                }
                R.id.vaisseau2, R.id.pintaTV -> {
                    vaisseauChoisi = dbhelperVaisseau.lireVaisseau("Pinta")
                    imageIV.setBackgroundResource(R.drawable.pinta)
                }
                R.id.vaisseau3, R.id.santaTV -> {
                    vaisseauChoisi = dbhelperVaisseau.lireVaisseau("Santa Maria")
                    imageIV.setBackgroundResource(R.drawable.santa_maria)
                }
                R.id.vaisseau4, R.id.victoriaTV -> {
                    vaisseauChoisi = dbhelperVaisseau.lireVaisseau("Victoria")
                    imageIV.setBackgroundResource(R.drawable.victoria)
                }
                R.id.retourTV -> {
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
            }

            nomTV.setText(vaisseauChoisi.nom)

            // on injecte les données tirées de la db dans les String où afficher les statistiques du vaisseau choisi
            var txtOriginal = vitesseTV.text.toString()
            var pos = txtOriginal.indexOf(':')
            var txt = txtOriginal.substring(0, pos+2) + vaisseauChoisi.vitesse + txtOriginal.substring(pos+1, txtOriginal.length)
            vitesseTV.setText(txt)

            txtOriginal = capaciteTV.text.toString()
            capaciteTV.setText(txtOriginal + vaisseauChoisi.capacite)

            txtOriginal = consommationTV.text.toString()
            pos = txtOriginal.indexOf(':')
            txt = txtOriginal.substring(0, pos+2) + vaisseauChoisi.consommation + txtOriginal.substring(pos+1, txtOriginal.length)
            consommationTV.setText(txt)

            txtOriginal = poidsTV.text.toString()
            pos = txtOriginal.indexOf(':')
            txt = txtOriginal.substring(0, pos+2) + vaisseauChoisi.poids + txtOriginal.substring(pos+1, txtOriginal.length)
            poidsTV.setText(txt)

            // cacher les images des vaisseaux lorsqu'un de ceux-ci est sélectionné
            for (objet in tableVaisseau) {
                objet.visibility = View.GONE
            }

            val layoutACacher = findViewById<ConstraintLayout>(R.id.layout_choix)
            layoutACacher.visibility = View.GONE

            // afficher l'emplacement où injecter les statistiques propres au vaisseau sélectionné
            carte?.visibility = View.VISIBLE

            // afficher le texte pour confirmer le choix et le bouton pour retourner à l'écran choix de vaisseau
            confirmerChoix?.visibility = View.VISIBLE
            confirmerChoix?.blink()
            retour?.visibility = View.VISIBLE

        }
    }
}