package com.dicoding.chownow.ui.dashboard.resto.confirm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.FoodItem
import com.dicoding.chownow.databinding.ActivityConfirmOrderBinding
import com.dicoding.chownow.ui.dashboard.resto.FoodItemAdapter

class ConfirmOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val foodItems = listOf(
//            FoodItem("Nama Makanan A", 25000, 1),
//            FoodItem("Nama Makanan B", 25000, 1),
//            FoodItem("Nama Makanan C", 25000, 1),
//            FoodItem("Nama Makanan D", 25000, 1),
//            FoodItem("Nama Makanan E", 25000, 1),
//            FoodItem("Nama Makanan F", 25000, 1),
//            FoodItem("Nama Makanan G", 25000, 1),
//            FoodItem("Nama Makanan H", 25000, 1)
//        )
//
//        val adapter = FoodItemAdapter(foodItems)
//        binding.rvFoodItems.adapter = adapter
//        binding.rvFoodItems.layoutManager = LinearLayoutManager(this)

    }
}