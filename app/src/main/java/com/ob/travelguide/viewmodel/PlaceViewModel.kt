package com.ob.travelguide.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ob.travelguide.model.Place.Place
import com.ob.travelguide.repo.IPlaceRepository
import com.ob.travelguide.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class PlaceViewModel @Inject constructor(private val placeRepository: IPlaceRepository): ViewModel() {

    private val places = MutableLiveData<Resource<Place>>()

    val placeList : LiveData<Resource<Place>>
        get() = places

    fun getPlaces() {
        viewModelScope.launch {
            var baseURL = "https://api.foursquare.com/v3/places/search?sort=POPULARITY&limit=50"
            val response = placeRepository.getPlaces(baseURL)
            places.value = response
        }
    }
}