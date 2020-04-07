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

        /* voir companion object à la fin de cette classe et le fichier DBContenuVaisseau.kt pour les détails
        de la création de la db */
        db?.execSQL(SQL_CREATE_ENTRIES)

        val values = ContentValues().apply {
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_VAISSEAU_ID, "1")
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_NOM, "Nina")
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_VITESSE, "400000")
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_CAPACITE, "6")
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_CONSOMMATION, "2.1")
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_POIDS, "900")
        }
        db?.insert(DBContenuVaisseau.EntreeVaisseau.NOM_TABLE, null, values)

        values.apply {
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_VAISSEAU_ID, "2")
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_NOM, "Pinta")
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_VITESSE, "300000")
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_CAPACITE, "8")
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_CONSOMMATION, "1.1")
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_POIDS, "1200")
        }
        db?.insert(DBContenuVaisseau.EntreeVaisseau.NOM_TABLE, null, values)

        values.apply {
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_VAISSEAU_ID, "3")
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_NOM, "Santa Maria")
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_VITESSE, "250000")
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_CAPACITE, "12")
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_CONSOMMATION, "1.8")
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_POIDS, "1500")
        }
        db?.insert(DBContenuVaisseau.EntreeVaisseau.NOM_TABLE, null, values)

        values.apply {
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_VAISSEAU_ID, "4")
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_NOM, "Victoria")
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_VITESSE, "275000")
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_CAPACITE, "11")
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_CONSOMMATION, "1.7")
            put(DBContenuVaisseau.EntreeVaisseau.COLONNE_POIDS, "1400")
        }
        db?.insert(DBContenuVaisseau.EntreeVaisseau.NOM_TABLE, null, values)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    // fonction qui retourne un objet ModeleVaisseau avec les statistiques d'un vaisseau tirées de la db
    fun lireVaisseau(nomVaisseau: String): ModeleVaisseau {
        val tableVaisseaux = ArrayList<ModeleVaisseau>()
        val db = writableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db!!.rawQuery("SELECT * FROM " + DBContenuVaisseau.EntreeVaisseau.NOM_TABLE
                    + " WHERE " + DBContenuVaisseau.EntreeVaisseau.COLONNE_NOM + "= '" + nomVaisseau + "'", null)
        } catch (e: SQLiteException) {
            db.execSQL(SQL_CREATE_ENTRIES)
        }

        var nom: String
        var vitesse: String
        var capacite: String
        var consommation: String
        var poids: String

        if(cursor!!.moveToFirst()) {
            while(cursor.isAfterLast == false) {
                nom = cursor.getString(cursor.getColumnIndex(DBContenuVaisseau.EntreeVaisseau.COLONNE_NOM))
                vitesse = cursor.getString(cursor.getColumnIndex(DBContenuVaisseau.EntreeVaisseau.COLONNE_VITESSE))
                capacite = cursor.getString(cursor.getColumnIndex(DBContenuVaisseau.EntreeVaisseau.COLONNE_CAPACITE))
                consommation = cursor.getString(cursor.getColumnIndex(DBContenuVaisseau.EntreeVaisseau.COLONNE_CONSOMMATION))
                poids = cursor.getString(cursor.getColumnIndex(DBContenuVaisseau.EntreeVaisseau.COLONNE_POIDS))

                //leVaisseau = ModeleVaisseau(nom, vitesse, capacite, consommation, poids)
                tableVaisseaux.add(ModeleVaisseau(nom, vitesse, capacite, consommation, poids))
                cursor.moveToNext()
            }
        }

        cursor.close()

        return tableVaisseaux[0]
    }

    // implantation d'un companion object qui fournit les paramètres à la classe étendue SQLiteOpenHelper
    companion object {
        val DATABASE_NAME = "vaisseaux_directory.db"
        val DATABASE_VERSION = 1

        private val SQL_CREATE_ENTRIES = "CREATE TABLE " + DBContenuVaisseau.EntreeVaisseau.NOM_TABLE + " (" +
                DBContenuVaisseau.EntreeVaisseau.COLONNE_VAISSEAU_ID + " TEXT PRIMARY KEY," +
                DBContenuVaisseau.EntreeVaisseau.COLONNE_NOM + " TEXT," +
                DBContenuVaisseau.EntreeVaisseau.COLONNE_VITESSE + " TEXT," +
                DBContenuVaisseau.EntreeVaisseau.COLONNE_CAPACITE + " TEXT," +
                DBContenuVaisseau.EntreeVaisseau.COLONNE_CONSOMMATION + " TEXT," +
                DBContenuVaisseau.EntreeVaisseau.COLONNE_POIDS + " TEXT)"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBContenuVaisseau.EntreeVaisseau.NOM_TABLE
    }
}