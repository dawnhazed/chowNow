package com.dicoding.chownow.ui.dashboard.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.chownow.R
import com.dicoding.chownow.data.local.entity.RestaurantEntity

class SearchRestoAdapter(private val items: List<RestaurantEntity>) : RecyclerView.Adapter<SearchRestoAdapter.SearchRestoViewHolder>() {

    class SearchRestoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgResto: ImageView = view.findViewById(R.id.iv_resto)
        val tvNamaResto: TextView = view.findViewById(R.id.tv_nama_resto)
        val tvLocation: TextView = view.findViewById(R.id.tv_location)
        val ratingBar: RatingBar = view.findViewById(R.id.rating_bar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRestoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_resto_search, parent, false)
        return SearchRestoViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchRestoViewHolder, position: Int) {
        val resto = items[position]
        Glide.with(holder.itemView.context)
            .load(resto.restoImage)
            .into(holder.imgResto)
        holder.tvNamaResto.text = resto.name
        holder.tvLocation.text = resto.location
        holder.ratingBar.rating = resto.rating
    }

    override fun getItemCount() = items.size
}
