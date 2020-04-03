package com.example.planet_jumper

import android.content.Context
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.ClassCastException


class FragCartes : Fragment() {

    //private lateinit var linearLayoutManager: LinearLayoutManager


    private var listener: OnCarteSelected

    companion object {

        fun newInstance(): FragCartes {
            return FragCartes()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if(context is OnCarteSelected) {    //???
            listener = context
        } else {
            throw ClassCastException(context.toString() + " doit implémenter OnCarteSelected.")
        }

        // obtenir les données propres aux cartes
        val resources = context.resources
        /* doit implémenter des arraylist des noms de cartes, mais doit réfléchir à comment peupler ces AList
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
        val view: View = inflater.inflate(R.layout.carte_jeu_item, container, false)
        val activity = activity as Context
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_cartes)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = ListeCartesAdapter(activity)
        return view
    }

    internal inner class ListeCartesAdapter(context: Context) : RecyclerView.Adapter<ViewHolder>() {

        private val layoutInflater = LayoutInflater.from(context)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val recyclerCartesBinding = RecyclerCartesModelBinding.inflate(layoutInflater, viewGroup, false)
            return ViewHolder(recyclerCartesBinding.root, recyclerCartesBinding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val carte = Cartes() // ??? remplir les infos des cartes à partir de tableau
            holder.setData(carte)
            holder.itemView.setOnClickListener {listener.onCarteSelected(carte)}
        }

        override fun getItemCount() = names.size // ??? voir names plus haut, à implémenter arraylist
    }

    internal inner class ViewHolder constructor(itemView: View, private val recyclerCartesModelBinding: RecyclerCartesModelBinding) : RecyclerView.ViewHolder(itemView) {

        fun setData(carte: Cartes) {
            recyclerCartesModelBinding.carte = carte
        }

    }

    interface OnCarteSelected {
        fun onCarteSelected(carte: Cartes)
    }
}
