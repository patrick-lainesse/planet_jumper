/*
Classe nécessaire pour utiliser la base de données. J'ai tenté de rester le plus près possible de l'exemple vu en
classe, en prenant seulement quelques-uns des raccourcis offerts par le langage Kotlin
 */

package com.example.planet_jumper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.view.Display
import android.widget.Toast

class DBHelper_Vaisseau(contexte: Context): SQLiteOpenHelper(contexte, DATABASE_NAME, null, DATABASE_VERSION) {

    //private var dbVaisseaux: SQLiteDatabase? = null  nécessaire???

    override fun onCreate(db: SQLiteDatabase?) {

/*        val sql = "CREATE TABLE IF NOT EXISTS vaisseaux (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nom TEXT, " + "vitesse INTEGER" + "capacite TINYINT" + "consommation REAL" + "poids INTEGER)"*/
        val sql = "CREATE TABLE IF NOT EXISTS vaisseaux (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nom TEXT, " + "vitesse TEXT" + "capacite TEXT" + "consommation TEXT" + "poids TEXT)"
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

    // fonction qui retourne un arraylist contenant les statistiques d'un vaisseau dans la db
    fun lireVaisseau(nomVaisseau: String): ModeleVaisseau {
        //val tableVaisseaux = ArrayList<ModeleVaisseau>()  ???
        var leVaisseau = ModeleVaisseau("OUAH", "", "", "", "") //??? OUAH
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM vaisseaux as vaisseau WHERE nom = '" + nomVaisseau + "'", null)
        } catch (e: SQLiteException) {
            // si la table n'est pas présente, la créer
            //????
            //db.execSQL(SQL_CREATE_ENTRIES)
            //return ArrayList()
        }

        var nom: String
        var vitesse: String
        var capacite: String
        var consommation: String
        var poids: String

        if(cursor!!.moveToFirst()) {
            while(cursor.isAfterLast == false) {
                nom = cursor.getString(cursor.getColumnIndex("nom"))
                vitesse = cursor.getString(cursor.getColumnIndex("vitesse"))
                capacite = cursor.getString(cursor.getColumnIndex("capacite"))
                consommation = cursor.getString(cursor.getColumnIndex("consommation"))
                poids = cursor.getString(cursor.getColumnIndex("poids"))

                leVaisseau = ModeleVaisseau(nom, vitesse, capacite, consommation, poids)
                //tableVaisseaux.add(ModeleVaisseau(nom, vitesse, capacite, consommation, poids))
                cursor.moveToNext()
            }
        }
        return leVaisseau
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