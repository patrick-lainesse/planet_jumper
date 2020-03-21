package com.example.planet_jumper

// classe qui servira simplement à renvoyer des objets de la classe DataBaseHelper
// à la classe ChoixVaisseau selon la requête SQL effectuée
class ModeleVaisseau(val nom: String, val vitesse: String, val capacite: String, val consommation: String, val poids: String)

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
