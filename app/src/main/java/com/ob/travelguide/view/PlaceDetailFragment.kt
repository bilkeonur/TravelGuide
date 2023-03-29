package com.ob.travelguide.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ob.travelguide.R
import com.ob.travelguide.databinding.FragmentPlaceDetailBinding
import com.ob.travelguide.viewmodel.PlaceDetailViewModel

class PlaceDetailFragment : Fragment(R.layout.fragment_place_detail) {

    private var fragmentPlaceDetailBinding: FragmentPlaceDetailBinding? = null
    private lateinit var viewModel: PlaceDetailViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[PlaceDetailViewModel::class.java]

        val binding = FragmentPlaceDetailBinding.bind(view)
        fragmentPlaceDetailBinding = binding

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.place_detail)

        arguments?.let {
            var selectedPlace = PlaceDetailFragmentArgs.fromBundle(it).place
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentPlaceDetailBinding = null
    }
}