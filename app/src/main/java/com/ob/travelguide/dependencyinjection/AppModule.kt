package com.ob.travelguide.dependencyinjection

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ob.travelguide.R
import com.ob.travelguide.repo.IPlaceRepository
import com.ob.travelguide.repo.PlaceRepository
import com.ob.travelguide.service.FourSquareAPI
import com.ob.travelguide.util.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRepo(api: FourSquareAPI) = PlaceRepository(api) as IPlaceRepository

    @Singleton
    @Provides
    fun injectRetrofitAPI() : FourSquareAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Util.baseUrl)
            .build().create(FourSquareAPI::class.java)
    }

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide
        .with(context).setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
        )
}