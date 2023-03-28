package com.ob.travelguide.model.Place

import com.google.gson.annotations.SerializedName

data class Context(
    @SerializedName("geo_bounds")
    val geoBounds: GeoBounds?
)