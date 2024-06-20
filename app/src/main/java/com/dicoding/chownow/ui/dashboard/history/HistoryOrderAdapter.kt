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
import java.text.SimpleDateFormat
import java.util.Locale

class HistoryOrderAdapter(private val items: List<HistoryOrder>) : RecyclerView.Adapter<HistoryOrderAdapter.HistoryOrderViewHolder>() {

    inner class HistoryOrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgResto: ImageView = view.findViewById(R.id.iv_resto)
        val tvNamaResto: TextView = view.findViewById(R.id.tv_nama_resto)
        val tvJumlahPesanan: TextView = view.findViewById(R.id.tv_jumlah_pesanan_value)
        val tvWaktuPesan: TextView = view.findViewById(R.id.tv_waktu_pesan_value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryOrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order_history, parent, false)
        return HistoryOrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryOrderViewHolder, position: Int) {
        val order = items[position]
        holder.imgResto.setImageResource(order.imgResto)
        holder.tvNamaResto.text = order.namaResto
        holder.tvJumlahPesanan.text = "${order.jumlahPesanan} Makanan"

        val context = holder.itemView.context

        // Format date
//        val dateFormatter = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
        val dateFormatter = SimpleDateFormat("dd MMM, HH:mm", Locale.getDefault())
        holder.tvWaktuPesan.text = "${dateFormatter.format(order.waktuPesan)}"

    }

    override fun getItemCount() = items.size
}