package com.example.project2.JSONReturnObjects

import java.io.Serializable

data class NicknameReturn(
    val sessionID: Int,
    val Nicknames: List<Nickname>
) : Serializable {
    data class Nickname(
        val nickname: String
    ) : Serializable
}