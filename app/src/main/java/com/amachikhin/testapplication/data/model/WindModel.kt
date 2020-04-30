package com.amachikhin.testapplication.data.model

import com.google.gson.annotations.SerializedName

data class WindModel(
    @SerializedName("speed") val speed: Int,
    @SerializedName("deg") val deg: Int
)