package com.dicoding.chownow.ui.dashboard.resto.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailMenuViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is menu Fragment"
    }
    val text: LiveData<String> = _text
}