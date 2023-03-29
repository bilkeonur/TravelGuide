package com.ob.travelguide.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.ob.travelguide.MainCoroutineRule
import com.ob.travelguide.getOrAwaitValueTest
import com.ob.travelguide.repo.FakePlaceRepository
import com.ob.travelguide.util.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PlaceViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: PlaceViewModel

    @Before
    fun Setup() {
        viewModel = PlaceViewModel(FakePlaceRepository())
    }

    @After
    fun tearDown() {

    }

    @Test
    fun `Get Places`() = runBlocking {
        viewModel.getPlaces()
        val placeList = viewModel.placeList.getOrAwaitValueTest()
        assertThat(placeList.status).isEqualTo(Status.SUCCESS)
    }
}