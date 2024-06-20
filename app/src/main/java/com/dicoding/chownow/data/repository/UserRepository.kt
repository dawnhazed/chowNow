package com.dicoding.chownow.data.repository

import com.dicoding.chownow.data.pref.UserModel
import com.dicoding.chownow.data.pref.UserPreference
import com.dicoding.chownow.data.remote.response.LoginResponse
import com.dicoding.chownow.data.remote.response.RegisterResponse
import com.dicoding.chownow.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

class UserRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService
) {

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    fun registerUser(name: String, username: String, email: String, password: String): Call<RegisterResponse> {
        return apiService.register(name, username, email, password)
    }

    fun loginUser(email: String, password: String): Call<LoginResponse> {
        return apiService.login(email, password)
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference, apiService)
            }.also { instance = it }
    }
}