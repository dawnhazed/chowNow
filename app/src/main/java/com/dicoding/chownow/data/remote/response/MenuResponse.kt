package com.dicoding.chownow.data.remote.response

import com.google.gson.annotations.SerializedName

data class MenuResponse(

    @field:SerializedName("menu")
    val menu: List<MenuItem>? = null
)

data class MenuItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("image")
    val image: String? = null
)
