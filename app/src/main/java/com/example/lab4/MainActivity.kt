package com.example.lab4

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

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

        Log.d(TAG, "onCreateActivityBegin")
        val frag = FragmentOne()
        Log.d(TAG, "onCreateActivityCrateFragment")
        supportFragmentManager.beginTransaction()
            .replace(R.id.frag1, frag)
            .commit()
        Log.d(TAG, "onCreateActivityAfterCommit")
    }
}
