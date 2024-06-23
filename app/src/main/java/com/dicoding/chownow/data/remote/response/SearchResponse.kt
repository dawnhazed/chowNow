package com.dicoding.chownow.data.remote.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(

	@field:SerializedName("restaurants")
	val restaurants: List<RestaurantItem>? = null
)

data class RestaurantItem(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("rating")
	val rating: Int? = null,

	@field:SerializedName("restoImage")
	val restoImage: String? = null
)
