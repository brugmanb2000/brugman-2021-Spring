package com.example.project2.ItemActivity.RecyclerElements

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.ItemActivity.CardItems.NicknameCard
import com.example.project2.ItemActivity.CardItems.ResultsCard
import com.example.project2.R
import kotlin.math.roundToInt

class ResultsAdapter(private val list: List<ResultsCard>): RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_results, parent, false)
        return ResultsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        val currentItem = list[position]
        holder.text.text = currentItem.title
        holder.text2.text = currentItem.result + "%"
        holder.resultsBar.progress = currentItem.result.toDouble().toInt()

    }

    override fun getItemCount(): Int {
        return list.size
        }

    class ResultsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.resultItemName)
        val text2: TextView = itemView.findViewById(R.id.resultResult)
        val resultsBar: ProgressBar = itemView.findViewById(R.id.resultsBar)
    }

}
