package com.example.planet_jumper

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout

class ChoixVaisseau: AppCompatActivity(), View.OnClickListener {

    // utilisation d'un ArrayList pour faciliter les manipulations répétitives sur les ImageView
    lateinit var vaisseau1: ImageView
    lateinit var vaisseau2: ImageView
    lateinit var vaisseau3: ImageView
    lateinit var vaisseau4: ImageView
    var table_vaisseau: MutableList<ImageView> = ArrayList()
    lateinit var dBHelper_Vaisseau: DBHelper_Vaisseau   // peut-être enelever ici et mettre plus bas???
    //lateinit var vaisseauID = -1

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

        // récupération des emplacements où seront affichés les données propres au vaisseau sélectionné
        // et des éléments à faire apparaître après une sélection
        val confirmer_choix = findViewById<TextView>(R.id.confirmer)
        //val carte = findViewById<LinearLayoutCompat>(R.id.carte_vaisseau)
        var carte = findViewById<View>(R.id.carte_vaisseau)
        //val nom_vaisseau = carte.findViewById<AppCompatTextView>(R.id.card_nom_vaisseau)
        var vaisseau_choisi: ModeleVaisseau
        dBHelper_Vaisseau = DBHelper_Vaisseau(this)

        val test: String

        if (v != null) {
            when(v.id) {
                R.id.vaisseau1 -> {
                    vaisseau_choisi = dBHelper_Vaisseau.lireVaisseau("Nina")
                    Toast.makeText(this, vaisseau_choisi.nom, Toast.LENGTH_LONG).show()
                    carte = vaisseau_choisi.peupler_card(findViewById(R.id.carte_vaisseau))

                }  // test ????

                R.id.vaisseau2 -> Toast.makeText(this, "v2", Toast.LENGTH_LONG).show()
                R.id.vaisseau3 -> Toast.makeText(this, "v3", Toast.LENGTH_LONG).show()
                R.id.vaisseau4 -> Toast.makeText(this, "v4", Toast.LENGTH_LONG).show()
            }

            // cacher les images des vaisseaux lorsqu'un de ceux-ci est sélectionné
            for (objet in table_vaisseau) {
                objet.visibility = View.GONE
            }

            //nom_vaisseau.setText("Hourra!") // test????

            // afficher l'emplacement où injecter les statistiques propres au vaisseau sélectionné
            carte?.visibility = View.VISIBLE

            // afficher le texte pour confirmer le choix et le bouton pour retourner à l'écran choix de vaisseau????
            confirmer_choix?.visibility = View.VISIBLE  // faire blinker ????
        }
    }

    fun toastExt() {
        Toast.makeText(this, "Externe", Toast.LENGTH_LONG).show()
    }
}