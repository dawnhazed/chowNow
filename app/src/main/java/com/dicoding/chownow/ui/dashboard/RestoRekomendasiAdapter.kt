package com.dicoding.chownow.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.Resto

class RestoRekomendasiAdapter(private val items: List<Resto>) : RecyclerView.Adapter<RestoRekomendasiAdapter.RestoViewHolder>() {

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
        holder.imgResto.setImageResource(resto.imgResto)
        holder.tvNamaResto.text = resto.namaResto
        val context = holder.itemView.context
        holder.tvRating.text = resto.rating.toString()
    }

    override fun getItemCount() = items.size
}