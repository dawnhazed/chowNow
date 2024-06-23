package com.dicoding.chownow.di

import android.content.Context
import com.dicoding.chownow.data.repository.RestaurantRepository
import com.dicoding.chownow.data.repository.UserRepository
import com.dicoding.chownow.data.pref.UserPreference
import com.dicoding.chownow.data.pref.dataStore
import com.dicoding.chownow.data.remote.retrofit.ApiConfig.getApiService
import com.dicoding.chownow.data.local.AppDatabase

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val apiService = getApiService()
        return UserRepository.getInstance(pref, apiService)
    }

    fun provideRestoRepository(context: Context): RestaurantRepository {
        val database = AppDatabase.getDatabase(context)
        val restaurantDao = database.restaurantDao()
        val apiService = getApiService()
        return RestaurantRepository.getInstance(apiService, restaurantDao)
    }
}
