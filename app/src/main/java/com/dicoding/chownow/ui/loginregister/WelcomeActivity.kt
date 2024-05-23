package com.dicoding.chownow.ui.loginregister

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.chownow.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private var binding: ActivityWelcomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("WelcomeActivity", "Inflating layout")

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        Log.d("WelcomeActivity", "Layout inflated")

        //setupView()
        Log.d("setupView", "View settled")

        //setupAction()
        Log.d("setupAction", "View be Clicked")
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
            Log.d("isAndroidRedVelvetOrLater", "Yap, android ini diatas atau sama dengan RedVelvet")
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
            Log.d("isAndroidRedVelvetOrLater", "Tidak, android ini dibawah RedVelvet")
        }
        supportActionBar?.hide()
        Log.d("ActionBar", "ActionBar disembunyikan")
    }

    private fun setupAction(){
        binding?.loginButton?.setOnClickListener {
            // startActivity(Intent(this, LoginActivity::class.java))
        }

        binding?.signupButton?.setOnClickListener {
            // startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}