package com.example.hw11

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import id.co.ionsoft.randomnumberanimationlibrary.RandomNumberAnimation
import kotlinx.coroutines.delay
import kotlin.random.Random

class RandomNumber : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.randonnum)
        setSupportActionBar(findViewById(R.id.toolbar))
        val startRandomButton = findViewById<Button>(R.id.startRandomButton)
        val stopRandomButton = findViewById<Button>(R.id.stopRandomButton)
        startRandomButton.setOnClickListener {
            val numViewer = findViewById<TextView>(R.id.numViewer)
            val random = Random.Default
            var value = random.nextInt(1, 20)
            numViewer.setText(value.toString())
        }

        stopRandomButton.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()


    }


    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}