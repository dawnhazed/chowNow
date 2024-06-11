package com.dicoding.chownow.ui.dashboard.geolocation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.Location
import com.dicoding.chownow.databinding.ActivityLocationBinding

class LocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val locationHistory = listOf(
            Location("Nama Lokasi", "Jl. Nama Jalan Nomor Rumah , Kelurahan, Kecamatan, Nama Kota, Kode Pos"),
            Location("Nama Lokasi", "Jl. Nama Jalan Nomor Rumah , Kelurahan, Kecamatan, Nama Kota, Kode Pos"),
            Location("Nama Lokasi", "Jl. Nama Jalan Nomor Rumah , Kelurahan, Kecamatan, Nama Kota, Kode Pos")
        )

        val adapter = LocationHistoryAdapter(locationHistory)
        binding.rvLocationHistory.adapter = adapter
        binding.rvLocationHistory.layoutManager = LinearLayoutManager(this)
    }
}