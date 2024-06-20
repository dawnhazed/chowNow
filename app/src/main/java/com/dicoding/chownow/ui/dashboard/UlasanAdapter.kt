package com.dicoding.chownow.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.Ulasan
import com.dicoding.chownow.data.remote.response.ReviewResponseItem

class UlasanAdapter(private var items: List<ReviewResponseItem?>) : RecyclerView.Adapter<UlasanAdapter.UlasanViewHolder>() {

    class UlasanViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgResto: ImageView = view.findViewById(R.id.iv_resto)
        val tvNamaResto: TextView = view.findViewById(R.id.tv_nama_resto)
        val tvMenuPesan: TextView = view.findViewById(R.id.tv_menu_pesan)
        val tvReviewText: TextView = view.findViewById(R.id.tv_review_text)
        val tvReviewerName: TextView = view.findViewById(R.id.tv_reviewer_name)
        val imgReviewer: ImageView = view.findViewById(R.id.iv_reviewer)
        val tvReviewTime: TextView = view.findViewById(R.id.tv_antri_info)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UlasanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ulasan_home, parent, false)
        return UlasanViewHolder(view)
    }

    override fun onBindViewHolder(holder: UlasanViewHolder, position: Int) {
        val ulasan = items[position]
        val restoDefault = R.drawable.resto
        val profileDefault = R.drawable.resto

        holder.tvNamaResto.text = "Nama Restoran"
        holder.tvMenuPesan.text = ulasan?.productName
        holder.tvReviewText.text = ulasan?.comment
        holder.tvReviewerName.text = ulasan?.customerName
        holder.tvReviewTime.text = ulasan?.updatedAt

        holder.imgResto.setImageResource(restoDefault)
        holder.imgReviewer.setImageResource(profileDefault)
    }

    fun updateData(newReviews: List<ReviewResponseItem?>) {
        this.items= newReviews
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size
}