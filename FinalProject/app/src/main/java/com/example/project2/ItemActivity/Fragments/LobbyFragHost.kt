package com.example.project2.ItemActivity.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import com.example.project2.ItemActivity.ViewModels.ItemViewModel
import com.example.project2.R


class LobbyFragHost : Fragment() {

    companion object {
        fun newInstance(): LobbyFragHost {
            return LobbyFragHost()
        }
    }

    private val viewModel: ItemViewModel by activityViewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.fragment_lobby_host, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("Lobby Fragment:", " Loaded")
        viewModel.changeGamestate(ItemViewModel.GameState.lobby)



        when (viewModel.playerStatus) {
            ItemViewModel.PlayerStatusEnum.host -> {

            }

            ItemViewModel.PlayerStatusEnum.player -> {

            }

            }
        }
    }

