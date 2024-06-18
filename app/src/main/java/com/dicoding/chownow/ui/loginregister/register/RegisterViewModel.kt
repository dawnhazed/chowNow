package com.dicoding.chownow.ui.loginregister.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.ads.mediationtestsuite.MediationTestSuite.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    fun performRegister(name: String, username: String, email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            // Simulasi operasi register berat, misalnya mengirim data ke server
            // Ganti dengan operasi jaringan asli Anda
            Thread.sleep(2000) // Simulasi waktu tunggu
            val success = email.isNotEmpty() && password.isNotEmpty() // Logika sederhana untuk contoh

            // Kembali ke UI thread untuk mengupdate hasil
            launch(Dispatchers.Main) {
                onResult(success)
            }
        }
    }
}