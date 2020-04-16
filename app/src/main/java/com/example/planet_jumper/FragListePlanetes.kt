package com.example.planet_jumper

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_lister.*
import org.json.JSONException
import java.util.ArrayList
import java.util.HashMap

class FragListePlanetes: Fragment() {

    var liste: ListView? = null
    lateinit var planete: String
    lateinit var distance: String
    //val planetes: MutableList<String> = ArrayList()

    companion object {
        fun newInstance(): FragListePlanetes {
            return FragListePlanetes()
        }
    }

    // méthode qui récupère le contexte de l'activité mère
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // récupère une référence sur le listeview et lance la méthode pour le remplir d'informations
        val view: View = inflater.inflate(R.layout.activity_lister, container, false)
        liste = view.findViewById<View>(R.id.liste) as ListView
        lister()

        return view
    }

    // affiche les infos des planètes dans le listview
    fun lister() {

        //val tabPlanetes = ArrayList<HashMap<String, Any?>>()
        val tabPlanetes = ArrayList<HashMap<String, String>>()

        val url = "https://exoplanetarchive.ipac.caltech.edu/cgi-bin/nstedAPI/nph-nstedAPI?&table=exoplanets&format=JSON&where=st_dist%3C10%20and%20pl_letter%20like%20%27b%27&select=pl_hostname,st_dist"
        val requete: JsonArrayRequest = object : JsonArrayRequest(
            Method.GET, url, null,
            Response.Listener { response ->
                try {
                    Log.d("LogReponseRequest", response.getString(0))

                    var map: HashMap<String, String>

                    // Loop through the array elements
                    for(i in 0 until response.length()) {
                        // obtenir les informations de la requête ligne par ligne
                        planete = response.getJSONObject(i).getString("pl_hostname")
                        distance = response.getJSONObject(i).getString("st_dist")

                        //planetes.add(response.getJSONObject(i).getString("pl_hostname"))
                        //Log.d("LogObjetJson(i)", response.getJSONObject(i).getString("pl_hostname"))

                        map = HashMap()
                        map["planete"] = planete
                        map["distance"] = distance
                        map["ici"] = distance
                        tabPlanetes.add(map)
                    }

                    // trier le ArrayList en fonction de la distance à la position du joueur dans l'Univers ????
                    //val sortedList = mylist.sortedWith(compareBy({ it.get("label") }))
                    //val listeClassee = tabPlanetes.sortBy { "ici" }
                    val listeClassee = tabPlanetes.sortedWith(compareBy { "distance" })

                    // https://stackoverflow.com/questions/27865113/different-ways-of-sorting-arraylist-of-of-hashmapstring-string
/*



                    Change the implements to Comparator<Hashmap<String,String>> and use:

                    public int compare(HashMap<String, String>> lhs,
                        HashMap<String, String>> rhs) {
                        return Integer.compare(Integer.parseInt(lhs.get("number")),
                            Integer.parseInt(rhs.get("number")));
                    }

                    I assume you meant a list of (Hash)maps.
*/


                    val monAdapter = SimpleAdapter(context, listeClassee, R.layout.rangee_planete, arrayOf("planete", "distance", "ici"), intArrayOf(R.id.card_nom_planete, R.id.card_distance_gaia, R.id.card_distance_ici))
                    liste!!.adapter = monAdapter

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error -> Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show() }
        ) {
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                // Les parametres pour POST
                params["action"] = "lister"
                return params
            }
        }
        Volley.newRequestQueue(context).add(requete) //Si Volley rouge clique Volley et choisir add dependency on module volley
    }
}