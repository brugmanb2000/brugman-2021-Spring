package com.example.project2.ItemActivity.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import com.example.project2.ItemActivity.ItemActivity
import com.example.project2.ItemActivity.ViewModels.ItemViewModel
import com.example.project2.R

class ItemActivityConnectionFragJoin : Fragment() {

    companion object {
        fun newInstance(): ItemActivityConnectionFragJoin {
            return ItemActivityConnectionFragJoin()
        }
    }

    private val viewModel: ItemViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.removePlayer()
                val intent = Intent(activity, ItemActivity.newInstance()::class.java)
                startActivity(intent)
                activity!!.finish()
            }
        })
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.item_activity_conn_join, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("Frag Join:", " Loaded")

        val sessionID = view?.findViewById<EditText>(R.id.sessionPINEditText2)

        sessionID?.setOnClickListener {
            sessionID.setText("")
        }

    }



}