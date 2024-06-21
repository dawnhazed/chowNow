package com.dicoding.chownow.ui.dashboard.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.FoodItem
import com.dicoding.chownow.data.model.FoodMenu
import java.text.SimpleDateFormat
import java.util.Locale

class StatusAdapter (private val items: MutableList<FoodItem>) : RecyclerView.Adapter<StatusAdapter.StatusViewHolder>() {

    class StatusViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgResto: ImageView = view.findViewById(R.id.iv_menu)
        val tvQuantity: TextView = view.findViewById(R.id.tv_quantity)
        val tvName: TextView = view.findViewById(R.id.tv_nama_makanan)
        val tvPrice: TextView = view.findViewById(R.id.tv_harga)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food_ordered, parent, false)
        return StatusViewHolder(view)
    }

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        val order = items[position]
        val photo = R.drawable.food_example
        holder.imgResto.setImageResource(photo)
        holder.tvName.text = order.name
        holder.tvQuantity.text = "${order.quantity}x"
        holder.tvPrice.text = "Rp${order.price}"

        val context = holder.itemView.context

    }

    override fun getItemCount() = items.size
}