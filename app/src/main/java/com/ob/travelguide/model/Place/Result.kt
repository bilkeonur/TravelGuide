package com.ob.travelguide.model.Place

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("categories")
    val categories: List<Category?>?,
    @SerializedName("chains")
    val chains: List<Any?>?,
    @SerializedName("distance")
    val distance: Int?,
    @SerializedName("fsq_id")
    val fsqId: String?,
    @SerializedName("geocodes")
    val geocodes: Geocodes?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("location")
    val location: Location?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("related_places")
    val relatedPlaces: RelatedPlaces?,
    @SerializedName("timezone")
    val timezone: String?
)