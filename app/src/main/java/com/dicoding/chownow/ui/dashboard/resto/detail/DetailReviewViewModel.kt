package com.dicoding.chownow.ui.dashboard.resto.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailReviewViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is review Fragment"
    }
    val text: LiveData<String> = _text
}