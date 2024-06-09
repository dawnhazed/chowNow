package com.dicoding.chownow.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.Ulasan

class UlasanAdapter(private val items: List<Ulasan>) : RecyclerView.Adapter<UlasanAdapter.UlasanViewHolder>() {

    class UlasanViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgResto: ImageView = view.findViewById(R.id.iv_resto)
        val tvNamaResto: TextView = view.findViewById(R.id.tv_nama_resto)
        val tvMenuPesan: TextView = view.findViewById(R.id.tv_menu_pesan)
        val tvReviewText: TextView = view.findViewById(R.id.tv_review_text)
        val tvReviewerName: TextView = view.findViewById(R.id.tv_reviewer_name)
        val imgReviewer: ImageView = view.findViewById(R.id.iv_reviewer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UlasanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ulasan_home, parent, false)
        return UlasanViewHolder(view)
    }

    override fun onBindViewHolder(holder: UlasanViewHolder, position: Int) {
        val ulasan = items[position]
        holder.imgResto.setImageResource(ulasan.imgResto)
        holder.tvNamaResto.text = ulasan.namaResto
        holder.tvMenuPesan.text = ulasan.menuPesan
        holder.tvReviewText.text = ulasan.reviewText
        holder.tvReviewerName.text = ulasan.reviewerName
        holder.imgReviewer.setImageResource(ulasan.imgReviewer)
    }

    override fun getItemCount() = items.size
}