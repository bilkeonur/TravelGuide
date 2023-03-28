package com.ob.travelguide.model.Place

import com.google.gson.annotations.SerializedName

data class DropOff(
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?
)