package com.example.hw11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val randomNumButton = findViewById<Button>(R.id.randomNumberButton)
        val imageButton = findViewById<Button>(R.id.imageViewerButton)
        randomNumButton.setOnClickListener {
            val intent = Intent(this@MainActivity, RandomNumber::class.java)
            startActivity(intent)
        }

        imageButton.setOnClickListener {
            val intent = Intent(this@MainActivity, ImageViewer::class.java)
            startActivity(intent)
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