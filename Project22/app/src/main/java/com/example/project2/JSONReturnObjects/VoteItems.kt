package com.example.project2.JSONReturnObjects

import java.io.Serializable


data class VoteItems(
    val `0`: Int,
    val VoteItems : List<VoteItem>
) : Serializable {
    data class VoteItem(
        val NoCount: String,
        val YesCount: String,
        val item: String
    ) : Serializable
}