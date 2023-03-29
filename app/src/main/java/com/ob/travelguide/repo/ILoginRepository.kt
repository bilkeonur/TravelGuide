package com.ob.travelguide.repo

import com.ob.travelguide.model.AccessToken
import com.ob.travelguide.util.Resource

interface ILoginRepository {
    suspend fun getAccessToken(code:String) : Resource<AccessToken>
}