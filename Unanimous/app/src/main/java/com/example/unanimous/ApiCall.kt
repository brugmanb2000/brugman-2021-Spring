package com.example.unanimous

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.unanimous.ApiServices.MovieService


class ApiCall : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_call)
        val baseURL = "https://api.themoviedb.org/3/"

        val movieTitle = findViewById<TextView>(R.id.movieTitle)
        val movieOverview = findViewById<TextView>(R.id.movieOverview)
        val setTitle = findViewById<EditText>(R.id.movieSearch)
        val searchButton = findViewById<Button>(R.id.searchButton)
        val poster = findViewById<ImageView>(R.id.posterImage)

        searchButton.setOnClickListener {
            Log.e("Title Test", setTitle.text.toString())
            MovieService.getMovie(setTitle.text.toString())
            movieTitle.text = MovieService.currentTitle as CharSequence
            movieOverview.text = MovieService.currentDescription as CharSequence
        }
    }

}
