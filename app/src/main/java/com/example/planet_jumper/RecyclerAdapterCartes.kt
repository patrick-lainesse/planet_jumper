package com.example.planet_jumper

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.carte_jeu.view.*

class RecyclerAdapterCartes(private val deck: ArrayList<Cartes>): RecyclerView.Adapter<RecyclerAdapterCartes.CartesHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapterCartes.CartesHolder {
        val inflatedView: View = parent.inflate(R.layout.carte_jeu, false)
        return CartesHolder(inflatedView)
    }

/*    override fun getItemCount(): Int {
        listeCartes.size
    }*/


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var carteHolder = holder as? CartesHolder
        carteHolder?.setUpView(laCarte = getItem(position))
    }

    class CartesHolder(private val view: View): RecyclerView.ViewHolder(v), View.OnClickListener {

        //private var imageView: ImageView = v.carteJeu_image
        //private val textView: TextView = view.text_view

        private var carte: Cartes? = null

        init {
            view.setOnClickListener(this)
        }

        fun setUpView(carte: Cartes) {
            this.carte = carte
            view.carteJeu_titre.text = carte.nom
            // à faire: remplir image et description aussi


            //laCarte?.nom?.let { imageView.setImageResource(it) }        // était resId, pas sûr que ça fonctionne carte.nom pour setImageResource...
            //textView.text = user?.name

        }

        override fun onClick(v: View?) {

            val context = itemView.context
            //val showCarteIntent = Intent(context, P)

            //itemClickListener?.onItemClick(adapterPosition, v)
        }

    }

    override fun onBindViewHolder(holder: CartesHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}
