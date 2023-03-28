package com.ob.travelguide.model.Place

import com.google.gson.annotations.SerializedName

data class GeoBounds(
    @SerializedName("circle")
    val circle: Circle?
)