package com.ob.travelguide.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ob.travelguide.R
import com.ob.travelguide.databinding.FragmentPlaceBinding
import com.ob.travelguide.util.Status
import com.ob.travelguide.viewmodel.PlaceViewModel
import kotlinx.coroutines.*

class PlaceFragment : Fragment(R.layout.fragment_place) {

    private var frogmentPlaceBinding: FragmentPlaceBinding? = null
    lateinit var viewModel: PlaceViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(PlaceViewModel::class.java)

        val binding = FragmentPlaceBinding.bind(view)
        frogmentPlaceBinding = binding

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.place_title)

        subscribeToObservers()

        viewModel.getPlaces()
    }

    private fun subscribeToObservers() {
        viewModel.placeList.observe(viewLifecycleOwner, Observer { resource ->
            when(resource.status) {
                Status.SUCCESS -> {
                    println("Success")
                }

                Status.ERROR -> {
                    println("Error : ${resource.message}")
                }

                Status.LOADING -> {
                    println("Loading")
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        frogmentPlaceBinding = null
    }
}