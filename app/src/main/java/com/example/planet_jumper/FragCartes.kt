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

    private lateinit var listener: OnCarteSelected  // à implémenter, va lancer la partie???
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
        //deck = dbhelper.lireCartes()

/*
if(context is OnCarteSelected) {    //???    va devoir implémenter pour afficher plus d'infos sur chaque carte et un flavor text?
            listener = context
        } else {
            throw ClassCastException(context.toString() + " doit implémenter OnCarteSelected.")
        }

        // obtenir les données propres aux cartes
        val resources = context.resources
        // doit implémenter des arraylist des noms de cartes, mais doit réfléchir à comment peupler ces AList?????
        selon les cartes que possède le joueur à un moment donné

        noms = resources.getStringArray(R.array.names)
        descriptions
        urls

        obtenir les images
*/

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        deck = dbhelper.lireCartes()
        deck.add(Cartes(1000,"Une", "Trad", "mage", "Allo"))

        val view: View = inflater.inflate(frag_cartes, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_cartes)
        recyclerView.layoutManager = LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = RecyclerAdapterCartes(deck)

        return view
    }

    // ??? à implémenter éventuellement
    interface OnCarteSelected {
        fun onCarteSelected(carte: Cartes)
    }
}
