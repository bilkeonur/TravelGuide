package com.ob.travelguide.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.ob.travelguide.R
import com.ob.travelguide.databinding.FragmentPlaceDetailBinding
import com.ob.travelguide.model.Place.Icon
import com.ob.travelguide.viewmodel.PlaceDetailViewModel
import javax.inject.Inject


class PlaceDetailFragment @Inject constructor(val glide: RequestManager) : Fragment(R.layout.fragment_place_detail) {

    private var fragmentPlaceDetailBinding: FragmentPlaceDetailBinding? = null
    private lateinit var viewModel: PlaceDetailViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[PlaceDetailViewModel::class.java]

        val binding = FragmentPlaceDetailBinding.bind(view)
        fragmentPlaceDetailBinding = binding

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.place_detail)

        val imgIcon = binding.imgIcon
        val txtPlaceName = binding.txtPlaceName
        val txtPlaceCategory = binding.txtPlaceCategory
        val txtPlaceAddress = binding.txtPlaceAddress
        val btnDirections = binding.btnDirections

        arguments?.let {
            var selectedPlace = PlaceDetailFragmentArgs.fromBundle(it).place

            val icon: Icon? = selectedPlace.categories?.get(0)?.icon
            val iconUrl = icon?.prefix + "64" + icon?.suffix

            val placeName = selectedPlace.name
            val placeCategory = selectedPlace.categories?.get(0)?.name
            val placeAddress = selectedPlace.location?.formattedAddress
            val latitude = selectedPlace.geocodes?.main?.latitude
            val longitude = selectedPlace.geocodes?.main?.longitude

            glide.load(iconUrl).into(imgIcon)
            txtPlaceName.setText(placeName)
            txtPlaceCategory.setText(placeCategory)
            txtPlaceAddress.setText(placeAddress)

            btnDirections.setOnClickListener {
                if(latitude != 0.0 && longitude != 0.0) {
                    val gmmIntentUri = Uri.parse("google.navigation:q=" + latitude.toString() + ","+longitude.toString())
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    startActivity(mapIntent)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentPlaceDetailBinding = null
    }
}