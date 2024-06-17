package com.dicoding.chownow.ui.dashboard.resto.confirm

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.FoodItem
import com.dicoding.chownow.databinding.ActivityConfirmOrderBinding
import com.dicoding.chownow.ui.dashboard.history.StatusActivity
import com.dicoding.chownow.ui.dashboard.resto.FoodItemAdapter
import com.dicoding.chownow.ui.dashboard.resto.detail.RestoDetailActivity

class ConfirmOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmOrderBinding
    private lateinit var adapter: FoodItemAdapter

    private val foodItems = mutableListOf(
        FoodItem("Nama Makanan A", 25000, 1),
        FoodItem("Nama Makanan B", 25000, 1),
        FoodItem("Nama Makanan C", 25000, 1)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvUbahPesanan.setOnClickListener { ubahPesanan() }
        binding.btnOrder.setOnClickListener { orderFood() }
        binding.backIcon.setOnClickListener { backIntent() }

        // recyclerview
        adapter = FoodItemAdapter(foodItems) { updateTotals() }
        binding.rvFoodItems.layoutManager = LinearLayoutManager(this)
        binding.rvFoodItems.adapter = adapter

        updateTotals()
    }

    private fun ubahPesanan() {
        val intent = Intent(this, RestoDetailActivity::class.java)
        startActivity(intent)
    }

    private fun orderFood(){
        val orderedItems = foodItems.filter { it.quantity > 0 }
        if (orderedItems.isNotEmpty()) {
            Toast.makeText(this, "Order Success!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, StatusActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Please select items", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateTotals(){
        val totalItems = foodItems.sumOf { it.quantity }
        val totalPrice = foodItems.sumOf { it.quantity * it.price }

        with(binding){
            if(totalItems == 1){ tvTotalItems.text = "$totalItems Item" }
            else { tvTotalItems.text = "$totalItems Items" }

            tvTotalPrice.text = "Rp $totalPrice"
        }
    }

    private fun backIntent(){
        val intent = Intent(this, StatusActivity::class.java)
        startActivity(intent)
    }
}