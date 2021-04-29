package com.example.project2.JSONReturnObjects

import java.io.Serializable

class ReturnStatusJSON : ArrayList<ReturnStatusJSON.ReturnStatusItem>(){
    data class ReturnStatusItem(
        val ReturnStatus: String
    )
}

