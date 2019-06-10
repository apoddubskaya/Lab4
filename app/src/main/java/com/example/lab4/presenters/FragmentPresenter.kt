package com.example.lab4.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.lab4.WeatherService
import com.example.lab4.views.FragmentIView


@InjectViewState
class FragmentPresenter: MvpPresenter<FragmentIView>() {

    init {
        refresh()
    }

    fun refresh() {
        WeatherService.getWeather(object : WeatherService.OnUpdateWeatherListener {
            override fun onFailureCallback() {
                Log.d("presenter", "getWeather fail")
            }

            override fun onResponseCallback(weatherList: List<WeatherService.WeatherItem>?) {
                Log.d("presenter", "getWeather success")
                viewState.showWeather(weatherList)
            }

        })
    }

}