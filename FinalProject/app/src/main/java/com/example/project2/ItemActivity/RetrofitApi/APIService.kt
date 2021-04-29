package com.example.project2.ItemActivity.RetrofitApi

import com.example.project2.JSONReturnObjects.NicknameReturn
import com.example.project2.JSONReturnObjects.ReturnStatusJSON
import com.example.project2.JSONReturnObjects.VoteItems
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface APIService {

    @GET ("database.php")
    fun testPIN(@QueryMap params: HashMap<String?, String?>): Call<ReturnStatusJSON>

    @POST("database.php")
    fun addPIN(@QueryMap params: HashMap<String?, String?>): Call<ReturnStatusJSON>

    @POST("database.php")
    fun removePIN(@QueryMap params: HashMap<String?, String?>): Call<ReturnStatusJSON>

    @POST("database.php")
    fun addNickname(@QueryMap params: HashMap<String?, String?>): Call<ReturnStatusJSON>

    @GET("database.php")
    fun removeNickname(@QueryMap params: HashMap<String?, String?>): Call<ReturnStatusJSON>

    @GET("database.php")
    fun getNicknames(@QueryMap params: HashMap<String?, String?>): Call<NicknameReturn>

    @POST("database.php")
    fun addItem(@QueryMap params: HashMap<String?, String?>): Call<ReturnStatusJSON>

    @POST("database.php")
    fun removeItem(@QueryMap params: HashMap<String?, String?>): Call<ReturnStatusJSON>

    @GET("database.php")
    fun getItems(@QueryMap params: HashMap<String?, String?>): Call<VoteItems>

    @POST("database.php")
    fun addVotes(@QueryMap params: HashMap<String?, String?>): Call<ReturnStatusJSON>

    @GET("database.php")
    fun getVotes(@QueryMap params: HashMap<String?, String?>): Call<VoteItems>

    companion object {

        fun create(): APIService {

            val retrofit =
                Retrofit.Builder().baseUrl("https://afternoon-beach-92771.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit.create(APIService::class.java)
        }
    }
}