package com.dicoding.chownow.ui.dashboard.history

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.FoodItem
import com.dicoding.chownow.data.model.FoodMenu
import com.dicoding.chownow.databinding.ActivityRestoDetailBinding
import com.dicoding.chownow.databinding.ActivityStatusBinding
import java.util.Date
import java.util.Locale

class StatusActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatusBinding
    private lateinit var adapter: StatusAdapter

    private val foodItems = mutableListOf(
        FoodItem( "Nama Makanan A", 25000, 1),
        FoodItem("Nama Makanan B", 25000, 1),
        FoodItem("Nama Makanan C", 25000, 1)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatusBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backIcon.setOnClickListener { backIntent() }

        adapter = StatusAdapter(foodItems)
        binding.rvStatus.layoutManager = LinearLayoutManager(this)
        binding.rvStatus.adapter = adapter

        val editTextReview: EditText = findViewById(R.id.et_review)

        editTextReview.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val DRAWABLE_END = 2
                if (event.rawX >= (editTextReview.right - editTextReview.compoundDrawables[DRAWABLE_END].bounds.width())) {
                    val reviewText = editTextReview.text.toString()
                    if (reviewText.isNotEmpty()) {
                        Toast.makeText(this, "Review sent!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Please enter a review", Toast.LENGTH_SHORT).show()
                    }
                    return@setOnTouchListener true
                }
            }
            false
        }

        editTextReview.setOnClickListener {

        }

        setupView()
        updateTotals()
        intentData()
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

    private fun backIntent(){
        val intent = Intent(this, HistoryFragment::class.java)
        startActivity(intent)
    }

    private fun updateTotals(){
        val totalPrice = foodItems.sumOf { it.quantity * it.price }
        binding.totalText.text = "Rp$totalPrice"
    }

    private fun intentData(){
        val namaResto = intent.getStringExtra("EXTRA_NAMA_RESTO")
        val waktuPesan = intent.getLongExtra("EXTRA_WAKTU_PESAN", -1L)
        val jumlahPesanan = intent.getIntExtra("EXTRA_JUMLAH_PESANAN", -1)

        val formattedDate = if (waktuPesan != -1L) {
            SimpleDateFormat("dd MMMM y  HH:mm", Locale.getDefault()).format(Date(waktuPesan))
        } else {
            "Unknown Date"
        }

        binding.tvNamaRestoran.text = namaResto
        binding.tvTanggal.text = formattedDate
    }
}