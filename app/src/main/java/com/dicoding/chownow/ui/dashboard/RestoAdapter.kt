package com.dicoding.chownow.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.Resto

class RestoAdapter(private val items: List<Resto>) : RecyclerView.Adapter<RestoAdapter.RestoViewHolder>() {

    class RestoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNamaResto: TextView = view.findViewById(R.id.tv_nama_resto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rekomendasi, parent, false)
        return RestoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestoViewHolder, position: Int) {
        holder.tvNamaResto.text = items[position].toString()
    }

    override fun getItemCount() = items.size
}