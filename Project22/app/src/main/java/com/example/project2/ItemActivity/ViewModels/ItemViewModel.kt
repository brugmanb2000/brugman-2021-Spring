package com.example.project2.ItemActivity.ViewModels

import androidx.lifecycle.ViewModel

import com.example.project2.JSONReturnObjects.VoteItems

class ItemViewModel : ViewModel() {

    enum class PlayerStatusEnum {
        host, player
    }

    enum class GameState {
        join_host, lobby, vote, results
    }

    val nicknameList: ArrayList<String> = ArrayList<String>()
    val itemList: ArrayList<String> = ArrayList<String>()
    val resultsList: ArrayList<VoteItems.VoteItem> = ArrayList<VoteItems.VoteItem>()
    var playerStatus = PlayerStatusEnum.player

    private var totalPlayers = nicknameList.size
    var nickname = ""
    private var gamePIN: Int = -1
    private var gameState = GameState.join_host;


    fun addItem(item: String) {
        itemList.add(item)
    }

    fun addNickname(nickname: String) {
        nicknameList.add(nickname)
    }

    fun addResults(result: VoteItems.VoteItem) {
        resultsList.add(result)
    }

    fun changeNickname(newName: String) {
        nickname = newName
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

    fun removeItem(item: String) {
        itemList.remove(item)
    }

    fun clearLists() {
        itemList.clear()
        nicknameList.clear()
        resultsList.clear()
    }
}