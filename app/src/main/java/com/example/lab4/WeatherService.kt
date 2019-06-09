package com.example.lab4

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object WeatherService {
    private val TAG = "WeatherService"

    data class WeatherItem(
        val date: String,
        val feel: String,
        var tod: String,
        val temp: String,
        val wind: String,
        val cloud: String,
        val humidity: String,
        val pressure: String
    )
    var weatherList : List<WeatherItem>? = null

    interface ApiHelper {
        @GET("meteo.php")
        fun getWeatherInfo(@Query("tid") tid: Int): Call<List<WeatherItem>>
    }

    fun getWeatherCall(): Call<List<WeatherItem>> {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://icomms.ru/inf/")

            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiHelper::class.java).getWeatherInfo(25)
    }
}