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

        val location = listOf(
            Location("Jakarta"),
            Location("Bandung"),
            Location("Jogja"),
            Location("Malang"),
            Location("Surabaya")
        )

        val adapter = LocationAdapter(location) { selectedLocation ->
            viewModel.setSelectedLocation(selectedLocation.namaLokasi)
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        binding.rvLocation.adapter = adapter
        binding.rvLocation.layoutManager = LinearLayoutManager(this)
        binding.ivBack.setOnClickListener { backIntent() }
    }

    private fun selectLocation(location: Location) {
        val loc = location.namaLokasi
        val intent = Intent(this, DashboardActivity::class.java).apply {
            putExtra("selected_location", location.namaLokasi)
            Log.d("pilih lokasi", "lokasi = $loc")
        }
        startActivity(intent)
    }

    private fun backIntent(){
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }
}