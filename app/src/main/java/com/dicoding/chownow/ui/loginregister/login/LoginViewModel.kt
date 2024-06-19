package com.dicoding.chownow.ui.loginregister.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.chownow.data.UserRepository
import com.dicoding.chownow.data.pref.UserModel
import com.dicoding.chownow.data.pref.UserPreference
import com.dicoding.chownow.data.remote.response.LoginResponse
import com.dicoding.chownow.data.remote.retrofit.ApiConfig
import com.google.android.ads.mediationtestsuite.MediationTestSuite.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    /* fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    } */

class LoginViewModel(
    private val userRepository: UserRepository,
    private val userPreference: UserPreference
) : ViewModel() {

    private val _loginResult = MutableLiveData<LoginResponse?>()
    val loginResult: LiveData<LoginResponse?> = _loginResult

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            userRepository.saveSession(user)
        }
    }

    fun loginUser(email: String, password: String) {
        ApiConfig.getApiService().login(email, password)
            .enqueue(object : Callback<LoginResponse> {

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        loginResponse?.let{
                            _loginResult.value = it
                            Log.d("login user", "Login Successful!")

                            viewModelScope.launch {
                                userPreference.saveSession(UserModel(email, it.token ?: "", true))
                            }
                        }
                    } else {
                        _loginResult.value = null
                        Log.d("login user", "Login Failed")
                    }
                }

                override fun onFailure(p0: Call<LoginResponse>, p1: Throwable) {
                    _loginResult.value = null
                    Log.d("login user", "Login Error")
                }
            })
        /* viewModelScope.launch {
            try {
                val response = userRepository.loginUser(email, password)
                if (!response.error!!) {
                    val user = UserModel(
                        email = email,
                        token = response.loginResult?.token,
                        isLogin = true
                    )
                    userRepository.saveSession(user)
                    _loginResult.postValue(response)
                } else {
                    _loginResult.postValue(response)
                }
            } catch (e: Exception) {
                // Handle error
            }
        } */
    }

//    fun performLogin(email: String, password: String, onResult: (Boolean) -> Unit) {
//        viewModelScope.launch(Dispatchers.IO) {
//            // Simulasi operasi login berat, misalnya memeriksa kredensial ke server
//            // Ganti dengan operasi jaringan asli Anda
//            Thread.sleep(2000) // Simulasi waktu tunggu
//            val success = email == "test@example.com" && password == "password"
//
//            // Kembali ke UI thread untuk mengupdate hasil
//            launch(Dispatchers.Main) {
//                onResult(success)
//            }
//        }
//    }

}