package com.ob.travelguide.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ob.travelguide.R
import com.ob.travelguide.databinding.FragmentPlaceBinding
import com.ob.travelguide.databinding.FragmentPlaceDetailBinding
import com.ob.travelguide.viewmodel.PlaceDetailViewModel
import com.ob.travelguide.viewmodel.PlaceViewModel

class PlaceDetailFragment : Fragment(R.layout.fragment_place_detail) {

    private var frogmentPlaceDetailBinding: FragmentPlaceDetailBinding? = null
    lateinit var viewModel: PlaceDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(PlaceDetailViewModel::class.java)

        val binding = FragmentPlaceDetailBinding.bind(view)
        frogmentPlaceDetailBinding = binding

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.place_detail)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        frogmentPlaceDetailBinding = null
    }
}