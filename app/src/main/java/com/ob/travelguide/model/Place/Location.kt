package com.ob.travelguide.model.Place

data class Location(
    val address: String?,
    val census_block: String?,
    val country: String?,
    val cross_street: String?,
    val dma: String?,
    val formatted_address: String?,
    val locality: String?,
    val postcode: String?,
    val region: String?
)