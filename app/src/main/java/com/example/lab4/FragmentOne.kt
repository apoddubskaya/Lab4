package com.example.lab4

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_one.*

class FragmentOne : Fragment() {

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false)
    }

    private val TAG = "FragmentOne"

    private fun setWeekInfo() {
        Log.d(TAG, "setWeekInfo")
        if (WeatherService.weatherList == null) {
            Log.d(TAG, "null list data")
            return
        }
        val items = ArrayList<String>()
//        for (i in 1..14)
//            items.add(i.toString())
        val wlist = WeatherService.weatherList!!
        for (i in 0 until wlist.size step 4)
            items.add(wlist[i].date)

        list.adapter = ArrayAdapter<String>(
            activity!!.applicationContext,
            android.R.layout.simple_list_item_1,
            items)

        list.onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            listener!!.onListFragmentInteraction(position)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setWeekInfo()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(position: Int)
    }
}
