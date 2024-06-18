package com.dicoding.chownow.ui.loginregister.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.SpannableString
import android.text.Spanned
import android.text.SpannedString
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.MotionEvent
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
import com.dicoding.chownow.ui.dashboard.DashboardActivity
import com.dicoding.chownow.ui.loginregister.register.RegisterActivity
//import com.google.android.ads.mediationtestsuite.viewmodels.ViewModelFactory

class LoginActivity : AppCompatActivity() {
    // private val viewModel by viewModels<LoginViewModel> { ViewModelFactory.getInstance(this) }
    private val viewModel: LoginViewModel by viewModels()

    private lateinit var binding: ActivityLoginBinding

    private var isPasswordValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupTextWatchers()
        setupAction()
        clickable()
        updateLoginButtonEnabledState()
    }

    private fun setupAction() {
        binding.btnLogin.setOnClickListener {
            val email = binding.tvEmailValue.text.toString()
            val password = binding.tvPasswordValue.text.toString()

            // Panggil ViewModel untuk login
            viewModel.performLogin(email, password) { success ->
                if (success) {
                    AlertDialog.Builder(this).apply {
                        setTitle("Yeah!")
                        setMessage("Anda berhasil login. Sudah tidak sabar untuk belajar ya?")
                        setPositiveButton("Lanjut") { _, _ ->
                            val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                            finish()
                        }
                        create()
                        show()
                    }
                } else {
                    AlertDialog.Builder(this).apply {
                        setTitle("Oops!")
                        setMessage("Login gagal. Silakan cek email dan password Anda.")
                        setPositiveButton("OK", null)
                        create()
                        show()
                    }
                }
            }
        }
    }

    private fun updateLoginButtonEnabledState() {
        val isEmailFilled = binding.tvEmailValue.text.isNotEmpty()
        val isPasswordFilled = binding.tvPasswordValue.text.length >= 8

        binding.btnLogin.isEnabled = isEmailFilled && isPasswordFilled
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

    private fun setupTextWatchers() {
        // Tambahkan TextWatcher untuk setiap EditText
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Tidak digunakan dalam implementasi ini
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Tidak digunakan dalam implementasi ini
            }

            override fun afterTextChanged(s: Editable?) {
                updateLoginButtonEnabledState()
            }
        }

        // Daftarkan TextWatcher ke setiap EditText
        binding.tvEmailValue.addTextChangedListener(textWatcher)
        binding.tvPasswordValue.addTextChangedListener(textWatcher)
    }

//    private fun setupAction() {
//        binding.btnLogin.setOnClickListener {
//            val email = binding.tvEmailValue.text.toString()
//            /* viewModel.saveSession(UserModel(email, "sample_token"))
//            AlertDialog.Builder(this).apply {
//                setTitle("Yeah!")
//                setMessage("Anda berhasil login. Sudah tidak sabar untuk belajar ya?")
//                setPositiveButton("Lanjut") { _, _ ->
//                    val intent = Intent(context, MainActivity::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                    startActivity(intent)
//                    finish()
//                }
//                create()
//                show()
//            } */
//        }
//    }

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