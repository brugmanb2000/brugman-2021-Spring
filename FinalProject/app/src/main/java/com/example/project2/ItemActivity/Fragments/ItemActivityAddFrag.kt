package com.example.project2.ItemActivity.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.project2.ItemActivity.RetrofitApi.APIService
import com.example.project2.ItemActivity.ViewModels.ItemViewModel
import com.example.project2.JSONReturnObjects.ReturnStatusJSON
import com.example.project2.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ItemActivityAddFrag : Fragment() {

    companion object {
        fun newInstance(): ItemActivityAddFrag {
            return ItemActivityAddFrag()
        }
    }

    private val viewModel: ItemViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_activity_vote, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("Lobby Fragment:", " Loaded")
        val addItemText = view?.findViewById<EditText>(R.id.itemEditField)
        val addButton = view?.findViewById<Button>(R.id.voteFragLoadList)


        addItemText?.setOnClickListener {
            addItemText.setText("")
        }

        addButton?.setOnClickListener {
            val item = addItemText?.text.toString()
            val params = HashMap<String?, String?>()
            params["gamePIN"] = viewModel.getPIN().toString()
            params["request"] = "addItem"
            params["item"] = item.toString()
            val api = APIService.create().addItem(params)
            Log.e("Testing List", "Testing...")
            api?.enqueue(
                object : Callback<ReturnStatusJSON> {
                    override fun onResponse(
                        call: Call<ReturnStatusJSON>,
                        response: Response<ReturnStatusJSON>
                    ) {

                        val json = response.body()
                        Log.e("Adding Item", "Added")
                        if ((json?.get(0)?.ReturnStatus == "duplicate")) {
                                Toast.makeText(activity, "Item already added =)", Toast.LENGTH_LONG).show()
                            } else {
                                Toast.makeText(activity, "Item added!", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ReturnStatusJSON>, t: Throwable) {
                        //Toast.makeText(ItemActivity.newInstance(), "Could not update list", Toast.LENGTH_SHORT).show()
                        Log.e("Adding Item", "Failed" + t.localizedMessage)
                        Toast.makeText(activity, "Item could not be added", Toast.LENGTH_SHORT).show()
                    }
                })


        }


    }

    fun onBackPressed() {
        Toast.makeText(activity, "Back button not allowed when in game", Toast.LENGTH_LONG).show()
    }





}