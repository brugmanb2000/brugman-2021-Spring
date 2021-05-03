package com.example.project2.ItemActivity.ViewModels

import androidx.lifecycle.ViewModel

import com.example.project2.JSONReturnObjects.VoteItems

class ItemViewModel : ViewModel() {

    enum class PlayerStatusEnum {
        host, player
    }

    enum class GameState {
        join_host, lobbyHost, lobby, add, lobby2, vote, results
    }

    var playerStatus = PlayerStatusEnum.player

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

}