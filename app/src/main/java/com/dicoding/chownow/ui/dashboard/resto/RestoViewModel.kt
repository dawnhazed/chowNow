package com.dicoding.chownow.ui.dashboard.resto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RestoViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is resto Fragment"
    }
    val text: LiveData<String> = _text
}