package com.example.planet_jumper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat

// classe qui servira simplement à renvoyer des objets de la classe DataBaseHelper
// à la classe ChoixVaisseau selon la requête SQL effectuée
class ModeleVaisseau(val nom: String, val vitesse: String, val capacite: String, val consommation: String, val poids: String) {
/*

    fun peupler_card(parent: ViewGroup): View? {

        //val carte = findViewById<LinearLayoutCompat>(R.id.carte_vaisseau)
        val carte = LayoutInflater.from(parent.context).inflate(R.layout.card_vaisseau, parent, false)

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

        nomTV.text = nom
        vitesseTV.text = vitesse
        capaciteTV.text = capacite
        consommationTV.text = consommation
        poidsTV.text = poids

        return carte


    }
*/

/*
     class VaisseauViewHolder(itemView: View) {
         var nomTV: AppCompatTextView
         var vitesseTV: AppCompatTextView
         var capaciteTV: AppCompatTextView
         var consommationTV: AppCompatTextView
         var poidsTV: AppCompatTextView

         init {
             nomTV = itemView.findViewById(R.id.card_nom_vaisseau)
             vitesseTV = itemView.findViewById(R.id.card_vitesse)
             capaciteTV = itemView.findViewById(R.id.card_capacite)
             consommationTV = itemView.findViewById(R.id.card_consommation)
             poidsTV = itemView.findViewById(R.id.card_poids)
         }
     }
     */
}

/*
put("nom", "Pinta")
put("vitesse", "400000")
put("capacite", 6)
put("consommation", 2.1)
put("poids", 900)*/

/* ??? au cas où je veux m'essayer avec des integer plutôt que des String
"CREATE TABLE IF NOT EXISTS vaisseaux (" +
"_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
"nom TEXT, " + "vitesse INTEGER" + "capacite TINYINT" + "consommation REAL" + "poids INTEGER)"*/
