package com.example.planet_jumper

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.prototype_layout.*

class PrototypeJeu: AppCompatActivity(), View.OnClickListener {

    private var distanceParcourue: Float? = null
    val KEY_ICI: String = "ici"
    //private lateinit var distanceParcourue: Float

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.prototype_layout)

        distanceParcourue = intent.extras?.getFloat(KEY_ICI, 0F)
        //val dist = intent.extras?.getString(KEY_ICI, "défaut")?.toFloat()
        Log.d("PlaneteDistance", distanceParcourue.toString())


        setListener()
    }

    private fun setListener() {
        proto_gagner.blink()
        proto_gagner.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        val intent = Intent(this, Planete::class.java)
        intent.putExtra("gain", distanceParcourue)
        startActivity(intent)
    }
}