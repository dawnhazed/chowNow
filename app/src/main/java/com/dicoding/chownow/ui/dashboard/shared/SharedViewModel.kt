package com.dicoding.chownow.ui.dashboard.shared

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.chownow.data.model.ListResto

class SharedViewModel : ViewModel() {
    private val _restaurants = MutableLiveData<List<ListResto>>()
    val restaurants: LiveData<List<ListResto>> get() = _restaurants

    fun setRestaurants(restaurants: List<ListResto>) {
        _restaurants.value = restaurants
    }
}
