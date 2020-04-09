/*
Classe nécessaire pour utiliser la base de données.
Un base adapter n'était pas nécessaire ici car on n'affiche les données que d'un vaisseau à la fois.
J'ai choisi d'inclure la méthode pour effectuer la SQL query à l'intérieur de la classe Helper car cela me semblait plus logique.
L'adapter sera utilisé dans la prochaine utilisation de SQL (pour afficher les cartes associées à un vaisseau en particulier, dans l'activité CartePlanetes).

Source utilisée: https://www.tutorialkart.com/kotlin-android/android-sqlite-example-application/
 */

package com.example.planet_jumper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(contexte: Context): SQLiteOpenHelper(contexte, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
//test merge branch???? + placer le companion object au début de la classe???
        /* voir companion object à la fin de cette classe et le fichier DBDetails.kt pour les détails
        de la création de la db */
        db?.execSQL(SQL_CREATE_ENTRIES)
        db?.execSQL(CREER_TABLE_CARTES)

        val values = ContentValues().apply {
            put(DBDetails.EntreeVaisseau.COLONNE_VAISSEAU_ID, "1")
            put(DBDetails.EntreeVaisseau.COLONNE_NOM, "Nina")
            put(DBDetails.EntreeVaisseau.COLONNE_VITESSE, "400000")
            put(DBDetails.EntreeVaisseau.COLONNE_CAPACITE, "6")
            put(DBDetails.EntreeVaisseau.COLONNE_CONSOMMATION, "2.1")
            put(DBDetails.EntreeVaisseau.COLONNE_POIDS, "900")
        }
        db?.insert(DBDetails.EntreeVaisseau.NOM_TABLE, null, values)

        values.apply {
            put(DBDetails.EntreeVaisseau.COLONNE_VAISSEAU_ID, "2")
            put(DBDetails.EntreeVaisseau.COLONNE_NOM, "Pinta")
            put(DBDetails.EntreeVaisseau.COLONNE_VITESSE, "300000")
            put(DBDetails.EntreeVaisseau.COLONNE_CAPACITE, "8")
            put(DBDetails.EntreeVaisseau.COLONNE_CONSOMMATION, "1.1")
            put(DBDetails.EntreeVaisseau.COLONNE_POIDS, "1200")
        }
        db?.insert(DBDetails.EntreeVaisseau.NOM_TABLE, null, values)

        values.apply {
            put(DBDetails.EntreeVaisseau.COLONNE_VAISSEAU_ID, "3")
            put(DBDetails.EntreeVaisseau.COLONNE_NOM, "Santa Maria")
            put(DBDetails.EntreeVaisseau.COLONNE_VITESSE, "250000")
            put(DBDetails.EntreeVaisseau.COLONNE_CAPACITE, "12")
            put(DBDetails.EntreeVaisseau.COLONNE_CONSOMMATION, "1.8")
            put(DBDetails.EntreeVaisseau.COLONNE_POIDS, "1500")
        }
        db?.insert(DBDetails.EntreeVaisseau.NOM_TABLE, null, values)

        values.apply {
            put(DBDetails.EntreeVaisseau.COLONNE_VAISSEAU_ID, "4")
            put(DBDetails.EntreeVaisseau.COLONNE_NOM, "Victoria")
            put(DBDetails.EntreeVaisseau.COLONNE_VITESSE, "275000")
            put(DBDetails.EntreeVaisseau.COLONNE_CAPACITE, "11")
            put(DBDetails.EntreeVaisseau.COLONNE_CONSOMMATION, "1.7")
            put(DBDetails.EntreeVaisseau.COLONNE_POIDS, "1400")
        }
        db?.insert(DBDetails.EntreeVaisseau.NOM_TABLE, null, values)

        val valuesCartes = ContentValues().apply {
            //put(DBDetails.CartesJeu.COLONNE_CARTE_ID, "1")????
            put(DBDetails.CartesJeu.COLONNE_NOM, "Burst")
            put(DBDetails.CartesJeu.COLONNE_IMAGE, "Du beau feu")
            put(DBDetails.CartesJeu.COLONNE_CATEGORIE, "Acceleration")
            put(DBDetails.CartesJeu.COLONNE_EFFET, "Diminue la distance de 100 000 km et augmente la température de 100°C.")
        }
        db?.insert(DBDetails.CartesJeu.NOM_TABLE, null, valuesCartes)

        valuesCartes.apply {
            //put(DBDetails.CartesJeu.COLONNE_CARTE_ID, "2")
            put(DBDetails.CartesJeu.COLONNE_NOM, "Réparation")
            put(DBDetails.CartesJeu.COLONNE_IMAGE, "Un outil")
            put(DBDetails.CartesJeu.COLONNE_CATEGORIE, "Réparation")
            put(DBDetails.CartesJeu.COLONNE_EFFET, "Répare jusqu'à 10% de l'état du vaisseau. Ne peut avoir un vaisseau réparé au-delà de 100%.")
        }
        db?.insert(DBDetails.CartesJeu.NOM_TABLE, null, valuesCartes)

        valuesCartes.apply {
            //put(DBDetails.CartesJeu.COLONNE_CARTE_ID, "3")
            put(DBDetails.CartesJeu.COLONNE_NOM, "Freinage")
            put(DBDetails.CartesJeu.COLONNE_IMAGE, "Du feu bleu")
            put(DBDetails.CartesJeu.COLONNE_CATEGORIE, "Accélération")
            put(DBDetails.CartesJeu.COLONNE_EFFET, "Diminue la vitesse d'une année-lumière.")
        }
        db?.insert(DBDetails.CartesJeu.NOM_TABLE, null, valuesCartes)

        valuesCartes.apply {
            //put(DBDetails.CartesJeu.COLONNE_CARTE_ID, "4")
            put(DBDetails.CartesJeu.COLONNE_NOM, "Virage")
            put(DBDetails.CartesJeu.COLONNE_IMAGE, "Vaisseau tourne")
            put(DBDetails.CartesJeu.COLONNE_CATEGORIE, "Protection")
            put(DBDetails.CartesJeu.COLONNE_EFFET, "Évite un obstacle sur la course, ajoute 5 boucliers.")
        }
        db?.insert(DBDetails.CartesJeu.NOM_TABLE, null, valuesCartes)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        db?.execSQL(EFFACER_TABLE_CARTES)
        onCreate(db)
    }

    // fonction qui retourne un objet ModeleVaisseau avec les statistiques d'un vaisseau tirées de la db
    fun lireVaisseau(nomVaisseau: String): ModeleVaisseau {
        val tableVaisseaux = ArrayList<ModeleVaisseau>()
        val db = writableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db!!.rawQuery("SELECT * FROM " + DBDetails.EntreeVaisseau.NOM_TABLE
                    + " WHERE " + DBDetails.EntreeVaisseau.COLONNE_NOM + "= '" + nomVaisseau + "'", null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
        }

        var nom: String
        var vitesse: String
        var capacite: String
        var consommation: String
        var poids: String

        if(cursor!!.moveToFirst()) {
            while(!cursor.isAfterLast) {
                nom = cursor.getString(cursor.getColumnIndex(DBDetails.EntreeVaisseau.COLONNE_NOM))
                vitesse = cursor.getString(cursor.getColumnIndex(DBDetails.EntreeVaisseau.COLONNE_VITESSE))
                capacite = cursor.getString(cursor.getColumnIndex(DBDetails.EntreeVaisseau.COLONNE_CAPACITE))
                consommation = cursor.getString(cursor.getColumnIndex(DBDetails.EntreeVaisseau.COLONNE_CONSOMMATION))
                poids = cursor.getString(cursor.getColumnIndex(DBDetails.EntreeVaisseau.COLONNE_POIDS))

                tableVaisseaux.add(ModeleVaisseau(nom, vitesse, capacite, consommation, poids))
                cursor.moveToNext()
            }
        }

        cursor.close()
        return tableVaisseaux[0]
    }

    // fonction qui lit l'ensemble des cartes de la table des cartes ??? à mettre à jour selon la sélection du vaisseau, et éventuellement selon les cartes que le joueur possède
    fun lireCartes(): ArrayList<Cartes> {
        val deck = ArrayList<Cartes>()
        val db = writableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db!!.rawQuery("SELECT * FROM " + DBDetails.CartesJeu.NOM_TABLE, null)
        } catch (e: SQLiteException) {
            db.execSQL(CREER_TABLE_CARTES)
        }

        // rajouter id ici???
        var id: Int
        var nom: String
        var image: String
        var categorie: String
        var effet: String

        if(cursor!!.moveToFirst()) {
            while(!cursor.isAfterLast) {

                // ??? userModel.setId(c.getInt(c.getColumnIndex(KEY_ID)))
                id = cursor.getInt(cursor.getColumnIndex(DBDetails.CartesJeu.COLONNE_CARTE_ID))
                nom = cursor.getString(cursor.getColumnIndex(DBDetails.CartesJeu.COLONNE_NOM))
                image = cursor.getString(cursor.getColumnIndex(DBDetails.CartesJeu.COLONNE_IMAGE))
                categorie = cursor.getString(cursor.getColumnIndex(DBDetails.CartesJeu.COLONNE_CATEGORIE))
                effet = cursor.getString(cursor.getColumnIndex(DBDetails.CartesJeu.COLONNE_EFFET))

                deck.add(Cartes(id, nom, image, categorie, effet))
                cursor.moveToNext()
            }
        }

        cursor.close()
        return deck
    }

    // implantation d'un companion object qui fournit les paramètres à la classe étendue SQLiteOpenHelper
    companion object {
        val DATABASE_NAME = "vaisseaux_directory.db"
        val DATABASE_VERSION = 3

        private val SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + DBDetails.EntreeVaisseau.NOM_TABLE + " (" +
                DBDetails.EntreeVaisseau.COLONNE_VAISSEAU_ID + " TEXT PRIMARY KEY," +
                DBDetails.EntreeVaisseau.COLONNE_NOM + " TEXT," +
                DBDetails.EntreeVaisseau.COLONNE_VITESSE + " TEXT," +
                DBDetails.EntreeVaisseau.COLONNE_CAPACITE + " TEXT," +
                DBDetails.EntreeVaisseau.COLONNE_CONSOMMATION + " TEXT," +
                DBDetails.EntreeVaisseau.COLONNE_POIDS + " TEXT)"


        // DBDetails.CartesJeu.COLONNE_NOM + " TEXT NOT NULL," +
        private val CREER_TABLE_CARTES = "CREATE TABLE IF NOT EXISTS " + DBDetails.CartesJeu.NOM_TABLE + " (" +
                //DBDetails.CartesJeu.COLONNE_CARTE_ID + " TEXT PRIMARY KEY," +
                DBDetails.CartesJeu.COLONNE_CARTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBDetails.CartesJeu.COLONNE_NOM + " TEXT NOT NULL," +
                DBDetails.CartesJeu.COLONNE_IMAGE + " TEXT NOT NULL," +
                DBDetails.CartesJeu.COLONNE_CATEGORIE + " TEXT NOT NULL," +
                DBDetails.CartesJeu.COLONNE_EFFET + " TEXT NOT NULL)"

        // manque les images dans les db????

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBDetails.EntreeVaisseau.NOM_TABLE
        private val EFFACER_TABLE_CARTES = "DROP TABLE IF EXISTS " + DBDetails.CartesJeu.NOM_TABLE
    }
}