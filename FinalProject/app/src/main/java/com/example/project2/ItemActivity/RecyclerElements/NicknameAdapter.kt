package com.example.project2.ItemActivity.RecyclerElements

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.ItemActivity.CardItems.NicknameCard
import com.example.project2.R



class NicknameAdapter(private val exampleList: List<NicknameCard>) :
    RecyclerView.Adapter<NicknameAdapter.NicknameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NicknameViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_nickname, parent, false)
        return NicknameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NicknameViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.textView.text = currentItem.nickname

    }

    override fun getItemCount() = exampleList.size


    class NicknameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textViewNicknameCard)
    }
}