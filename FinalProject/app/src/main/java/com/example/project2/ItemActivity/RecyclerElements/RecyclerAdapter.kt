package com.example.project2.ItemActivity.RecyclerElements

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.ItemActivity.CardItems.NicknameCard
import com.example.project2.R
import org.w3c.dom.Text

class NicknameAdapter(private val list: List<NicknameCard>): RecyclerView.Adapter<NicknameAdapter.NicknamesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NicknamesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_nickname, parent, false)
        return NicknamesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NicknamesViewHolder, position: Int) {
        val currentItem = list[position]
        holder.text.text = currentItem.nickname
    }

    override fun getItemCount(): Int {
        return list.size
        }


    class NicknamesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.nicknameCardText)
    }

}
