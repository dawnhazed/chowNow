package com.dicoding.chownow.ui.dashboard.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.ListMenu

class SearchMenuAdapter(private val items: List<ListMenu>) : RecyclerView.Adapter<SearchMenuAdapter.SearchMenuViewHolder>() {

    class SearchMenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgMenu: ImageView = view.findViewById(R.id.iv_menu)
        val tvNamaMenu: TextView = view.findViewById(R.id.tv_nama_menu)
        val tvEstimateReachDistance: TextView = view.findViewById(R.id.tv_estimate_reach_distance)
        val tvEstimateReachTime: TextView = view.findViewById(R.id.tv_estimate_reach_time)
        val tvRatingScale: TextView = view.findViewById(R.id.tv_rating_scale)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return SearchMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchMenuViewHolder, position: Int) {
        val menu = items[position]
        holder.imgMenu.setImageResource(menu.imgMenu)
        holder.tvNamaMenu.text = menu.namaMenu
        val context = holder.itemView.context
        holder.tvEstimateReachDistance.text = context.getString(R.string.distance_format_km_info, menu.estimateReachDistance)
        holder.tvEstimateReachTime.text = context.getString(R.string.time_format, menu.estimateReachTime)
        holder.tvRatingScale.text = context.getString(R.string.rating_scale, menu.ratingScale)
    }

    override fun getItemCount() = items.size
}