package com.ob.travelguide.repo

import androidx.lifecycle.MutableLiveData
import com.ob.travelguide.model.Place.*
import com.ob.travelguide.util.Resource

class FakePlaceRepository: IPlaceRepository {

    private val placesLiveData = MutableLiveData<Place>()

    init {
        val center = Center(41.0091,28.9743)
        val radius = 22000
        val circle = Circle(center,radius)
        val geoBounds = GeoBounds(circle)
        val context = Context(geoBounds)
        val prefix = "https://ss3.4sqi.net/img/categories_v2/parks_outdoors/river_"
        val suffix = ".png"
        val name = "Waterfront"
        val id = 16053
        val icon = Icon(prefix,suffix)
        val category = Category(icon,id,name)
        val categories = listOf<Category>(category)
        val chains = listOf<Any>()
        val distance = 6113
        val fsqId = "503fcb18e4b0047a40a633bf"
        val dropOff = DropOff(41.047894,29.026061)
        val main = Main(41.047854,29.026069)
        val roof = Roof(41.047854,29.026069)
        val geocodes = Geocodes(dropOff,main,roof)
        val link = "/v3/places/503fcb18e4b0047a40a633bf"
        val location = Location("Ortaköy Meydanı", "","TR","","","Ortaköy Meydanı, Beşiktaş","Beşiktaş","","Istanbul")
        val result = Result(categories,chains,distance,fsqId,geocodes,link,location,"Ortaköy Sahili",null,"Europe/Istanbul")
        val newPlace = Place(context, listOf<Result>(result))
        placesLiveData.postValue(newPlace)
    }

    override suspend fun getPlaces(url: String): Resource<Place> {
        return Resource.success(placesLiveData.value)
    }
}