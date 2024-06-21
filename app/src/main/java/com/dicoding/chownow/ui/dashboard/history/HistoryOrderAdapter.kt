package com.dicoding.chownow.ui.dashboard.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.HistoryOrder
import com.dicoding.chownow.data.model.Location
import com.dicoding.chownow.databinding.ItemOrderHistoryBinding
import java.text.SimpleDateFormat
import java.util.Locale

class HistoryOrderAdapter(private val items: List<HistoryOrder>, private val onClick: (HistoryOrder) -> Unit) : RecyclerView.Adapter<HistoryOrderAdapter.HistoryOrderViewHolder>() {

    class HistoryOrderViewHolder(val binding: ItemOrderHistoryBinding) : RecyclerView.ViewHolder(binding.root)
    /* {
       val imgResto: ImageView = view.findViewById(R.id.iv_resto)
        val tvNamaResto: TextView = view.findViewById(R.id.tv_nama_resto)
        val tvJumlahPesanan: TextView = view.findViewById(R.id.tv_jumlah_pesanan_value)
        val tvWaktuPesan: TextView = view.findViewById(R.id.tv_waktu_pesan_value)
    } */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryOrderViewHolder {
        val binding = ItemOrderHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryOrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryOrderViewHolder, position: Int) {
        val order = items[position]
        val dateFormatter = SimpleDateFormat("dd MMM, HH:mm", Locale.getDefault())
        val photo = R.drawable.food_example
        with(holder.binding){
            ivResto.setImageResource(photo)
            tvNamaResto.text = order.namaResto
            tvWaktuPesanValue.text = "${dateFormatter.format(order.waktuPesan)}"
            tvJumlahPesananValue.text = "${order.jumlahPesanan} Item"
            root.setOnClickListener { onClick(order) }
        }

    }

    override fun getItemCount() = items.size
}