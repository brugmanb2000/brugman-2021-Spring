package com.example.project2.ItemActivity.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.ItemActivity.CardItems.ResultsCard
import com.example.project2.ItemActivity.ItemActivity
import com.example.project2.ItemActivity.RecyclerElements.ResultsAdapter
import com.example.project2.ItemActivity.RetrofitApi.APIService
import com.example.project2.ItemActivity.ViewModels.ItemViewModel
import com.example.project2.JSONReturnObjects.VoteItems
import com.example.project2.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat


class ItemActivityResults : Fragment() {

    companion object {
        fun newInstance(): ItemActivityResults {
            return ItemActivityResults()
        }
    }

    private val viewModel: ItemViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_item_activity_results, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val refreshButton = view?.findViewById<Button>(R.id.resultsRefreshButton)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.resultsRecyclerView)
        var list = ArrayList<ResultsCard>()
        updateList(list)

        recyclerView?.adapter = ResultsAdapter(list)
        recyclerView?.layoutManager = LinearLayoutManager(ItemActivity.newInstance())
        recyclerView?.setHasFixedSize(true)


        refreshButton?.setOnClickListener {
            updateList(list)
        }
    }

    private fun updateList(list: ArrayList<ResultsCard>) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.resultsRecyclerView)
        val params: HashMap<String?, String?> = HashMap<String?, String?>()
        params["gamePIN"] = viewModel.getPIN().toString()
        params["request"] = "getVotes"
        val api = APIService.create().getVotes(params)
        Log.e("Testing List", "Testing...")
        api?.enqueue(
            object : Callback<VoteItems> {
                override fun onResponse(
                    call: Call<VoteItems>,
                    response: Response<VoteItems>
                ) {
                    val json = response.body()
                    list.clear()
                    if (!json.isNullOrEmpty()) {
                        for (item in json) {
                            val double1 = item.YesCount.toDouble()
                            val double2 = item.YesCount.toDouble() + item.NoCount.toDouble()
                            val percentage = calculatePercentage(double1, double2)
                            list.add(ResultsCard(item.item, percentage))
                        }
                        list.sortWith(Comparator<ResultsCard> { p1, p2 ->
                            when {
                                p1.result.toDouble() > p2.result.toDouble() -> -1
                                p1.result.toDouble() == p2.result.toDouble() -> 0
                                else -> 1
                            }
                        })

                        recyclerView?.adapter = ResultsAdapter(list)
                        Log.e("Testing List", "Passed")

                    } else {
                        Log.e("Testing List,", "Nicknames found null")
                    }

                }

                override fun onFailure(call: Call<VoteItems>, t: Throwable) {
                    //Toast.makeText(ItemActivity.newInstance(), "Could not update list", Toast.LENGTH_SHORT).show()
                    Log.e("Testing List", "Failed")
                }
            })

    }

    private fun calculatePercentage(double1: Double, double2: Double): String {
        val df = DecimalFormat("###.##")
        val retVal = df.format((double1/double2) * 100)
        return retVal
    }
}