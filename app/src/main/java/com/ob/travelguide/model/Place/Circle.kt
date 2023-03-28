package com.ob.travelguide.model.Place

import com.google.gson.annotations.SerializedName

data class Circle(
    @SerializedName("center")
    val center: Center?,
    @SerializedName("radius")
    val radius: Int?
)