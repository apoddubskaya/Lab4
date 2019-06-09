package com.example.lab4

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
            finish()
        else {
            val fragment = FragmentTwo()
            fragment.setPosition(intent!!.getIntExtra("position", 0))
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragDetail, fragment)
                .commit()
        }
    }
}
