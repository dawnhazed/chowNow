package com.dicoding.chownow.ui.loginregister.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.ads.mediationtestsuite.MediationTestSuite.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    /* fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    } */

class LoginViewModel : ViewModel() {
    fun performLogin(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            // Simulasi operasi login berat, misalnya memeriksa kredensial ke server
            // Ganti dengan operasi jaringan asli Anda
            Thread.sleep(2000) // Simulasi waktu tunggu
            val success = email == "test@example.com" && password == "password"

            // Kembali ke UI thread untuk mengupdate hasil
            launch(Dispatchers.Main) {
                onResult(success)
            }
        }
    }

}