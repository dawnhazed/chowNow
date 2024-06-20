package com.dicoding.chownow.ui.loginregister.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dicoding.chownow.data.pref.UserModel
import com.dicoding.chownow.data.pref.UserPreference
import com.dicoding.chownow.data.pref.dataStore
import com.dicoding.chownow.databinding.ActivityLoginBinding
import com.dicoding.chownow.ui.ViewModelFactory
import com.dicoding.chownow.ui.dashboard.DashboardActivity
import com.dicoding.chownow.ui.loginregister.register.RegisterActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModel> { ViewModelFactory.getInstance(this) }
    //private val viewModel: LoginViewModel by viewModels()

    private lateinit var binding: ActivityLoginBinding

    private lateinit var pref: UserPreference

//    private var isPasswordValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = UserPreference.getInstance(dataStore)

        setupView()
        setupTextWatchers()
        setupAction()
        clickable()
        observeViewModel()
        updateLoginButtonEnabledState()
    }

    private fun setupAction() {
        binding.btnLogin.setOnClickListener {
            val email = binding.tvEmailValue.text.toString()
            val password = binding.tvPasswordValue.text.toString()

            showLoading(true)
            viewModel.loginUser(email, password)

            Log.d("login button", "Login Button Clicked!")
        }
    }

    private fun updateLoginButtonEnabledState() {
        val isEmailFilled = binding.tvEmailValue.text.isNotEmpty()
        val isPasswordFilled = binding.tvPasswordValue.text.length >= 8

        binding.btnLogin.isEnabled = isEmailFilled && isPasswordFilled
        binding.tvPasswordWarning.visibility = if (isPasswordFilled) View.GONE else View.VISIBLE
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

    private fun observeViewModel() {
        viewModel.loginResult.observe(this) { response ->
            if (response?.token != null) {
                val email = binding.tvEmailValue.text.toString()
                val token = response.token
                saveToken(token)
                Log.d("observe viewmodel", "token = $token")
                lifecycleScope.launch {
                    pref.saveSession(UserModel(email, token, true))
                    Log.d("lifecycle scope", "token = $token")
                    showDialogue("Anda berhasil login.")
                }
            } else {
                Toast.makeText(this, "Email atau Password salah", Toast.LENGTH_SHORT).show()
                showLoading(false)
            }
        }
    }

    private fun showDialogue(message: String) {
        AlertDialog.Builder(this).apply {
            setTitle("Login")
            setMessage(message)
            setPositiveButton("OK") { _, _ ->
                val intent = Intent(context, DashboardActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
            create()
            show()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun saveToken(token: String) {
        val sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }
}