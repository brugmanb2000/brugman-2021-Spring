package com.example.unanimous.ui.frag1

import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var word1 = ""
    var word2 = ""
    companion object {
       val viewModel = SharedViewModel
    }





}

