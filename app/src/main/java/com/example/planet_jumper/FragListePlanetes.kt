package com.example.planet_jumper

import android.content.Context
import android.content.Intent
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

/* En utilisant Volley, se connecte au site des exoplanètes chapeauté par la Nasa et obtient des tableaux json contenant le nom des planètes
et leur distance au satellite Gaia (situé à mi-distance entre la Terre et le Soleil). Redéfinit la méthode Comparator pour
trier un arraylist de hashmap */
class FragListePlanetes: Fragment() {

    private var distanceCumul: Float = 0.0F
    var listView: ListView? = null
    lateinit var adapter: SimpleAdapter
    lateinit var planete: String
    lateinit var distance: String
    val KEY_PLANETE: String = "planete"
    val KEY_DISTANCE: String = "distance"
    val KEY_ICI: String = "ici"

    companion object {
        @JvmStatic
        fun newInstance(distanceCumul: Float) = FragListePlanetes().apply {
            arguments = Bundle().apply {
                putFloat(KEY_ICI, distanceCumul)
            }
        }

        fun newInstance(): FragListePlanetes {
            return FragListePlanetes()
        }
    }

    // méthode qui récupère le contexte de l'activité mère, nécessaire pour d'autres fonctions
    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getFloat(KEY_ICI)?.let { distanceCumul = it }
        Log.d("logOnAttach", distanceCumul.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // récupère une référence sur le listeview du fragment et lance la méthode pour le remplir d'informations
        val view: View = inflater.inflate(R.layout.frag_planetes, container, false)
        listView = view.findViewById<View>(R.id.liste) as ListView
        listerConnexion()
        setAdapter()

        return view
    }

    private fun setAdapter() {
        listView?.setOnItemClickListener { parent, view, position, id ->

            // récupère le choix effectué dans le ListView et parse son résultat pour obtenir le float nécessaire au calcul
            val choix = adapter.getItem(position)
            // exemple d'un choix.toString: {planete=YZ Cet, distance=3.6. ici=3.6}
            // pour éviter le bogue sur Linux/MotoG3, il faut plutôt utiliser la ligne suivante:
            //val posAccolade = choix.toString().indexOf(',')
            val posEgal = choix.toString().indexOf("ici=")
            val posAccolade = choix.toString().indexOf('}')
            val distanceGaia = choix.toString().substring(posEgal+4, posAccolade).toFloat() + distanceCumul

            // démarre l'activité du jeu en lui passant la distance parcourue jusqu'à présent (distance qui sépare le joueur du satellite Gaia)
            val intent = Intent(context, PrototypeJeu::class.java)
            intent.putExtra(KEY_ICI, distanceGaia)
            startActivity(intent)
        }
    }

    // méthode principale
    fun listerConnexion() {

        val tabPlanetes = ArrayList<HashMap<String, String>>()

        /* L'url agit comme déterminant de la requête qu'on cherche à faire dans la database des exoplanètes
            Paramètres intéressants:
                            - &format=JSON      format du tableau retourné, ce qui permet d'utiliser directement la réponse (c'est un JSONArray)
                            - &where=st_dist%3C10%20    signifie: distance de Gaia < 10. C'est ce paramètre qu'il faudra changer chaque fois qu'on voyage dans le jeu, cette distance doit augmenter au fil de l'aventure
                            - and%20pl_letter%20like%20%27b%27      plusieurs planètes se retrouvent souvent dans le même système, avec un nom et une distance identiques,
                                                                    mais identifiés avec un code de lettres: b pour la plus proche de l'étoile, c, d... On sélectionne seulement la première
                            - &select=pl_hostname,st_dist           les colonnes du tableau que l'on veut obtenir */
        //val url = "https://exoplanetarchive.ipac.caltech.edu/cgi-bin/nstedAPI/nph-nstedAPI?&table=exoplanets&format=JSON&where=st_dist%3C10%20and%20pl_letter%20like%20%27b%27&select=pl_hostname,st_dist"
        val urlP1 = "https://exoplanetarchive.ipac.caltech.edu/cgi-bin/nstedAPI/nph-nstedAPI?&table=exoplanets&format=JSON&where=st_dist"
        val urlP3 = "%20and%20pl_letter%20like%20%27b%27&select=pl_hostname,st_dist"
        // pour

        // La première distance envoyée dans une requête est de 10 parsecs
        var distanceScope: Float = 10F
        // %3C dans la requête signifie <
        //var urlP2 = "%3C" + distanceScope.toString()
        var urlP2 = "<" + distanceScope.toString()

        // pour les requêtes subséquentes, on demande toutes les planètes à au moins 10 parsecs de la dernière planète à avoir été visitée
        if(distanceCumul > 0) {
            distanceScope += distanceCumul
            urlP2 = ">" + distanceCumul + "and st_dist<" + (distanceCumul + 100)
            //Log.d("logBrackets", distanceScope.toString())
        }
        Log.d("logFragPlus", distanceScope.toString())


        val url = urlP1 + urlP2 + urlP3
        val requete: JsonArrayRequest = object : JsonArrayRequest(
            Method.GET, url, null,
            Response.Listener { response ->
                try {
                    Log.d("LogReponseRequest", response.getString(0))

                    // ??? peut-être mettre le deuxième paramètre en Float et fixed 2 chiffres après la décimale pour faciliter l'affichage
                    var map: HashMap<String, String>

                    /* On parcourt chacun des objets json de l'arraylist obtenu en réponse, et on crée un hashmap pour chacun, qu'on place dans un arraylist de map
                    ce qui permettra de le trier plus tard et de le passer à un SimpleAdapter pour l'afficher dans le fragment */
                    for(i in 0 until response.length()) {
                        // obtenir les informations de la requête ligne par ligne
                        planete = response.getJSONObject(i).getString("pl_hostname")
                        distance = response.getJSONObject(i).getString("st_dist")

                        // ??? décider plus tard si le map 'ici' est nécessaire ou si le calcul va se faire ailleurs, idem pour l'url et les params
                        map = HashMap()
                        map[KEY_PLANETE] = planete
                        map[KEY_DISTANCE] = distance
                        map[KEY_ICI] = distance
                        tabPlanetes.add(map)
                    }

                    // trier le ArrayList en fonction de la distance à la position du joueur dans l'Univers
                    tabPlanetes.sortWith(Comparator { m1: HashMap<String, String>, m2: HashMap<String, String> -> m1[KEY_ICI]!!.compareTo(
                        m2.get(KEY_ICI).toString()
                    ) })

                    val tabPlanetesReduit = tabPlanetes.take(10)

                    adapter = SimpleAdapter(context, tabPlanetesReduit,
                                    R.layout.rangee_planete, arrayOf(KEY_PLANETE, KEY_DISTANCE, KEY_ICI),
                                    intArrayOf(R.id.card_nom_planete, R.id.card_distance_gaia, R.id.card_distance_ici))

                    listView!!.adapter = adapter

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error -> Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show() }
        ) {
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["action"] = "listerConnexion()"
                return params
            }
        }
        // ajoute la présente requête à la queue de requêtes de Volley
        Volley.newRequestQueue(context).add(requete)
    }
}