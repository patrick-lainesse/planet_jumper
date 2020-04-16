package com.example.planet_jumper

import android.os.Bundle
import androidx.core.widget.ImageViewCompat
//import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class ListerActivity : AppCompatActivity() {
    //implements View.OnClickListener {
    var liste: ListView? = null

    var planete: String? = null
    var distance: String? = null
    var erreur: String? = null

    //var planetes: Array<String>? = null
    var distances: Array<String>? = null

    val planetes: MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lister)
        liste = findViewById<View>(R.id.liste) as ListView
        lister()
    }

    fun lister() {
        val tabLivres = ArrayList<HashMap<String, Any?>>()

        val tabPlanetes = ArrayList<HashMap<String, Any?>>()

        //val url = "http://10.0.2.2/Exemple_Controleur_Android/PHP/livresControleurJSON.php"
        val url = "https://exoplanetarchive.ipac.caltech.edu/cgi-bin/nstedAPI/nph-nstedAPI?&table=exoplanets&format=JSON&where=st_dist%3C10%20and%20pl_letter%20like%20%27b%27&select=pl_hostname,st_dist"
        val requete: JsonArrayRequest = object : JsonArrayRequest(Method.GET, url, null,
                Response.Listener { response ->
                    try {
                        Log.d("RESULTAT", response.getString(0))

                        Log.d("METEOinit", "try")
                        //Log.d("RESULTAT", response)
                        //val fEntree = con.inputStream
                        //val entree = BufferedReader(InputStreamReader(fEntree))
                        /*val entree = BufferedReader(response)
                        var ligne: String?
                        while (entree.readLine().also { ligne = it } != null) {
                            Log.d("METEOlist", ligne)
                            pageJson += ligne
                        }
                        entree.close() //Fermer les readers et le inputStream*/

                        //val objetsJson = JSONArray(response)

                        var map: HashMap<String, Any?>

                        // Loop through the array elements
                        for(i in 0 until response.length()) {
                            // Get current json object

                            planete = response.getJSONObject(i).getString("pl_hostname")
                            distance = response.getJSONObject(i).getString("st_dist")

                            planetes.add(response.getJSONObject(i).getString("pl_hostname"))
                            Log.d("METEOjson", response.getJSONObject(i).getString("pl_hostname"))

                            map = HashMap()
                            map["planete"] = planete
                            map["distance"] = distance
                            map["ici"] = distance
                            tabPlanetes.add(map)
                        }

                        val monAdapter = SimpleAdapter(this@ListerActivity, tabPlanetes, R.layout.rangee_planete, arrayOf("planete", "distance", "ici"), intArrayOf(R.id.card_nom_planete, R.id.card_distance_gaia, R.id.card_distance_ici))
                        liste!!.adapter = monAdapter
                        //val iv: ImageViewCompat


                        /*var i: Int
                        var j: Int
                        //val jsonResponse = JSONArray(response)
                        var map: HashMap<String, Any?>
                        val msg = response.getString(0)
                        if (msg == "OK") {
                            var unLivre: JSONObject
                            i = 1
                            while (i < response.length()) {
                                unLivre = response.getJSONObject(i)
                                map = HashMap()
                                j = i % 7 //m0.jpg, ...,m6.jpg round robin
                                val nomImage = "m$j"
                                 byte[] decodedString = Base64.decode(unLivre.getString("image"), Base64.DEFAULT);
                                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                            Drawable d = new BitmapDrawable(getResources(),decodedByte);
                                            map.put("Image", d);
                                            if (i==3)
                                                map.put("img", d);
                                            elsemap["img"] = resources.getIdentifier(nomImage, "drawable", packageName).toString()
                                map["idl"] = unLivre.getString("idlivre")
                                map["mtitre"] = unLivre.getString("titre")
                                map["mauteur"] = unLivre.getString("auteur")
                                map["mannee"] = unLivre.getString("annee")
                                map["mpages"] = unLivre.getString("pages")
                                tabLivres.add(map)
                                i++
                            }
                            val monAdapter = SimpleAdapter(this@ListerActivity, tabLivres, R.layout.lister_livres_map, arrayOf("img", "idl", "mtitre", "mauteur", "mannee", "mpages"), intArrayOf(R.id.img, R.id.idl, R.id.mtitre, R.id.mauteur, R.id.mannee, R.id.mpages))
                            liste!!.adapter = monAdapter
                        } else {
                        }*/
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { error -> Toast.makeText(this@ListerActivity, error.message, Toast.LENGTH_SHORT).show() }
        ) {
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                // Les parametres pour POST
                params["action"] = "lister"
                return params
            }
        }
        Volley.newRequestQueue(this).add(requete) //Si Volley rouge clique Volley et choisir add dependency on module volley
    }
}