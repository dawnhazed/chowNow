package com.dicoding.chownow.ui.dashboard.resto.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailAboutViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is about Fragment"
    }
    val text: LiveData<String> = _text
}