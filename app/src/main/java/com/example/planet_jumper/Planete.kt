package com.example.planet_jumper

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Planete: AppCompatActivity(), View.OnClickListener {

    private lateinit var vaisseau: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.planete)

        vaisseau = intent.extras!!.getString("choix").toString()
        setListener()
    }

    private fun setListener() {

        val boutonCarte = findViewById<AppCompatButton>(R.id.bouton_cartes)
        boutonCarte.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.bouton_cartes -> {
                val fragCartes: FragCartes = FragCartes.newInstance()
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.frame, fragCartes)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}