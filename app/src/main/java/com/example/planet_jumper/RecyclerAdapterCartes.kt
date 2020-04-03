package com.example.planet_jumper

import android.service.media.MediaBrowserService
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.carte_jeu_item.view.*

class RecyclerAdapterCartes(private val deck: ArrayList<Cartes>): RecyclerView.Adapter<RecyclerAdapterCartes.CartesHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapterCartes.CartesHolder {
        val inflatedView: View = parent.inflate(R.layout.carte_jeu_item, false)
        return CartesHolder(inflatedView)
    }


    override fun getItemCount(): Int = deck.size


    //val carte: Cartes = deck[position]


/*    override fun getItemCount(): Int {
        listeCartes.size
    }*/


    override fun onBindViewHolder(holder: RecyclerAdapterCartes.CartesHolder, position: Int) {
        //var carteHolder = holder as? CartesHolder
        //carteHolder?.setUpView(laCarte = getItem(position))
        val carte: Cartes = deck[position]
        holder.bindCarte(carte)
    }

    class CartesHolder(private val view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

        //private var imageView: ImageView = v.carteJeu_image
        //private val textView: TextView = view.text_view

        private var carte: Cartes? = null

        init {
            view.setOnClickListener(this)
        }

        fun bindCarte(carte: Cartes) {

            this.carte = carte
            view.carteJeu_titre.text = carte.nom
            view.carteJeu_description.text = carte.effet
            // à faire: remplir image et description aussi  ????

            /*fun setUpView(carte: Cartes) {
                this.carte = carte
                view.carteJeu_titre.text = carte.nom*/
            // à faire: remplir image et description aussi


            //laCarte?.nom?.let { imageView.setImageResource(it) }        // était resId, pas sûr que ça fonctionne carte.nom pour setImageResource...
            //textView.text = user?.name

        }

        override fun onClick(v: View?) {

            //val context = itemView.context
            Toast.makeText(v?.context, "Clic", Toast.LENGTH_LONG).show()


            //val showCarteIntent = Intent(context, P)

            //itemClickListener?.onItemClick(adapterPosition, v)
        }

    }

}

private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {

    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
