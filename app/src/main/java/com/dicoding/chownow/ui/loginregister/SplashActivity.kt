package com.dicoding.chownow.ui.loginregister

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.dataStore
import androidx.lifecycle.lifecycleScope
import com.dicoding.chownow.R
import com.dicoding.chownow.data.pref.UserPreference
import com.dicoding.chownow.data.pref.dataStore
import com.dicoding.chownow.ui.dashboard.DashboardActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private lateinit var pref: UserPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = UserPreference.getInstance(dataStore)

        lifecycleScope.launch {
            //val isLoggedIn = pref.getSession().first().isLogin
            val isLoggedIn = pref.getSession().first().isLogin
            if (isLoggedIn) {
                startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))
                Log.d("SplashActivity", "isLoggedIn: $isLoggedIn")
            } else {
                startActivity(Intent(this@SplashActivity, WelcomeActivity::class.java))
                Log.d("SplashActivity", "isLoggedIn: $isLoggedIn")
            }
            finish()
        }
    }
}