package com.example.project2.ItemActivity.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.ItemActivity.CardItems.BigCard
import com.example.project2.ItemActivity.CardItems.NicknameCard
import com.example.project2.ItemActivity.ItemActivity
import com.example.project2.ItemActivity.RecyclerElements.NicknameAdapter
import com.example.project2.ItemActivity.RecyclerElements.VotingAdapter
import com.example.project2.ItemActivity.RetrofitApi.APIService
import com.example.project2.ItemActivity.ViewModels.ItemViewModel
import com.example.project2.JSONReturnObjects.ItemList
import com.example.project2.JSONReturnObjects.NicknameReturn
import com.example.project2.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemActivityVoteFrag : Fragment() {

    companion object {
        fun newInstance(): ItemActivityVoteFrag {
            return ItemActivityVoteFrag()
        }
    }

    private val viewModel: ItemViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_item_activity_vote2, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val loadButton = view?.findViewById<Button>(R.id.voteFragLoadList)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.voteItemsRecyclerView)
        var list = ArrayList<BigCard>()
        updateList(list)

        recyclerView?.adapter = VotingAdapter(viewModel.getPIN(), list)
        recyclerView?.layoutManager = LinearLayoutManager(ItemActivity.newInstance())
        recyclerView?.setHasFixedSize(true)


        loadButton?.setOnClickListener {
            updateList(list)
        }
    }

    private fun updateList(list: ArrayList<BigCard>) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.voteItemsRecyclerView)
        val params: HashMap<String?, String?> = HashMap<String?, String?>()
        val loadButton = view?.findViewById<Button>(R.id.voteFragLoadList)
        params["gamePIN"] = viewModel.getPIN().toString()
        params["request"] = "getItems"
        val api = APIService.create().getItems(params)
        Log.e("Testing List", "Testing...")
        api?.enqueue(
            object : Callback<ItemList> {
                override fun onResponse(
                    call: Call<ItemList>,
                    response: Response<ItemList>
                ) {
                    val json = response.body()
                    list.clear()
                    if (!json.isNullOrEmpty()) {
                        for (item in json) {
                            list.add(BigCard(item.item))
                        }
                        recyclerView?.adapter = VotingAdapter(viewModel.getPIN(), list)
                        Log.e("Testing List", "Passed")
                        loadButton?.visibility = View.INVISIBLE
                    } else {
                        Log.e("Testing List,", "Nicknames found null")
                    }

                }

                override fun onFailure(call: Call<ItemList>, t: Throwable) {
                    //Toast.makeText(ItemActivity.newInstance(), "Could not update list", Toast.LENGTH_SHORT).show()
                    Log.e("Testing List", "Failed")
                }
            })

    }
}