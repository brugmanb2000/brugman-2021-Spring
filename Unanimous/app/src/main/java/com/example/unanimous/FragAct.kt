package com.example.unanimous

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.unanimous.ui.frag1.Frag1
import com.example.unanimous.ui.frag1.Frag2
import com.example.unanimous.ui.frag1.SharedViewModel

class FragAct : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragmain)
        val viewModel: SharedViewModel by viewModels()
        val reset = findViewById<Button>(R.id.resetFragMain)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragContainer1, Frag1(), "frag1")
                .commitNow()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragContainer2, Frag2(), "frag2")
                .commitNow()
        }

        reset.setOnClickListener {
            Log.e("Reset Button", "Reset Button Pressed!!")
            viewModel.word1 = ""
            viewModel.word2 = ""
            val frag1 = supportFragmentManager.findFragmentByTag("frag1")
            if (frag1 != null) {
                supportFragmentManager.beginTransaction().remove(frag1).commitNow()
            }
            val frag2 = supportFragmentManager.findFragmentByTag("frag2")
            if (frag2 != null) {
                supportFragmentManager.beginTransaction().remove(frag2).commitNow()
            }
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragContainer1, Frag1(), "frag1")
                    .commitNow()
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragContainer2, Frag2(), "frag2")
                    .commitNow()
            }
        }
    }
}
