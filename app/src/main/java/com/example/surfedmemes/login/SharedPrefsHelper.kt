package com.example.surfedmemes.login

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.surfedmemes.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPrefsHelper(context: Context) {
    companion object {
        private const val PREFS_NAME = "shared_prefs_name"
        private const val TOKEN = "token"
        private const val USER_INFO = "user_info"
    }

    private val gson = Gson()
    private var sharedPrefs: SharedPreferences = context.getSharedPreferences(
        PREFS_NAME, Context.MODE_PRIVATE
    )

    var accessToken: String?
        get() = sharedPrefs.getString(TOKEN, "") ?: ""
        set(value) = sharedPrefs.edit { putString(TOKEN, value) }

    var userInfo: User?
        get() {
            val jsonString = sharedPrefs.getString(USER_INFO, "")
            return gson.fromJson(jsonString, object : TypeToken<User>(){}.type)
        }
        set(value) = sharedPrefs.edit { putString(USER_INFO, gson.toJson(value)) }
}