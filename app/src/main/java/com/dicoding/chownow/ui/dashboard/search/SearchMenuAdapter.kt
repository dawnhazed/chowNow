package com.dicoding.chownow.ui.dashboard.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.chownow.R
import com.dicoding.chownow.data.remote.response.MenuItem

class SearchMenuAdapter(private val items: List<MenuItem>) : RecyclerView.Adapter<SearchMenuAdapter.SearchMenuViewHolder>() {

    class SearchMenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgMenu: ImageView = view.findViewById(R.id.iv_menu)
        val tvNamaMenu: TextView = view.findViewById(R.id.tv_nama_menu)
        val tvDescription: TextView = view.findViewById(R.id.tv_description)
        val tvPrice: TextView = view.findViewById(R.id.tv_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu_search, parent, false)
        return SearchMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchMenuViewHolder, position: Int) {
        val menu = items[position]
        Glide.with(holder.itemView.context)
            .load(menu.image)
            .into(holder.imgMenu)
        holder.tvNamaMenu.text = menu.name
        holder.tvDescription.text = menu.description
        holder.tvPrice.text = holder.itemView.context.getString(R.string.price_format, menu.price)
    }

    override fun getItemCount() = items.size
}
