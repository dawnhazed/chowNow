package com.dicoding.chownow.ui.loginregister.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.chownow.data.UserRepository
import com.dicoding.chownow.data.remote.response.RegisterResponse
import com.dicoding.chownow.data.remote.retrofit.ApiConfig
import com.google.android.ads.mediationtestsuite.MediationTestSuite.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel (private val userRepository: UserRepository) : ViewModel() {

    private val _registerResult = MutableLiveData<RegisterResponse?>()
    val registerResult: LiveData<RegisterResponse?> = _registerResult

    fun registerUser(name: String, username: String, email: String, password: String) {
        ApiConfig.getApiService().register(name, username, email, password)
            .enqueue(object : Callback<RegisterResponse> {

                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.isSuccessful) {
                        val registerResponse = response.body()
                        registerResponse?.let {
                            _registerResult.value = it
                            Log.d("register user", "Registration Successful : ${it.message}")
                        }
                    } else {
                        _registerResult.value = null
                        Log.d("register user", "Registration Failed")
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    _registerResult.value = null
                    Log.d("register user", "Registration Error")
                }

            })
    }

//    fun performRegister(name: String, username: String, email: String, password: String, onResult: (Boolean) -> Unit) {
//        viewModelScope.launch(Dispatchers.IO) {
//            // Simulasi operasi register berat, misalnya mengirim data ke server
//            // Ganti dengan operasi jaringan asli Anda
//            Thread.sleep(2000) // Simulasi waktu tunggu
//            val success = email.isNotEmpty() && password.isNotEmpty() // Logika sederhana untuk contoh
//
//            // Kembali ke UI thread untuk mengupdate hasil
//            launch(Dispatchers.Main) {
//                onResult(success)
//            }
//        }
//    }
}