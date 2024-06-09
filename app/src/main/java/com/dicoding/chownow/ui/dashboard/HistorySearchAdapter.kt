package com.dicoding.chownow.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.ListResto

class HistorySearchAdapter(private val items: List<ListResto>) : RecyclerView.Adapter<HistorySearchAdapter.HistorySearchViewHolder>() {

    class HistorySearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgResto: ImageView = view.findViewById(R.id.iv_resto)
        val tvNamaResto: TextView = view.findViewById(R.id.tv_nama_resto)
        val tvEstimateReachDistance: TextView = view.findViewById(R.id.tv_estimate_reach_distance)
        val tvEstimateReachTime: TextView = view.findViewById(R.id.tv_estimate_reach_time)
        val tvRatingScale: TextView = view.findViewById(R.id.tv_rating_scale)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorySearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_resto, parent, false)
        return HistorySearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistorySearchViewHolder, position: Int) {
        val resto = items[position]
        holder.imgResto.setImageResource(resto.imgResto)
        holder.tvNamaResto.text = resto.namaResto
//        holder.tvEstimateReach.text = resto.estimateReach
        val context = holder.itemView.context
        holder.tvEstimateReachDistance.text = context.getString(R.string.distance_format_km_info, resto.estimateReachDistance)
        holder.tvEstimateReachTime.text = context.getString(R.string.time_format, resto.estimateReachTime)
        holder.tvRatingScale.text = context.getString(R.string.rating_scale, resto.ratingScale)
//        holder.tvEstimateReach.text = context.getString(R.string.distance_format, resto.estimateReach)
//        holder.tvRating.text = resto.rating.toString()
    }

    override fun getItemCount() = items.size
}