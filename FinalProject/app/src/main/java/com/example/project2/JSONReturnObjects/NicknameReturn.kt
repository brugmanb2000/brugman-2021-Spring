package com.example.project2.JSONReturnObjects

import java.io.Serializable

class NicknameReturn : ArrayList<NicknameReturn.Nickname>() {
        data class Nickname(
            val nickname: String
        ) : Serializable
    }