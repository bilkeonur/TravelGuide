package com.ob.travelguide.service

import com.ob.travelguide.model.AccessTokenModel
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
}