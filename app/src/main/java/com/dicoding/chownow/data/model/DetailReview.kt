package com.dicoding.chownow.data.model

import java.util.Date

data class DetailReview (
    val imgResto: Int,
    val reviewerName: String,
    val menuPesan: String,
    val reviewText: String,
    val imgReviewer: Int,
    val date: Date
)