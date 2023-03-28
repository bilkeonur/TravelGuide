package com.ob.travelguide.model.Place

data class Result(
    val categories: List<Category?>?,
    val chains: List<Any?>?,
    val distance: Int?,
    val fsq_id: String?,
    val geocodes: Geocodes?,
    val link: String?,
    val location: Location?,
    val name: String?,
    val related_places: RelatedPlaces?,
    val timezone: String?
)