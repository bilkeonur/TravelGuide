package com.ob.travelguide.service

import com.ob.travelguide.model.AccessToken
import com.ob.travelguide.model.Place.Place
import retrofit2.Response
import retrofit2.http.*

interface FourSquareAPI {
    @Headers("Accept: application/json")
    @POST("oauth2/access_token")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String,
        @Field("redirect_uri") redirectUri: String,
        @Field("code") code:String): Response<AccessToken>

    @Headers("Accept: application/json", "Authorization: fsq3H1I8FI7X90yAaNOC+7YJ6Y6DPwEctjLNvT3NJVF1UG4=")
    @GET
    suspend fun searchPlace(@Url url : String): Response<Place>
}