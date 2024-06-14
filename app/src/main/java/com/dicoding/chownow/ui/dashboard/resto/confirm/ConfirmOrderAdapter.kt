package com.dicoding.chownow.ui.dashboard.resto.confirm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.FoodMenu
import com.dicoding.chownow.data.model.HistoryOrder
import com.dicoding.chownow.ui.dashboard.history.HistoryOrderAdapter
import java.text.SimpleDateFormat
import java.util.Locale

class ConfirmOrderAdapter (private val items: List<FoodMenu>) : RecyclerView.Adapter<ConfirmOrderAdapter.ConfirmOrderViewHolder>() {

    class ConfirmOrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgMenu: ImageView = view.findViewById(R.id.iv_menu)
        val tvNamaMenu: TextView = view.findViewById(R.id.tv_nama_menu)
        val tvRatingScale: TextView = view.findViewById(R.id.tv_rating_scale)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfirmOrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return ConfirmOrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConfirmOrderViewHolder, position: Int) {
        val menu = items[position]
        holder.imgMenu.setImageResource(menu.imgMenu)
        holder.tvNamaMenu.text = menu.namaMenu
        val context = holder.itemView.context
        holder.tvRatingScale.text = context.getString(R.string.rating_scale, menu.ratingScale)
    }

    override fun getItemCount() = items.size
}