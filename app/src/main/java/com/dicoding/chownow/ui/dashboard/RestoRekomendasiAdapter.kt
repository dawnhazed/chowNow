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
import com.dicoding.chownow.data.model.Resto

class RestoRekomendasiAdapter(private val items: List<ListResto>) : RecyclerView.Adapter<RestoRekomendasiAdapter.RestoViewHolder>() {

    class RestoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgResto: ImageView = view.findViewById(R.id.img_resto)
        val tvNamaResto: TextView = view.findViewById(R.id.tv_nama_resto)
        val tvRating: TextView = view.findViewById(R.id.tv_rating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rekomendasi, parent, false)
        return RestoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestoViewHolder, position: Int) {
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
        holder.tvRating.text = resto.ratingScale.toString()
    }

    override fun getItemCount() = items.size
}