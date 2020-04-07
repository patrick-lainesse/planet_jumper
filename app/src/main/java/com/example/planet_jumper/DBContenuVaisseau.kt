package com.example.planet_jumper

import android.provider.BaseColumns

// contract class pour stocker les constantes utilisées pour créer les tables SQL

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

    class CartesJeu: BaseColumns {
        companion object {
            val NOM_TABLE = "cartes"
            val COLONNE_CARTE_ID = "carte_id"
            val COLONNE_NOM = "nom"
            val COLONNE_CATEGORIE = "categorie"
            val COLONNE_EFFET = "effet"
        }
    }
}

// ???? manque les images dans les deux db
// val nom: String, val categorie: String, val image: String, val effet:String