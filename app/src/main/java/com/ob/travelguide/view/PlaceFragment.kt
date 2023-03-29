package com.ob.travelguide.view

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.RequestManager
import com.ob.travelguide.R
import com.ob.travelguide.adapter.PlaceRecyclerAdapter
import com.ob.travelguide.databinding.FragmentPlaceBinding
import com.ob.travelguide.util.Status
import com.ob.travelguide.viewmodel.PlaceViewModel
import com.ob.travelguide.model.Place.Result
import javax.inject.Inject

class PlaceFragment @Inject constructor(val placeRecyclerAdapter: PlaceRecyclerAdapter, val glide: RequestManager) : Fragment(R.layout.fragment_place) {

    private var frogmentPlaceBinding: FragmentPlaceBinding? = null
    lateinit var viewModel: PlaceViewModel

    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var recyclerViewPlaces:RecyclerView
    lateinit var pbLoading:ProgressBar
    lateinit var txtNoData:TextView

    var placesList = arrayListOf<Result>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(PlaceViewModel::class.java)

        val binding = FragmentPlaceBinding.bind(view)
        frogmentPlaceBinding = binding

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.place_title)

        swipeRefreshLayout = binding.swipeRefreshLayout
        recyclerViewPlaces = binding.rcvPlaces
        pbLoading = binding.pbLoading
        txtNoData = binding.txtNoData

        placeRecyclerAdapter.places = placesList
        recyclerViewPlaces.adapter = placeRecyclerAdapter
        recyclerViewPlaces.layoutManager = LinearLayoutManager(requireContext())

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getPlaces()
        }

        placeRecyclerAdapter.itemClickListener = { position ->
            val selectedPlace = placeRecyclerAdapter.places[position]
            val action = PlaceFragmentDirections.actionPlaceFragmentToPlaceDetailFragment(selectedPlace)
            findNavController().navigate(action)
        }

        subscribeToObservers()

        viewModel.getPlaces()
    }

    private fun subscribeToObservers() {
        viewModel.placeList.observe(viewLifecycleOwner) { resource ->

            swipeRefreshLayout.isRefreshing = false

            when (resource.status) {
                Status.SUCCESS -> {

                    var places = resource.data?.results as ArrayList<Result>

                    if (places.size > 0) {

                        pbLoading.visibility = View.GONE
                        recyclerViewPlaces.visibility = View.VISIBLE
                        txtNoData.visibility = View.GONE

                        placesList.clear();
                        placesList.addAll(places)
                        placeRecyclerAdapter.notifyDataSetChanged()
                    } else {
                        pbLoading.visibility = View.GONE
                        recyclerViewPlaces.visibility = View.GONE
                        txtNoData.visibility = View.VISIBLE
                    }
                }

                Status.ERROR -> {
                    pbLoading.visibility = View.GONE
                    recyclerViewPlaces.visibility = View.GONE
                    txtNoData.visibility = View.VISIBLE
                    println("Error : ${resource.message}")
                }

                Status.LOADING -> {
                    pbLoading.visibility = View.VISIBLE
                    recyclerViewPlaces.visibility = View.GONE
                    txtNoData.visibility = View.GONE
                    println("Loading")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        frogmentPlaceBinding = null
    }
}