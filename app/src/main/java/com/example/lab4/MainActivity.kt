package com.example.lab4

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), FragmentOne.OnListFragmentInteractionListener{

    private val TAG = "MainActivity"

    override fun onListFragmentInteraction(position: Int) {

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val fragment = FragmentTwo()
            fragment.setPosition(position)
            supportFragmentManager.beginTransaction()
                .replace(R.id.frag2, fragment)
                .commit()
        }
        else {
            val myIntent = Intent(applicationContext, SecondActivity::class.java)
            myIntent.putExtra("position", position)
            startActivity(myIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "try get W")
        val call = WeatherService.getWeatherCall()
        call.enqueue(object : Callback<List<WeatherService.WeatherItem>> {
            override fun onFailure(call: Call<List<WeatherService.WeatherItem>>, t: Throwable) {
                Log.d(TAG, "Fail: " + t.toString())
            }

            override fun onResponse(
                call: Call<List<WeatherService.WeatherItem>>,
                response: Response<List<WeatherService.WeatherItem>>
            ) {
                if (response.code() == 200) {
                    WeatherService.weatherList = response.body()!!
                    val str: String = Gson().toJson(WeatherService.weatherList)
                    Log.d(TAG, str)
                    val fragment = FragmentOne()
                    Log.d(TAG, "create frag list")
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frag1, fragment)
                        .commit()
                }
            }
        })
    }

}
