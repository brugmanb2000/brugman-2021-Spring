package com.example.project2.ItemActivity.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.project2.R


class ItemActivityMovieList : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val searchedTitle = view?.findViewById<EditText>(R.id.editTextTextPersonName)
        val movieTitle =  view?.findViewById<TextView>(R.id.MovieActivitySearchTitle)
        val movieDescription = view?.findViewById<TextView>(R.id.MovieActivitySearchDescription)
        val addButton = view?.findViewById<Button>(R.id.ListItemAddButton)

        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_activity_item_list, container, false)
    }
 }