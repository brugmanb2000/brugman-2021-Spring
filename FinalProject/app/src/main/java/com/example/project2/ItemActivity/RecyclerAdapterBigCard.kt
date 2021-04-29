package com.example.project2.ItemActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.ItemActivity.CardItems.BigCard
import com.example.project2.R

class RecyclerAdapterBigCard(private val list: List<BigCard>) : RecyclerView.Adapter<RecyclerAdapterBigCard.RecyclerViewHolder>() {

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.nicknameCardText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_large, parent, false)

        return RecyclerViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentItem = list[position]
        holder.title.text = currentItem.title
    }

    override fun getItemCount() = list.size
}