package com.dicoding.chownow.ui.dashboard.geolocation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.chownow.data.model.Location
import com.dicoding.chownow.databinding.ActivityLocationBinding
import com.dicoding.chownow.ui.dashboard.DashboardActivity
import com.dicoding.chownow.ui.dashboard.home.HomeViewModel

class LocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocationBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val locations = listOf(
            Location("Jakarta"),
            Location("Bandung"),
            Location("Jogja"),
            Location("Malang"),
            Location("Surabaya")
        )

        val adapter = LocationAdapter(locations) { selectedLocation ->
            val intent = Intent().apply {
                putExtra("selected_location", selectedLocation.namaLokasi)
            }
            setResult(RESULT_OK, intent)
            finish() // Close the LocationActivity and return to HomeFragment
        }

        binding.rvLocation.adapter = adapter
        binding.rvLocation.layoutManager = LinearLayoutManager(this)
        binding.ivBack.setOnClickListener { finish() }
    }
}