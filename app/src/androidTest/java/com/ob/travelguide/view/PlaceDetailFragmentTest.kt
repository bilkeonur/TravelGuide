package com.ob.travelguide.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.filters.MediumTest
import com.ob.travelguide.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
class PlaceDetailFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory: PlaceFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testOnBackPressed() {

        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<PlaceDetailFragment>(factory = fragmentFactory) {
            Navigation.setViewNavController(requireView(),navController)
        }

        Espresso.pressBack()

        Mockito.verify(navController).popBackStack()
    }
}