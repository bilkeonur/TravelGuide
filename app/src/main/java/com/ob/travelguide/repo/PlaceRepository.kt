package com.ob.travelguide.repo

import com.ob.travelguide.model.Place.Place
import com.ob.travelguide.service.FourSquareAPI
import com.ob.travelguide.util.Resource
import javax.inject.Inject

class PlaceRepository @Inject constructor (private val retrofitApi : FourSquareAPI): IPlaceRepository
{
    override suspend fun getPlaces(url:String): Resource<Place> {
        return try {

            val response = retrofitApi.searchPlace(url)

            if (response.isSuccessful) {
                response.body()?.let {places ->
                    return@let Resource.success(places)
                } ?: Resource.error("Error",null)
            } else {
                Resource.error("Error",null)
            }
        }
        catch (e: Exception) {
            Resource.error("No Data!",null)
        }
    }
}