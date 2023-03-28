package com.ob.travelguide.model.Place

import com.google.gson.annotations.SerializedName

data class PlaceModel(
    @SerializedName("context")
    val context: Context?,
    @SerializedName("results")
    val results: List<Result?>?
)