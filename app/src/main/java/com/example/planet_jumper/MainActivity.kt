package com.example.planet_jumper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setClic()
    }

    private fun setClic() {

        val tv = findViewById<TextView>(R.id.mainTV)
        val intent = Intent(this@MainActivity, Accueil::class.java)
        tv.setOnClickListener { startActivity(intent) }

    }
}
