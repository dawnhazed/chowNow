package com.dicoding.chownow.ui.dashboard.resto.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.FoodMenu
import com.dicoding.chownow.utils.formatRupiah

class DetailMenuAdapter(private val items: List<FoodMenu>) : RecyclerView.Adapter<DetailMenuAdapter.DetailMenuViewHolder>() {

    class DetailMenuViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgMenu: ImageView = view.findViewById(R.id.iv_menu)
        val tvNamaMakanan: TextView = view.findViewById(R.id.tv_nama_makanan)
        val tvHarga: TextView = view.findViewById(R.id.tv_harga)
        val tvQuantity: TextView = view.findViewById(R.id.tv_quantity)
        val btnIncrease: Button = view.findViewById(R.id.btn_increase)
        val btnDecrease: Button = view.findViewById(R.id.btn_decrease)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailMenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return DetailMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailMenuViewHolder, position: Int) {
        val menu = items[position]
        holder.imgMenu.setImageResource(menu.imgMenu)
        holder.tvNamaMakanan.text = menu.namaMakanan

        val formattedPrice = formatRupiah(menu.harga)
        holder.tvHarga.text = holder.itemView.context.getString(R.string.price_format, formattedPrice)

        holder.tvQuantity.text = menu.quantity.toString()
        holder.btnIncrease.setOnClickListener {
            menu.quantity++
            holder.tvQuantity.text = menu.quantity.toString()
        }

        holder.btnDecrease.setOnClickListener {
            if (menu.quantity > 1) {
                menu.quantity--
                holder.tvQuantity.text = menu.quantity.toString()
            }
        }
    }

    override fun getItemCount() = items.size
}
