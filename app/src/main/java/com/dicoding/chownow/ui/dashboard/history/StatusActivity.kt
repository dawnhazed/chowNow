package com.dicoding.chownow.ui.dashboard.history

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
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

        setupView()
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
}