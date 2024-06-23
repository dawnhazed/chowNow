package com.dicoding.chownow.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.chownow.data.local.AppDatabase
import com.dicoding.chownow.data.local.entity.RestaurantEntity
import com.dicoding.chownow.data.remote.retrofit.ApiConfig
import com.dicoding.chownow.data.repository.RestaurantRepository
import kotlinx.coroutines.launch

class RestaurantViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RestaurantRepository
    val restaurants: LiveData<List<RestaurantEntity>>

    init {
        val restaurantDao = AppDatabase.getDatabase(application).restaurantDao()
        repository = RestaurantRepository.getInstance(ApiConfig.getApiService(), restaurantDao)
        restaurants = MutableLiveData()
    }

    fun getRestaurantsByLocation(location: String) {
        viewModelScope.launch {
            repository.getRestaurantsByLocation(location).collect { result ->
                (restaurants as MutableLiveData).postValue(result)
            }
        }
    }

    fun insertAll(restaurants: List<RestaurantEntity>) {
        viewModelScope.launch {
            repository.insertAll(restaurants)
        }
    }
}
