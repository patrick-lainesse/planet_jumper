package com.example.planet_jumper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class ChoixVaisseau: AppCompatActivity(), View.OnClickListener {

    // utilisation d'un ArrayList pour faciliter les manipulations répétitives sur les ImageView
    lateinit var vaisseau1: ImageView
    lateinit var vaisseau2: ImageView
    lateinit var vaisseau3: ImageView
    lateinit var vaisseau4: ImageView
    var table_vaisseau: MutableList<ImageView> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choix_vaisseau_layout)

        afficher_stats()
    }

    private fun afficher_stats() {
        vaisseau1 = findViewById<ImageView>(R.id.vaisseau1)
        vaisseau2 = findViewById<ImageView>(R.id.vaisseau2)
        vaisseau3 = findViewById<ImageView>(R.id.vaisseau3)
        vaisseau4 = findViewById<ImageView>(R.id.vaisseau4)

        table_vaisseau.add(vaisseau1)
        table_vaisseau.add(vaisseau2)
        table_vaisseau.add(vaisseau3)
        table_vaisseau.add(vaisseau4)

        for (objet in table_vaisseau) {
            objet.setOnClickListener(this)
        }



    }

    override fun onClick(v: View?) {

        val layout = LinearLayout(v?.context)
        val layoutInflater = LayoutInflater.from(this)

        if (v != null) {
            when(v.id) {
                R.id.vaisseau1 -> Toast.makeText(this, "v1", Toast.LENGTH_LONG).show()
                R.id.vaisseau2 -> Toast.makeText(this, "v2", Toast.LENGTH_LONG).show()
                R.id.vaisseau3 -> Toast.makeText(this, "v3", Toast.LENGTH_LONG).show()
                R.id.vaisseau4 -> Toast.makeText(this, "v4", Toast.LENGTH_LONG).show()
            }

            for (objet in table_vaisseau) {
                objet.visibility = View.GONE
            }

            //layout.addView(cardView)
            val card = layoutInflater.inflate(R.layout.card_vaisseau, null)
            layout.addView(card)



        }
    }
}