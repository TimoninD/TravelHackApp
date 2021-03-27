package com.travel.hack.model

import android.content.Context

class Prefs constructor(
    private val context: Context
) {

    private fun getSharedPreferences(prefsName: String) =
        context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

    companion object {
        private const val AUTH_DATA = "auth_data"
        private const val KEY_NAME = "name"
    }


    private val authPrefs by lazy { getSharedPreferences(AUTH_DATA) }

    var name: String?
        get() = authPrefs.getString(KEY_NAME, "")
        set(value) {
            authPrefs.edit().putString(KEY_NAME, value).apply()
        }
}