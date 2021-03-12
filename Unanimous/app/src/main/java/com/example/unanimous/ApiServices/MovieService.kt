package com.example.unanimous.ApiServices

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieService {


    var currentPath: String = ""
    private val service: MovieDatabaseApi
    var currentDescription: String = ""
    var currentTitle: String = ""


    init {
        val retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(MovieDatabaseApi::class.java)

    }


    fun getMovie(movieTitle: String) {
       service.getMovie(movie = movieTitle).enqueue(object : Callback<MovieData> {
           override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {
                Log.e("Response Code:", response.code().toString())
                if (response.code() == 200) {
                    Log.e("Connection", "Successful")
                    val movieData = response.body()
                    Log.e("Data", movieData.toString())
                    Log.e("title", movieData!!.results[0].original_title)
                    Log.e("description", movieData!!.results[0].overview)
                    Log.e("poster_path", movieData!!.results[0].poster_path)
                    currentTitle = movieData!!.results[0].original_title
                    currentDescription = movieData!!.results[0].overview
                    currentPath = movieData!!.results[0].poster_path



                } else {
                    Log.e("Connection", "Failed")
                    currentTitle = "$movieTitle: Title Not Found"
                    currentDescription=("$movieTitle: Title Not Found")
                }


            }

            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                Log.e("Repository", "onFailure", t)
                currentTitle = "No connection to database"
                currentDescription="No connection to database"
            }

        })
    }


}