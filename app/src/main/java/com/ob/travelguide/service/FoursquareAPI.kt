package com.ob.travelguide.service

import com.ob.travelguide.model.AccessTokenModel
import com.ob.travelguide.model.Place.PlaceModel
import retrofit2.http.*

interface FoursquareAPI {
    @Headers("Accept: application/json")
    @POST("access_token")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String,
        @Field("redirect_uri") redirectUri: String,
        @Field("code") code:String): AccessTokenModel

    @Headers("Accept: application/json", "Authorization: fsq3H1I8FI7X90yAaNOC+7YJ6Y6DPwEctjLNvT3NJVF1UG4=")
    @GET("places/search?ll=39.0185,35.6098&categories=10000&sort=POPULARITY&limit=50")
    suspend fun searchPlace(): PlaceModel
}