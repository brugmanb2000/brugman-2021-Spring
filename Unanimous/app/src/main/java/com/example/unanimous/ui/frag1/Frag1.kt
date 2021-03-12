package com.example.unanimous.ui.frag1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.unanimous.R

class Frag1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.frag1_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("Frag1", "Frag1 Built")
    }

    override fun onStart() {
        super.onStart()
        val viewModel: SharedViewModel by activityViewModels()
        var copy = view?.findViewById<Button>(R.id.frag1CopyButton)
        var text = view?.findViewById<EditText>(R.id.frag1EditText)


        copy?.setOnClickListener {
            Log.e("Button", "Copy button pressed!!!")
            viewModel.word1 = text?.text.toString()
            Log.e("ViewModel Word 1", viewModel.word1)
        }
    }
}
