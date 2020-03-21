package com.example.planet_jumper

import android.provider.BaseColumns

object  DBContenuVaisseau {

    // classe pour définir les paramètres de la table SQL des vaisseaux
    class EntreeVaisseau: BaseColumns {
        companion object {
            val NOM_TABLE = "vaisseaux"
            val COLONNE_VAISSEAU_ID = "vaisseau_id"
            val COLONNE_NOM = "nom"
            val COLONNE_VITESSE = "vitesse"
            val COLONNE_CAPACITE = "capacite"
            val COLONNE_CONSOMMATION = "consommation"
            val COLONNE_POIDS = "poids"
        }
    }
}