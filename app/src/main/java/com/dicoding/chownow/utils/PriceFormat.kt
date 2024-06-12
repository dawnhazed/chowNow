package com.dicoding.chownow.utils

import java.text.NumberFormat
import java.util.Locale

// Fungsi untuk memformat harga dalam Rupiah
fun formatRupiah(amount: Int): String {
    val formatter = NumberFormat.getInstance(Locale("in", "ID"))
    return formatter.format(amount)
}
