/*
Classe nécessaire pour utiliser la base de données. J'ai tenté de rester le plus près possible de l'exemple vu en
classe, en prenant seulement quelques-uns des raccourcis offerts par le langage Kotlin
 */

package com.example.planet_jumper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(contexte: Context): SQLiteOpenHelper(contexte, DATABASE_NAME, null, DATABASE_VERSION) {

    //private var dbVaisseaux: SQLiteDatabase? = null  nécessaire???

    override fun onCreate(db: SQLiteDatabase?) {

        val sql = "CREATE TABLE IF NOT EXISTS vaisseaux (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nom TEXT, " + "vitesse INTEGER" + "capacite TINYINT" + "consommation REAL" + "poids INTEGER)"
        db?.execSQL(sql)

        val values = ContentValues().apply {
            put("nom", "Santa Maria")
            put("vitesse", "300000")
            put("capacite", 8)
            put("consommation", 1.1)
            put("poids", 1200)
        }
        db?.insert("vaisseaux", "null", values)

        values.apply {
            put("nom", "Nina")
            put("vitesse", "250000")
            put("capacite", 12)
            put("consommation", 1.8)
            put("poids", 1500)
        }
        db?.insert("vaisseaux", "null", values)

        values.apply {
            put("nom", "Pinta")
            put("vitesse", "400000")
            put("capacite", 6)
            put("consommation", 2.1)
            put("poids", 900)
        }
        db?.insert("vaisseaux", "null", values)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS vaisseaux")
        onCreate(db)
    }

    // implantation d'un companion object qui fournit les paramètres à la classe étendue SQLiteOpenHelper
    companion object {
        val DATABASE_NAME = "vaisseaux_directory"
        //@SuppressLint("SdCardPath")     // nécessaire???
        //val DATABASE_PATH = "/data/data/com.jk.developers.firstkotlinapp/databases/"
        val DATABASE_VERSION = 1
        //val TABLE_ACCOUNTS = "accounts"
        //val TAG = "Error-->>class_dbhelper"
    }
}