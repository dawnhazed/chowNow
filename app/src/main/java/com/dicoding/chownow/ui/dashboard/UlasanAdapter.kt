package com.dicoding.chownow.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.Ulasan

class UlasanAdapter(private val ulasanList: List<Ulasan>) : RecyclerView.Adapter<UlasanAdapter.UlasanViewHolder>() {

    class UlasanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagePlaceholder: ImageView = itemView.findViewById(R.id.image_placeholder)
        val title: TextView = itemView.findViewById(R.id.title)
        val subtitle: TextView = itemView.findViewById(R.id.subtitle)
        val reviewText: TextView = itemView.findViewById(R.id.review_text)
        val reviewerName: TextView = itemView.findViewById(R.id.reviewer_name)
        val antriInfo: TextView = itemView.findViewById(R.id.antri_info)
        val antriIcon: ImageView = itemView.findViewById(R.id.antri_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UlasanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ulasan_home, parent, false)
        return UlasanViewHolder(view)
    }

    override fun onBindViewHolder(holder: UlasanViewHolder, position: Int) {
        val ulasan = ulasanList[position]
        // Bind data ke view
        holder.title.text = ulasan.title
        holder.subtitle.text = ulasan.subtitle
        holder.reviewText.text = ulasan.reviewText
        holder.reviewerName.text = ulasan.reviewerName
        holder.antriInfo.text = ulasan.antriInfo
    }

    override fun getItemCount(): Int {
        return ulasanList.size
    }
}
