package com.ob.travelguide.repo

import com.ob.travelguide.model.Place.Place
import com.ob.travelguide.util.Resource

interface IPlaceRepository {
    suspend fun getPlaces(url:String) : Resource<Place>
}