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
//        private const val ID = "id"
//        private const val USERNAME = "username"
//        private const val FIRST_NAME = "first_name"
//        private const val LAST_NAME = "last_name"
//        private const val USER_DESCRIPTION = "user_description"
    }

    private val gson = Gson()
    private var sharedPrefs: SharedPreferences = context.getSharedPreferences(
        PREFS_NAME, Context.MODE_PRIVATE
    )

    var accessToken: String
        get() = sharedPrefs.getString(TOKEN, "") ?: ""
        set(value) = sharedPrefs.edit { putString(TOKEN, value) }

    var userInfo: User
        get() {
            val jsonString = sharedPrefs.getString(USER_INFO, "")
            return gson.fromJson(jsonString, object : TypeToken<User>(){}.type)
        }
        set(value) = sharedPrefs.edit { putString(USER_INFO, gson.toJson(value)) }

//    var id: Int
//        get() = sharedPrefs.getInt(ID, 0)
//        set(value) = sharedPrefs.edit { putInt(ID, value) }
//
//    var userName: String
//        get() = sharedPrefs.getString(USERNAME, "") ?: ""
//        set(value) = sharedPrefs.edit { putString(USERNAME, value) }
//
//    var firstName: String
//        get() = sharedPrefs.getString(FIRST_NAME, "") ?: ""
//        set(value) = sharedPrefs.edit { putString(FIRST_NAME, value) }
//
//    var secondName: String
//        get() = sharedPrefs.getString(LAST_NAME, "") ?: ""
//        set(value) = sharedPrefs.edit { putString(LAST_NAME, value) }
//
//    var userDescription: String
//        get() = sharedPrefs.getString(USER_DESCRIPTION, "") ?: ""
//        set(value) = sharedPrefs.edit { putString(USER_DESCRIPTION, value) }
}