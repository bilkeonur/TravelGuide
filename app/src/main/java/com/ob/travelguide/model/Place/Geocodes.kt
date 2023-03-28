package com.ob.travelguide.model.Place

import com.google.gson.annotations.SerializedName

data class Geocodes(
    @SerializedName("drop_off")
    val dropOff: DropOff?,
    @SerializedName("main")
    val main: Main?,
    @SerializedName("roof")
    val roof: Roof?
)