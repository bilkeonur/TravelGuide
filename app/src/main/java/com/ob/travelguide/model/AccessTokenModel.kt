package com.ob.travelguide.model

import com.google.gson.annotations.SerializedName

data class AccessTokenModel(
    @SerializedName("access_token")
    val accessToken:String
)