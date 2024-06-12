package com.dicoding.chownow.ui.dashboard.resto.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.chownow.R
import com.dicoding.chownow.data.model.DetailReview
import com.dicoding.chownow.utils.TimeFormat
import java.util.*

class DetailReviewAdapter(private val items: List<DetailReview>) : RecyclerView.Adapter<DetailReviewAdapter.DetailReviewViewHolder>() {

    class DetailReviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgResto: ImageView = view.findViewById(R.id.iv_resto)
        val tvReviewerName: TextView = view.findViewById(R.id.tv_nama_reviewer)
        val tvMenuPesan: TextView = view.findViewById(R.id.tv_item_count)
        val tvReviewText: TextView = view.findViewById(R.id.tv_review)
        val tvTime: TextView = view.findViewById(R.id.tv_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail_review, parent, false)
        return DetailReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailReviewViewHolder, position: Int) {
        val ulasan = items[position]
        holder.imgResto.setImageResource(ulasan.imgResto)
        holder.tvReviewerName.text = ulasan.reviewerName
        holder.tvMenuPesan.text = ulasan.menuPesan
        holder.tvReviewText.text = ulasan.reviewText

        // Format and set the date as "x hari lalu"
        val timeFormat = TimeFormat()
        holder.tvTime.text = timeFormat.calculateDaysAgo(ulasan.date)
    }

    override fun getItemCount() = items.size
}