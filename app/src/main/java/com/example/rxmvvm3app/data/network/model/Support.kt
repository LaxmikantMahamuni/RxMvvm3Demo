package com.example.rxmvvm3app.data.network.model

import com.google.gson.annotations.SerializedName

data class Support(@SerializedName("text") val text: String,
                   @SerializedName("url") val url: String)
