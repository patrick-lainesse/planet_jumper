package com.example.planet_jumper

import java.io.Serializable

/* classe qui servira simplement à renvoyer à contenir les paramètres d'une carte à jouer
et simplifiera son intégration dans un layout */
class Cartes(val nom: String, val categorie: String, val image: String, val effet:String): Serializable

// id????