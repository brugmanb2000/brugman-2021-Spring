package com.example.project2.ItemActivity.ViewModels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.project2.ItemActivity.RetrofitApi.APIService
import com.example.project2.JSONReturnObjects.ReturnStatusJSON

import com.example.project2.JSONReturnObjects.VoteItems
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemViewModel : ViewModel() {

    enum class PlayerStatusEnum {
        host, player
    }

    enum class GameState {
        join_host, lobbyHost, lobby, add, lobby2, vote, results
    }

    enum class ValidPIN {
        YES, NO
    }

    var playerStatus = PlayerStatusEnum.player
    var validPIN = ValidPIN.NO
    var nickname = ""
    private var gamePIN: Int = -1
    private var gameState = GameState.join_host;




    fun addNickname(nickname: String) {
        this.nickname = nickname
    }

    fun changeGamestate(state: ItemViewModel.GameState) {
        gameState = state
    }

    fun getGamestate(): ItemViewModel.GameState {
        return gameState
    }

    fun addPIN(pin: Int) {
        gamePIN = pin
    }

    fun getPIN(): Int {
        return gamePIN
    }

    fun changePlayerStatusHost() {
       playerStatus = ItemViewModel.PlayerStatusEnum.host
    }

    fun changePlayerStatusPlayer() {
        playerStatus = ItemViewModel.PlayerStatusEnum.player
    }

    fun removePlayer() {

        val params: HashMap<String?, String?> = HashMap()
        params["gamePIN"] = getPIN().toString()
        params["request"] = "addNickname"
        params["nickname"] = nickname


        val api = APIService.create().removeNickname(params)

        api.enqueue(
            object : Callback<Void> {
                override fun onResponse(
                    call: Call<Void>,
                    response: Response<Void>
                ) {
                    Log.e("Nickname Removed", nickname.toString())
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                     Log.e("Nickname Removed", t.localizedMessage)
                }
            }
        )
    }

}