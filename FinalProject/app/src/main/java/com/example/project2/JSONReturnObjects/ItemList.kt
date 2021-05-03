package com.example.project2.JSONReturnObjects

class ItemList : ArrayList<ItemList.ItemListItem>(){
    data class ItemListItem(
        val item: String
    )
}