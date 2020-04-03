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
    private lateinit var linearLayoutManager: LinearLayoutManager

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
                /*val carte = findViewById<View>(R.id.carte_vaisseau)
                carte?.visibility = View.VISIBLE*/

                //supportFragmentManager.beginTransaction().replace(R.id.fragment_cartes, FragCartes.newInstance(), FragCartes.TAG).commit()

                val fragCartes: FragCartes = FragCartes.newInstance()


                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_cartes, fragCartes)
                    .addToBackStack(null)
                    .commit()
            }
        }


        /*val recyclerView = findViewById<RecyclerView>(R.id.recycler_cartes)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager*/


        //val recCartes = findViewById<View>(R.id.planete_recycler)
        //val listeCartes = Array<Cartes>
    }
}