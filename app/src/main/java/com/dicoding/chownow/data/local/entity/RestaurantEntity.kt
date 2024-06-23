package com.dicoding.chownow.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant")
data class RestaurantEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val location: String,
    val rating: Float,
    val restoImage: String
)
