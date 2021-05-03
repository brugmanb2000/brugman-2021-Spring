package com.example.project2.ItemActivity.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project2.ItemActivity.CardItems.NicknameCard
import com.example.project2.ItemActivity.ItemActivity
import com.example.project2.ItemActivity.RecyclerElements.NicknameAdapter
import com.example.project2.ItemActivity.RetrofitApi.APIService
import com.example.project2.ItemActivity.ViewModels.ItemViewModel
import com.example.project2.JSONReturnObjects.NicknameReturn
import com.example.project2.JSONReturnObjects.ReturnStatusJSON
import com.example.project2.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LobbyFragHost : Fragment() {

    companion object {
        fun newInstance(): LobbyFragHost {
            return LobbyFragHost()
        }
    }

    private val viewModel: ItemViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.removePlayer()
                val intent = Intent(activity, ItemActivity.newInstance()::class.java)
                startActivity(intent)
                activity!!.finish()
            }
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_lobby_host, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("Lobby Fragment:", " Loaded")
        val recyclerView = view?.findViewById<RecyclerView>(R.id.nicknames_recyclerview)
        val list = ArrayList<NicknameCard>()
        val refreshButton = view?.findViewById<Button>(R.id.nicknameRefresh)
        val gamePIN = view?.findViewById<TextView>(R.id.nicknamesPIN)
        gamePIN?.text = "Game PIN: " + viewModel.getPIN().toString()
        updateList(list)
        recyclerView?.adapter = NicknameAdapter(list)
        recyclerView?.layoutManager = LinearLayoutManager(ItemActivity.newInstance())
        recyclerView?.setHasFixedSize(true)

        refreshButton?.setOnClickListener {
            updateList(list)
        }


    }
    private fun updateList(list: ArrayList<NicknameCard>) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.nicknames_recyclerview)
        val size = view?.findViewById<TextView>(R.id.nicknameList)
        val params: HashMap<String?, String?> = HashMap<String?, String?>()
        params["gamePIN"] = viewModel.getPIN().toString()
        params["request"] = "getNicknames"
        val api = APIService.create().getNicknames(params)
        Log.e("Testing List", "Testing...")
        api?.enqueue(
            object : Callback<NicknameReturn> {
                override fun onResponse(
                    call: Call<NicknameReturn>,
                    response: Response<NicknameReturn>
                ) {
                    val json: NicknameReturn? = response.body()
                    list.clear()
                    if (!json.isNullOrEmpty()) {
                        for (nickname in json) {
                            list.add(NicknameCard(nickname.nickname))
                        }
                        size?.text = "Total Players: " + (list.size)

                        recyclerView?.adapter = NicknameAdapter(list)
                        Log.e("Testing List", "Passed")

                    } else {
                        Log.e("Testing List,", "Nicknames found null")
                        //Toast.makeText(getApplicationContext(), "Refresh List Again Here Shortly", Toast.LENGTH_SHORT)
                    }



                }

                override fun onFailure(call: Call<NicknameReturn>, t: Throwable) {
                    //Toast.makeText(ItemActivity.newInstance(), "Could not update list", Toast.LENGTH_SHORT).show()
                    Log.e("Testing List", "Failed")
                }
            })

    }
}



