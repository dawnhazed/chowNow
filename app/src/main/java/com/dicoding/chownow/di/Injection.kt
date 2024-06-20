package com.dicoding.chownow.di

import android.content.Context
import com.dicoding.chownow.data.repository.RestoRepository
import com.dicoding.chownow.data.repository.UserRepository
import com.dicoding.chownow.data.pref.UserPreference
import com.dicoding.chownow.data.pref.dataStore
import com.dicoding.chownow.data.remote.retrofit.ApiConfig.getApiService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val apiService = getApiService()
        return UserRepository.getInstance(pref, apiService)
    }

    fun provideRestoRepository(context: Context): RestoRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = getApiService()
        return RestoRepository.getInstance(apiService, pref)
    }
}