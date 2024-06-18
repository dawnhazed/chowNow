package com.dicoding.chownow.ui.loginregister.register

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
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
import com.dicoding.chownow.databinding.ActivityRegisterBinding
import com.dicoding.chownow.ui.loginregister.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var binding: ActivityRegisterBinding

    private var isPasswordValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupTextWatchers()
        setupAction()
        clickable()
        updateSignupButtonEnabledState()
    }

    private fun setupAction() {
        binding.btnRegister.setOnClickListener {
            val name = binding.tvNamaValue.text.toString()
            val username = binding.tvUsernameValue.text.toString()
            val email = binding.tvEmailValue.text.toString()
            val password = binding.tvPasswordValue.text.toString()

            // Panggil ViewModel untuk register
            viewModel.performRegister(name, username, email, password) { success ->
                if (success) {
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    AlertDialog.Builder(this).apply {
                        setTitle("Oops!")
                        setMessage("Registrasi gagal. Silakan coba lagi.")
                        setPositiveButton("OK", null)
                        create()
                        show()
                    }
                }
            }
        }
    }

    private fun updateSignupButtonEnabledState() {
        val isNamaFilled = binding.tvNamaValue.text.isNotEmpty()
        val isUsernameFilled = binding.tvUsernameValue.text.isNotEmpty()
        val isEmailFilled = binding.tvEmailValue.text.isNotEmpty()
        val isPasswordFilled = binding.tvPasswordValue.text.length >= 8
        val isConfirmPasswordFilled = binding.tvConfirmPasswordValue.text.isNotEmpty()
        val isPasswordMatch = binding.tvPasswordValue.text.toString() == binding.tvConfirmPasswordValue.text.toString()

        binding.btnRegister.isEnabled = isNamaFilled && isUsernameFilled && isEmailFilled && isPasswordFilled && isConfirmPasswordFilled && isPasswordMatch
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
                updateSignupButtonEnabledState()
            }
        }

        // Daftarkan TextWatcher ke setiap EditText
        binding.tvNamaValue.addTextChangedListener(textWatcher)
        binding.tvUsernameValue.addTextChangedListener(textWatcher)
        binding.tvEmailValue.addTextChangedListener(textWatcher)
        binding.tvPasswordValue.addTextChangedListener(textWatcher)
        binding.tvConfirmPasswordValue.addTextChangedListener(textWatcher)
    }

    private fun clickable() {
        val textView = binding.loginFromRegister
        val signupText = "Sudah punya akun? Masuk"
        val spanString = SpannableString(signupText)

        val daftarText = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        spanString.setSpan(daftarText, 18, 23, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = spanString
        textView.movementMethod = LinkMovementMethod.getInstance()
    }
}