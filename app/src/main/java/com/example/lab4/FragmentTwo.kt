package com.example.lab4

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.example.lab4.presenters.FragmentPresenter
import com.example.lab4.views.FragmentIView
import kotlinx.android.synthetic.main.fragment_two.*

class FragmentTwo : MvpAppCompatFragment(), FragmentIView {

    @InjectPresenter(type = PresenterType.GLOBAL, tag = "commonPresenter")
    lateinit var presenter: FragmentPresenter

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
        this.position = position
    }

    override fun showWeather(weatherList: List<WeatherService.WeatherItem>?) {
        Log.d(TAG, "frag2 set " + position + " info")
        val currDayPosition = position * 4
        val items = ArrayList<String>(listOf(
            weatherList!![currDayPosition].date,
            "\nНочь\n",
            "\nУтро\n",
            "\nДень\n",
            "\nВечер\n"
            )
        )

        for (i in 0..3) {
            items[i + 1] += "\nТемпература   " + weatherList[currDayPosition + i].temp + "C"
            items[i + 1] += "\nВетер   " + weatherList[currDayPosition + i].wind
            items[i + 1] += "\nДавление   " + weatherList[currDayPosition + i].pressure + "мм"
            items[i + 1] += "\nВлажность   " + weatherList[currDayPosition + i].humidity + "%"
        }

        DayWeatherList.adapter = ArrayAdapter<String>(
            activity!!.applicationContext,
            android.R.layout.simple_list_item_1,
            items
        )
    }
}
