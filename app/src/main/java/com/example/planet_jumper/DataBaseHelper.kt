package com.example.planet_jumper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(private val contexte: Context): SQLiteOpenHelper(contexte, DATABASE_NAME, null, DATABASE_VERSION) {

    //static final String DATABASE_NAME = "table_vaisseaux"



    override fun onCreate(db: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}