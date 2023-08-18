package com.example.rxmvvm3app.data.network.model

import com.google.gson.annotations.SerializedName

data class ResponseallUsers(
    @SerializedName("page") val page: Int,
    @SerializedName("per_page") val perPage: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("data") val usersList: List<User>,
    @SerializedName("support") val support: Support, )
