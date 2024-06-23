package com.dicoding.chownow.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("token")
    val token: String? = null,

    @field:SerializedName("error")
    val error: String? = null,

    @field:SerializedName("userId")
    val userId: String? = null

)