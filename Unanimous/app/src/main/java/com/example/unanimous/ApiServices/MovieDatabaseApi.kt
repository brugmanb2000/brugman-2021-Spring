package com.example.unanimous.ApiServices

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDatabaseApi {
    @GET("search/movie")
    fun getMovie(
        @Query("api_key") apiKey: String = "351eb9e08022cd3723f14105462adfe9",
        @Query("query") movie:String
    ): Call<MovieData>
}