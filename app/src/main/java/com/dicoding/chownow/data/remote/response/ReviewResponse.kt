package com.dicoding.chownow.data.remote.response

import com.google.gson.annotations.SerializedName

data class ReviewResponse(

	@field:SerializedName("ReviewResponse")
	val reviewResponse: List<ReviewResponseItem?>? = null
)

data class ReviewResponseItem(

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("comment")
	val comment: String? = null,

	@field:SerializedName("customer_name")
	val customerName: String? = null,

	@field:SerializedName("product_name")
	val productName: String? = null
)
