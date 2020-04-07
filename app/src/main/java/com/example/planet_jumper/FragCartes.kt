package com.example.planet_jumper

import android.content.Context
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.planet_jumper.R.layout.frag_cartes
import java.lang.ClassCastException


class FragCartes : Fragment() {

    private lateinit var listener: OnCarteSelected  // nécessaire???
    private var deck: ArrayList<Cartes> = ArrayList()
    private lateinit var dbhelper: DBHelper

    companion object {

        fun newInstance(): FragCartes {
            return FragCartes()
        }
    }

    // fonction qui fournit le contexte pour le dbhelper
    override fun onAttach(context: Context) {
        super.onAttach(context)

        dbhelper = DBHelper(context)

        /*if(context is OnCarteSelected) {    //???
            listener = context
        } else {
            throw ClassCastException(context.toString() + " doit implémenter OnCarteSelected.")
        }

        // obtenir les données propres aux cartes
        val resources = context.resources
        // doit implémenter des arraylist des noms de cartes, mais doit réfléchir à comment peupler ces AList
        selon les cartes que possède le joueur à un moment donné

        noms = resources.getStringArray(R.array.names)
        descriptions
        urls

        obtenir les images*/
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //dbhelper = DBHelper(context)

        // test???
        deck.add(Cartes("Une", "Trad", "mage", "Allo"))
        deck.add(Cartes("Deux", "Trad", "mage", "Bobo"))
        deck.add(Cartes("Trois", "Trad", "mage", "Rhume"))
        deck.add(Cartes("Quatre", "Trad", "mage", "Cerveau"))
        deck.add(Cartes("Cinq", "Trad", "mage", "Coqueliquot"))
        deck.add(Cartes("Six", "Trad", "mage", "Manon"))
        deck.add(Cartes("Sept", "Trad", "mage", "Condilons"))
        deck.add(Cartes("Huit", "Trad", "mage", "Gustave"))
        deck.add(Cartes("Neuf", "Trad", "mage", "Violette"))
        deck.add(Cartes("Dix", "Trad", "mage", "Gugu"))
        deck.add(Cartes("Onze", "Trad", "mage", "Viovio"))
        deck.add(Cartes("Douze", "Trad", "mage", "Rococo, rococo, rococo"))


        val view: View = inflater.inflate(frag_cartes, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_cartes)
        recyclerView.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = RecyclerAdapterCartes(deck)

        return view

        // reste à faire p-e sur Galaction: setScrollListener pour charger des images, et viewItemTouchListener ????
    }

    interface OnCarteSelected {
        fun onCarteSelected(carte: Cartes)
    }
}
