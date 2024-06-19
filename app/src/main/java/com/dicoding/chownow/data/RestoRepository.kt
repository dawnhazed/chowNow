package com.dicoding.chownow.data

import com.dicoding.chownow.data.pref.UserPreference
import com.dicoding.chownow.data.remote.retrofit.ApiService

class RestoRepository private constructor(
    private val apiService: ApiService,
    private val userPreferences: UserPreference
) {




    companion object {
        @Volatile
        private var instance: RestoRepository? = null

        fun getInstance(apiService: ApiService, userPreferences: UserPreference): RestoRepository =
            instance ?: synchronized(this) {
                instance ?: RestoRepository(apiService, userPreferences)
            }.also { instance = it }
    }

}