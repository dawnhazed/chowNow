package com.dicoding.chownow.ui.dashboard.geolocation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.chownow.data.model.Location
import com.dicoding.chownow.databinding.ActivityLocationBinding

class LocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocationBinding

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

        val adapter = LocationAdapter(location)
        binding.rvLocation.adapter = adapter
        binding.rvLocation.layoutManager = LinearLayoutManager(this)
    }
}