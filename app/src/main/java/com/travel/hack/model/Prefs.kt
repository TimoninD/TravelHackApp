package com.travel.hack.model

import android.content.Context

class Prefs constructor(
    private val context: Context
) {

    private fun getSharedPreferences(prefsName: String) =
        context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

    companion object {
        private const val APP_DATA = "app_data"
        private const val KEY_NAME = "name"
        private const val CITY_ID_NAME = "city_id_name"
        private const val CITY_NAME_NAME = "city_name_name"
        private const val LAT_NAME = "lat_name"
        private const val LNG_NAME = "lng_name"
    }


    private val appPrefs by lazy { getSharedPreferences(APP_DATA) }

    var name: String?
        get() = appPrefs.getString(KEY_NAME, "")
        set(value) {
            appPrefs.edit().putString(KEY_NAME, value).apply()
        }

    var cityId: Int
        get() = appPrefs.getInt(CITY_ID_NAME, 0)
        set(value) {
            appPrefs.edit().putInt(CITY_ID_NAME, value).apply()
        }

    var cityName: String?
        get() = appPrefs.getString(CITY_NAME_NAME, "")
        set(value) {
            appPrefs.edit().putString(CITY_NAME_NAME, value).apply()
        }

    var lat: Float
        get() = appPrefs.getFloat(LAT_NAME, 0f)
        set(value) {
            appPrefs.edit().putFloat(LAT_NAME, value).apply()
        }

    var lng: Float
        get() = appPrefs.getFloat(LNG_NAME, 0f)
        set(value) {
            appPrefs.edit().putFloat(LNG_NAME, value).apply()
        }
}