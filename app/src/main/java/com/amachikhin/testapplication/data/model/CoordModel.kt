package com.amachikhin.testapplication.data.model

import com.google.gson.annotations.SerializedName

data class CoordModel (
	@SerializedName("lon") val lon : Double,
	@SerializedName("lat") val lat : Double
)