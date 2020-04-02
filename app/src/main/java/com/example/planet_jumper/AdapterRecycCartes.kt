package com.example.planet_jumper

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.carte_jeu.view.*

class AdapterRecycCartes: BaseRecyclerViewAdapter<Cartes>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return CartesHolder(LayoutInflater.from(parent?.context).inflate(R.layout.carte_jeu, parent, false))
    }

/*    override fun getItemCount(): Int {
        listeCartes.size
    }*/


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var carteHolder = holder as? CartesHolder
        carteHolder?.setUpView(laCarte = getItem(position))
    }

    inner class CartesHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener {

        private var imageView: ImageView = v.carteJeu_image
        //private val textView: TextView = view.text_view

        private var carte: Cartes? = null

        init {
            v.setOnClickListener(this)
        }

        fun setUpView(laCarte: Cartes?) {
            laCarte?.nom?.let { imageView.setImageResource(it) }        // était resId, pas sûr que ça fonctionne carte.nom pour setImageResource...
            //textView.text = user?.name

        }

        override fun onClick(v: View?) {
            itemClickListener?.onItemClick(adapterPosition, v)
        }

    }
}
