package com.dicoding.chownow.data.remote.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class RestoResponse(

	@field:SerializedName("recommendation")
	val recommendation: List<RecommendationItem>
) : Parcelable

@Parcelize
data class RecommendationItem(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("score")
	val score: Float,

	@field:SerializedName("recommended_restaurant_id")
	val recommendedRestaurantId: Int,

	// nama resto
	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("rating")
	val rating: Int,

	// VVV not used
	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("customer_id")
	val customerId: Int

) : Parcelable
