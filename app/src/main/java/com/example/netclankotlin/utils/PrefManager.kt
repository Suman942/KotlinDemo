package com.example.netclankotlin.utils

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.preference.PreferenceManager
import android.provider.Settings.Global.putString
import android.util.Pair
import android.util.SparseArray
import androidx.annotation.StringRes
import androidx.core.content.edit
import com.example.netclankotlin.R
import java.lang.reflect.Array.set

class PrefManager private constructor(context: Context) {

    // ------- Preference Variables


    var sessionId: String?
        get() = pref.getString("sessionId", null)
        set(value) = pref.edit { putString("sessionId", value) }

    var authKey: String?
        get() = pref.getString("authToken", null)
        set(value) = pref.edit { putString("authToken", value) }

    // ---------------------------------------------------------------------------------------------

    private val pref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    fun clear() = pref.edit { clear() }



    companion object : SingletonHolder<PrefManager, Context>(::PrefManager) {
        private const val FILE_NAME = "AUTHENTICATION_FILE_NAME"
    }
}