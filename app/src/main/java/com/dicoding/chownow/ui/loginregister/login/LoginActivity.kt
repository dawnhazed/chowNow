package com.dicoding.chownow.ui.loginregister.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.SpannedString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.chownow.R
import com.dicoding.chownow.databinding.ActivityLoginBinding
import com.dicoding.chownow.ui.loginregister.register.RegisterActivity
import com.google.android.ads.mediationtestsuite.viewmodels.ViewModelFactory

class LoginActivity : AppCompatActivity() {
    // private val viewModel by viewModels<LoginViewModel> { ViewModelFactory.getInstance(this) }
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
        clickable()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditTextLayout.text.toString()
            /* viewModel.saveSession(UserModel(email, "sample_token"))
            AlertDialog.Builder(this).apply {
                setTitle("Yeah!")
                setMessage("Anda berhasil login. Sudah tidak sabar untuk belajar ya?")
                setPositiveButton("Lanjut") { _, _ ->
                    val intent = Intent(context, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
                create()
                show()
            } */
        }
    }

    private fun clickable() {
        val textView = binding.signupFromLogin
        val signupText = "Belum punya akun? Daftar"
        val spanString = SpannableString(signupText)

        val daftarText = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }

        spanString.setSpan(daftarText, 18, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = spanString
        textView.movementMethod = LinkMovementMethod.getInstance()
    }
}