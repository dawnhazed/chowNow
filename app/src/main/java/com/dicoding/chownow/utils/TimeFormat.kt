package com.dicoding.chownow.utils

import java.util.Date
import java.util.concurrent.TimeUnit

class TimeFormat {
    // Fungsi untuk menghitung perbedaan hari
    fun calculateDaysAgo(reviewDate: Date): String {
        val currentDate = Date()
        val diffInMillis = currentDate.time - reviewDate.time
        val diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis)

        return when {
            diffInDays < 1 -> "Hari ini"
            diffInDays == 1L -> "Kemarin"
            else -> "$diffInDays hari lalu"
        }
    }
}