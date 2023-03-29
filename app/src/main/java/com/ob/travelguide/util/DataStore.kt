package com.ob.travelguide.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class DataStore {
    companion object {
        private var sharedPreferences: SharedPreferences? = null
        @Volatile private var instance: DataStore? = null
        private val lock = Any()

        operator fun invoke(context: Context): DataStore = instance ?: synchronized(lock) {
            instance ?: makeDataStore(context).also {
                instance = it
            }
        }

        private fun makeDataStore(context: Context): DataStore {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return DataStore()
        }
    }

    fun setLogin(accessToken:String) {
        val editor = sharedPreferences?.edit()
        editor?.putString("access_token",accessToken)
        editor?.apply()
    }

    fun getLogin(): String? {
        return sharedPreferences?.getString("access_token", "")
    }

    fun resetLogin() {
        val editor = sharedPreferences?.edit()
        editor?.clear()?.apply()
    }
}