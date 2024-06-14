package com.dicoding.chownow.data.model

import java.util.Date

data class ConfirmOrder (
    val dateOrder: Date,
    val namaResto: String,
    val estimasiWaktu: Int,
    val nomorPesanan: Int,
    val foodItems: List<FoodItem>,
    val totalMakanan: Int,
    val totalHarga: Int,
    )