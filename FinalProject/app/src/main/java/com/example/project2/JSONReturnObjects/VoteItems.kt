package com.example.project2.JSONReturnObjects

import java.io.Serializable

class VoteItems : ArrayList<VoteItems.VoteItem>() {
    data class VoteItem(
        val NoCount: String,
        val YesCount: String,
        val item: String
    ) : Serializable
}