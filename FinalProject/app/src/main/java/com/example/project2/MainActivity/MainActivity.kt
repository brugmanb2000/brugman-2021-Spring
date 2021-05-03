package com.example.project2.MainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.project2.ItemActivity.Fragments.ItemActivityConnectionFragJoin
import com.example.project2.ItemActivity.Fragments.LogoFrag
import com.example.project2.ItemActivity.Fragments.RulesFragment
import com.example.project2.ItemActivity.ItemActivity
import com.example.project2.R

class MainActivity : AppCompatActivity() {


    companion object {
        fun newInstance(): MainActivity {
            return MainActivity()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val itemsButton = findViewById<Button>(R.id.itemsButton)
        val rulesButton = findViewById<Button>(R.id.rules_button)
        var switch = 0;
        val fragMan = supportFragmentManager.beginTransaction()
        fragMan.replace(R.id.main_frag_frame, LogoFrag())
        fragMan . commit ()

        itemsButton.setOnClickListener {
            val intent = Intent(this, ItemActivity.newInstance()::class.java)
            startActivity(intent)
            finish()
        }

        rulesButton.setOnClickListener {

            when (switch) {
                0 -> {
                    val fragMan = supportFragmentManager.beginTransaction()
                    fragMan.replace(R.id.main_frag_frame, RulesFragment())
                    fragMan . commit ()
                    switch = 1
                }

                1 -> {
                    val fragMan = supportFragmentManager.beginTransaction()
                    fragMan.replace(R.id.main_frag_frame, LogoFrag())
                    fragMan . commit ()
                    switch = 0

                }


            }
        }
    }
}