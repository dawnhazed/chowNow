package com.dicoding.chownow.ui.dashboard.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.ListResto

class SearchRestoAdapter(private val items: List<ListResto>) : RecyclerView.Adapter<SearchRestoAdapter.SearchRestoViewHolder>() {
    class SearchRestoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgResto: ImageView = view.findViewById(R.id.iv_resto)
        val tvNamaResto: TextView = view.findViewById(R.id.tv_nama_resto)
        val tvRatingScale: TextView = view.findViewById(R.id.tv_rating_scale)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRestoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_resto, parent, false)
        return SearchRestoViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchRestoViewHolder, position: Int) {
        val menu = items[position]
        holder.imgResto.setImageResource(menu.imgResto)
        holder.tvNamaResto.text = menu.namaResto
        holder.tvRatingScale.text = holder.itemView.context.getString(R.string.rating_scale, menu.ratingScale)
    }

    override fun getItemCount() = items.size

}