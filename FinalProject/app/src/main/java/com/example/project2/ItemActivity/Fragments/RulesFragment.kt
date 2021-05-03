package com.example.project2.ItemActivity.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.ItemActivity.CardItems.BigCard
import com.example.project2.ItemActivity.CardItems.ResultsCard
import com.example.project2.ItemActivity.ItemActivity
import com.example.project2.ItemActivity.RecyclerElements.ResultsAdapter
import com.example.project2.ItemActivity.RecyclerElements.VotingAdapter
import com.example.project2.ItemActivity.RetrofitApi.APIService
import com.example.project2.ItemActivity.ViewModels.ItemViewModel
import com.example.project2.JSONReturnObjects.ItemList
import com.example.project2.JSONReturnObjects.VoteItems
import com.example.project2.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RulesFragment : Fragment() {
    private val viewModel: ItemViewModel by activityViewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rules_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }}