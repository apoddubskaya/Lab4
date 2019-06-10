package com.example.lab4.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.lab4.WeatherService

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FragmentOneIView : MvpView {
    fun showWeather(weatherList : List<WeatherService.WeatherItem>?)
}