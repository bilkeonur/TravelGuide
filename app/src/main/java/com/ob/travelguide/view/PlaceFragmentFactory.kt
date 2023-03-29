package com.ob.travelguide.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.ob.travelguide.adapter.PlaceRecyclerAdapter
import javax.inject.Inject

class PlaceFragmentFactory @Inject constructor(
    private val placeRecyclerAdapter: PlaceRecyclerAdapter,
    private val glide: RequestManager): FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            PlaceFragment::class.java.name -> PlaceFragment(placeRecyclerAdapter, glide)
            else -> super.instantiate(classLoader, className)
        }
    }
}