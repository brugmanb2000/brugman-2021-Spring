package com.example.hw11

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class ImageViewer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_viewer)
        setSupportActionBar(findViewById(R.id.toolbar))
        val image = findViewById<ImageView>(R.id.imageView)
        image.setImageResource(R.drawable.arnold)

        val pumpUp = findViewById<Button>(R.id.pumpUp)
        val pumpDown = findViewById<Button>(R.id.pumpDown)
        val back = findViewById<Button>(R.id.Back)
        pumpUp.setOnClickListener {
            val progressBar = findViewById<ProgressBar>(R.id.progressBar)
            var progress = progressBar.getProgress()
            if (progress <= 100) {
                progress = progress + 10
                progressBar.setProgress(progress)
            }
            if (progress == 100) {
                val image = findViewById<ImageView>(R.id.imageView)
                image.setImageResource(R.drawable.predator_handshake)
            }
        }

        back.setOnClickListener{
            finish()
        }

        pumpDown.setOnClickListener {
            val progressBar = findViewById<ProgressBar>(R.id.progressBar)
            var progress = progressBar.getProgress()
            if (progress > 0) {
                progress = progress - 10
                progressBar.setProgress(progress)
            }
            if (progress != 100) {
                val image = findViewById<ImageView>(R.id.imageView)
                image.setImageResource(R.drawable.arnold)
            }
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