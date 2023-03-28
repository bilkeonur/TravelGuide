package com.ob.travelguide.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ob.travelguide.R
import com.ob.travelguide.databinding.FragmentPlaceBinding
import com.ob.travelguide.viewmodel.PlaceViewModel

class PlaceFragment : Fragment() {

    private var frogmentPlaceBinding: FragmentPlaceBinding? = null
    lateinit var viewModel: PlaceViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(PlaceViewModel::class.java)

        val binding = FragmentPlaceBinding.bind(view)
        frogmentPlaceBinding = binding

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.place_title)
    }
}