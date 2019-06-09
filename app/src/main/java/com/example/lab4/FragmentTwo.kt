package com.example.lab4

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_two.*

class FragmentTwo : Fragment() {

    private val TAG = "FragmentTwo"

    private var position = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two, container, false)
    }

    fun setPosition(position : Int) {
        Log.d(TAG, "set pos" + position)
        this.position = position
    }

    private fun setDayInfo() {
        Log.d(TAG, "frag2 set " + position + " info")
        val items = ArrayList<String>()
        items.add(WeatherService.weatherList!![position * 4].date)
        items.add("Ночь")
        items.add("Утро")
        items.add("День")
        items.add("Вечер")

        DayWeatherList.adapter = ArrayAdapter<String>(
            activity!!.applicationContext,
            android.R.layout.simple_list_item_1,
            items
        )
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setDayInfo()

    }
}
