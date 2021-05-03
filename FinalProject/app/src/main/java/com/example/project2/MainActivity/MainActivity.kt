package com.example.project2.MainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.project2.ItemActivity.Fragments.ItemActivityConnectionFragJoin
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

        itemsButton.setOnClickListener {
            val intent = Intent(this, ItemActivity.newInstance()::class.java)
            startActivity(intent)
            finish()
        }

        rulesButton.setOnClickListener {
            var fragMan = supportFragmentManager.beginTransaction()
            fragMan.replace(R.id.main_frag_frame, RulesFragment())
            fragMan.commit()
        }

    }
}