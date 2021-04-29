package com.example.project2.ItemActivity.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.project2.ItemActivity.ViewModels.ItemViewModel
import com.example.project2.R
import kotlin.random.Random

class ItemActivityConnectionFragHost : Fragment() {

    companion object {
        fun newInstance(): ItemActivityConnectionFragHost {
            return ItemActivityConnectionFragHost()
        }
    }

    private val viewModel: ItemViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.item_activity_conn_host, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("Frag Host:", " Loaded")

        view?.findViewById<Button>(R.id.sessionPINButton)?.setOnClickListener {
            val pin = Random.nextInt(100000, 999999)
            val sessionPIN = "Session PIN: $pin"
            view?.findViewById<TextView>(R.id.sessionPINEditText2)?.text = sessionPIN
            viewModel.addPIN(pin)
        }
    }

}