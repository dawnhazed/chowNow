package com.dicoding.chownow.ui.dashboard.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _selectedLocation = MutableLiveData<String>()
    val selectedLocation: LiveData<String> get() = _selectedLocation

    fun setSelectedLocation(location: String) {
        _selectedLocation.value = location
        Log.d("HomeViewModel", "Set location to $location")
    }
}