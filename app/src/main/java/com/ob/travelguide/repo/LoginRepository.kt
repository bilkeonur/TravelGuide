package com.ob.travelguide.repo

import com.ob.travelguide.model.AccessToken
import com.ob.travelguide.service.FourSquareAPI
import com.ob.travelguide.util.Resource
import com.ob.travelguide.util.Util
import javax.inject.Inject

class LoginRepository @Inject constructor (private val retrofitApi : FourSquareAPI): ILoginRepository {

    override suspend fun getAccessToken(code: String): Resource<AccessToken> {
        return try {

            val response = retrofitApi.getAccessToken(
                Util.clientId,
                Util.clientSecret,
                "authorization_code",
                Util.redirectUri, code
            )

            if (response.isSuccessful) {
                response.body()?.let { accessToken ->
                    return@let Resource.success(accessToken)
                } ?: Resource.error("Error",null)
            } else {
                Resource.error("Error",null)
            }
        }
        catch (e: Exception) {
            Resource.error("Error",null)
        }
    }
}