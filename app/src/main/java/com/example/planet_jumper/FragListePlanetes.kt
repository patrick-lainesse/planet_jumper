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
import org.json.JSONException
import java.util.*

/* Se connecte au site de la Nasa par Volley et obtient des tableaux json contenant le nom des planètes et leur
distance au satellite Gaia (situé à mi-distance entre la Terre et le Soleil). Redéfinit la méthode Comparator pour
trier un arraylist de hashmap */
class FragListePlanetes: Fragment() {

    var liste: ListView? = null
    lateinit var planete: String
    lateinit var distance: String
    val KEY_PLANETE: String = "planete_activity"
    val KEY_DISTANCE: String = "distance"
    val KEY_ICI: String = "ici"

    companion object {
        fun newInstance(): FragListePlanetes {
            return FragListePlanetes()
        }
    }

    // méthode qui récupère le contexte de l'activité mère, nécessaire pour d'autres fonctions
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // récupère une référence sur le listeview du fragment et lance la méthode pour le remplir d'informations
        val view: View = inflater.inflate(R.layout.frag_planetes, container, false)
        liste = view.findViewById<View>(R.id.liste) as ListView
        lister()

        return view
    }

    // méthode principale
    fun lister() {

        val tabPlanetes = ArrayList<HashMap<String, String>>()

        /* L'url agit comme déterminant de la requête qu'on cherche à faire dans la database des exoplanètes
            Paramètres intéressants:
                            - &format=JSON      format du tableau retourné, ce qui permet d'utiliser directement la réponse (c'est un JSONArray)
                            - &where=st_dist%3C10%20    signifie: distance de Gaia < 10. C'est ce paramètre qu'il faudra changer chaque fois qu'on voyage dans le jeu, cette distance doit augmenter au fil de l'aventure
                            - and%20pl_letter%20like%20%27b%27      plusieurs planètes se retrouvent souvent dans le même système, avec un nom et une distance identiques,
                                                                    mais identifiés avec un code de lettres: b pour la plus proche de l'étoile, c, d... On sélectionne seulement la première
                            - &select=pl_hostname,st_dist           les colonnes du tableau que l'on veut obtenir */
        val url = "https://exoplanetarchive.ipac.caltech.edu/cgi-bin/nstedAPI/nph-nstedAPI?&table=exoplanets&format=JSON&where=st_dist%3C10%20and%20pl_letter%20like%20%27b%27&select=pl_hostname,st_dist"
        val requete: JsonArrayRequest = object : JsonArrayRequest(
            Method.GET, url, null,
            Response.Listener { response ->
                try {
                    Log.d("LogReponseRequest", response.getString(0))

                    // ??? peut-être mettre le deuxième paramètre en Int et fixed 2 chiffres après la décimale pour faciliter l'affichage
                    var map: HashMap<String, String>

                    /* On parcourt chacun des objets json de l'arraylist obtenu en réponse, et on crée un hashmap pour chacun, qu'on place dans un arraylist de map
                    ce qui permettra de le trier plus tard et de le passer à un SimpleAdapter pour l'afficher dans le fragment */
                    for(i in 0 until response.length()) {
                        // obtenir les informations de la requête ligne par ligne
                        planete = response.getJSONObject(i).getString("pl_hostname")
                        distance = response.getJSONObject(i).getString("st_dist")

                        // ??? décider plus tard si le map 'ici' est nécessaire ou si le calcul va se faire ailleurs, idem pour l'url et les params
                        map = HashMap()
                        map["planete_activity"] = planete
                        map["distance"] = distance
                        map["ici"] = distance
                        tabPlanetes.add(map)
                    }

                    // trier le ArrayList en fonction de la distance à la position du joueur dans l'Univers
                    tabPlanetes.sortWith(Comparator { m1: HashMap<String, String>, m2: HashMap<String, String> -> m1["ici"]!!.compareTo(
                        m2.get("ici").toString()
                    ) })

                    val tabPlanetesReduit = tabPlanetes.take(10)

                    val monAdapter = SimpleAdapter(context, tabPlanetesReduit, R.layout.rangee_planete, arrayOf("planete_activity", "distance", "ici"), intArrayOf(R.id.card_nom_planete, R.id.card_distance_gaia, R.id.card_distance_ici))
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
        Volley.newRequestQueue(context).add(requete)
    }
}