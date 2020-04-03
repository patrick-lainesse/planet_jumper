package com.example.planet_jumper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager


class FragCartes : Fragment() {

    //private lateinit var linearLayoutManager: LinearLayoutManager

    companion object {

        fun newInstance(): FragCartes {
            return FragCartes()
        }
    }



/*    protected lateinit var rootView: View
    //lateinit var recyclerView: RecyclerView
    lateinit var adapter: RecyclerAdapterCartes

    companion object {

        var TAG = FragCartes::class.java.simpleName
        const val ARG_POSITION: String = "position"

        fun newInstance(): FragCartes {
            var fragment = FragCartes()
*//*            val args = Bundle()
            args.putInt(ARG_POSITION, 1)
            fragment.arguments = args       ???*//*
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //onCreateComponent()   ???
    }

    private fun onCreateComponent() {
        adapter = RecyclerAdapterCartes()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.frag_cartes, container, false)
        initView()

        return rootView
    }

    private fun initView() {
        setUpAdapter()
        initializeRecyclerView()
        setUpDummyData()
    }

    private fun setUpAdapter() {
        adapter.setOnItemClickListener(onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(position: Int, view: View?) {
                var carte = adapter.getItem(position)
                startActivity(context?.let {ctx -> carte?.let {
                    carte -> DetailsActivity.newIntent(ctx, carte)
                }
                })
            }
        })
    }

    private fun setUpDummyData() {
        var list: ArrayList<Cartes> = ArrayList<Cartes>()
        // ajouter cartes à la liste????
    }

    private fun initializeRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_cartes)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }*/

}

//https://medium.com/@info.anikdey003/kotlin-recyclerview-in-a-proper-and-re-usable-way-bb14717daa93
