package com.dicoding.chownow.ui.dashboard.geolocation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.Location
import com.dicoding.chownow.databinding.ItemLocationBinding

class LocationAdapter(private val location: List<Location>, private val onLocationClick: (Location) -> Unit) :
    RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    class LocationViewHolder(val binding: ItemLocationBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = location[position]
        with(holder.binding) {
            tvNamaLokasi.text = location.namaLokasi
            root.setOnClickListener { onLocationClick(location) }
        }
    }

    override fun getItemCount() = location.size
}
