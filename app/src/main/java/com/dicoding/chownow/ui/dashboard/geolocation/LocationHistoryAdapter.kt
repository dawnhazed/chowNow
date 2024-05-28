package com.dicoding.chownow.ui.dashboard.geolocation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.Location
import com.dicoding.chownow.databinding.ItemLocationBinding

class LocationHistoryAdapter(private val locationHistory: List<Location>) :
    RecyclerView.Adapter<LocationHistoryAdapter.LocationViewHolder>() {

    class LocationViewHolder(val binding: ItemLocationBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = locationHistory[position]
        with(holder.binding) {
            tvLocationName.text = location.name
            tvLocationAddress.text = location.address
            ivLocationIcon.setImageResource(R.drawable.location_on) // Replace with your location icon
        }
    }

    override fun getItemCount() = locationHistory.size
}
