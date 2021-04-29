package com.example.project2.ItemActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.ItemActivity.CardItems.ResultsCard
import com.example.project2.R

class RecyclerAdapterResultCard(private val list: List<ResultsCard>) : RecyclerView.Adapter<RecyclerAdapterResultCard.RecyclerViewHolder>() {

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.resultItemName)
        val result: TextView = itemView.findViewById(R.id.resultResult)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_large, parent, false)

        return RecyclerViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentItem = list[position]
        holder.title.text = currentItem.title
        holder.result.text = currentItem.result
    }

    override fun getItemCount() = list.size
}