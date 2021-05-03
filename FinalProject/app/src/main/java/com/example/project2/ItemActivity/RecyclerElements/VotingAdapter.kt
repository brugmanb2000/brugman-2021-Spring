package com.example.project2.ItemActivity.RecyclerElements

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.ItemActivity.CardItems.BigCard
import com.example.project2.ItemActivity.CardItems.NicknameCard
import com.example.project2.ItemActivity.RetrofitApi.APIService
import com.example.project2.ItemActivity.ViewModels.ItemViewModel
import com.example.project2.JSONReturnObjects.NicknameReturn
import com.example.project2.JSONReturnObjects.ReturnStatusJSON
import com.example.project2.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VotingAdapter(private val gamePIN: Int, private val list: ArrayList<BigCard>): RecyclerView.Adapter<VotingAdapter.VotingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VotingViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_large, parent, false)
        return VotingViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VotingViewHolder, position: Int) {
        val currentItem = list[position]
        val context = holder.itemView.context
        holder.text.text = currentItem.title

        holder.likeButton.setOnClickListener {
            Toast.makeText(context, "Item Liked", Toast.LENGTH_SHORT).show()
            vote(gamePIN, currentItem.title, "yes", context)
            list.remove(currentItem)
            notifyDataSetChanged()

        }

        holder.dislikeButton.setOnClickListener {
            Toast.makeText(context, "Item Disliked", Toast.LENGTH_SHORT).show()
            vote(gamePIN, currentItem.title, "no", context)
            list.remove(currentItem)
            notifyDataSetChanged()
        }
    }


    override fun getItemCount(): Int {
        return list.size
        }


    class VotingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.voteItemCardText)
        val likeButton: Button = itemView.findViewById<Button>(R.id.bigCardLikeButton)
        val dislikeButton: Button = itemView.findViewById<Button>(R.id.bigCardDislikeButton)
    }

}

private fun vote(gamePIN: Int, item: String, type: String, context: Context) {
    val params: HashMap<String?, String?> = HashMap()
    params["gamePIN"] = gamePIN.toString()
    params["request"] = "addVotes"
    params["item"] = item
    params["vote"] = type


    val api = APIService.create().addVotes(params)

    api?.enqueue(
        object : Callback<Void> {
            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                Log.e("Vote:", "Added")
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("Vote:","Failed: " + t.localizedMessage)

            }
        }
    )


}