package com.ob.travelguide.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.ob.travelguide.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.ob.travelguide.R
import com.ob.travelguide.adapter.PlaceRecyclerAdapter
import com.ob.travelguide.getOrAwaitValueTest
import com.ob.travelguide.model.Place.Place
import com.ob.travelguide.model.Place.Result
import com.ob.travelguide.repo.FakePlaceRepository
import com.ob.travelguide.viewmodel.PlaceViewModel

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class PlaceFragmentTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: PlaceFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun selectPlace() {

        val navController = Mockito.mock(NavController::class.java)
        val testViewModel = PlaceViewModel(FakePlaceRepository())

        launchFragmentInHiltContainer<PlaceFragment>(factory = fragmentFactory) {
            Navigation.setViewNavController(requireView(),navController)
            viewModel = testViewModel
            viewModel.getPlaces()
            var testPlaceList = testViewModel.placeList.getOrAwaitValueTest().data as Place
            placeRecyclerAdapter.places = listOf(testPlaceList.results!!.get(0)!!)
        }

        Espresso.onView(withId(R.id.rcvPlaces)).perform(
            RecyclerViewActions.actionOnItemAtPosition<PlaceRecyclerAdapter.PlaceViewHolder>(0,click())
        )

        var testPlaceList = testViewModel.placeList.getOrAwaitValueTest().data as Place

        Mockito.verify(navController).navigate(
            PlaceFragmentDirections.actionPlaceFragmentToPlaceDetailFragment(testPlaceList.results!!.get(0)!!)
        )
    }
}