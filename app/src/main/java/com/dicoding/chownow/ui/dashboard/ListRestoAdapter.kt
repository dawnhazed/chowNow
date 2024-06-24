package com.dicoding.chownow.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.ListResto

class ListRestoAdapter(private val items: List<ListResto>) : RecyclerView.Adapter<ListRestoAdapter.ListRestoViewHolder>() {

    class ListRestoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgResto: ImageView = view.findViewById(R.id.iv_resto)
        val tvNamaResto: TextView = view.findViewById(R.id.tv_nama_resto)
        val tvRatingScale: TextView = view.findViewById(R.id.tv_rating_scale)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListRestoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_resto, parent, false)
        return ListRestoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListRestoViewHolder, position: Int) {
        val resto = items[position]

        // Load image based on the type of imgResto
        if (resto.imgResto is String) {
            Glide.with(holder.itemView.context)
                .load(resto.imgResto)
                .placeholder(R.drawable.breakfast)
                .into(holder.imgResto)
        } else if (resto.imgResto is Int) {
            holder.imgResto.setImageResource(resto.imgResto)
        }

        holder.tvNamaResto.text = resto.namaResto
        holder.tvRatingScale.text = resto.ratingScale.toString()
    }

    override fun getItemCount() = items.size
}
