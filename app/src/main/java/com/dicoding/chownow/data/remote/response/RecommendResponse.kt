// RecommendResponse.kt
package com.dicoding.chownow.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecommendResponse(

	@field:SerializedName("recommendation")
	val recommendation: List<RecommendationItem?>? = null
)

data class RecommendationItem(

	@field:SerializedName("score")
	val score: Any? = null,

	@field:SerializedName("recommended_restaurant_id")
	val recommendedRestaurantId: Int? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("rating")
	val rating: Int? = null
)
