package com.example.unanimous

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.unanimous.ui.frag1.SharedViewModel


class MainActivity : AppCompatActivity() {

    private val viewModel: SharedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val cameraButton = findViewById<Button>(R.id.buttonCamera)
        val preferencesButton = findViewById<Button>(R.id.buttonPreferences)
        val selectionsButton = findViewById<Button>(R.id.buttonSelect)

        cameraButton.setOnClickListener {
            val intent = Intent(this, camera::class.java)
            startActivity(intent)
        }

        preferencesButton.setOnClickListener {
            val intent = Intent(this, FragAct::class.java)
            startActivity(intent)
        }

        selectionsButton.setOnClickListener {
            val intent = Intent(this, ApiCall::class.java)
            startActivity(intent)
        }

    }
}