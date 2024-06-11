package com.dicoding.chownow.data.model

import java.util.Date

data class HistoryOrder(
    val imgResto: Int,
    val namaResto: String,
    val jumlahPesanan: Int,
    val waktuPesan: Date,
    val isDone: Boolean
)