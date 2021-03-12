package com.example.unanimous.ui.frag1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.unanimous.R

class Frag2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.frag2_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("Frag2", "Frag2 Built")
    }

    override fun onStart() {
        super.onStart()
        val viewModel: SharedViewModel by activityViewModels()
        var paste = view?.findViewById<Button>(R.id.frag2Reset)
        var text2 = view?.findViewById<TextView>(R.id.frag2TextView)

        paste?.setOnClickListener {
            Log.e("Button", "Paste button pressed!!!")
            viewModel.word2 = viewModel.word1
            text2?.text = viewModel.word2
            Log.e("ViewModel Word 1", viewModel.word1)
            Log.e("ViewModel Word 2", viewModel.word2)
            Log.e("TextView", viewModel.word2)
            }
        }
    }



