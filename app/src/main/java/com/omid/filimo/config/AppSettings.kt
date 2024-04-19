package com.omid.filimo.config

import android.content.Context
import android.content.SharedPreferences
import com.omid.filimo.utils.configuration.AppConfiguration

class AppSettings {

    private var sharedPreferences: SharedPreferences = AppConfiguration.getContext().getSharedPreferences("setting", Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun saveLogin(state: Int) {
        editor.putInt("isLogin", state)
        editor.commit()
    }

    fun getLogin(): Int {
        return sharedPreferences.getInt("isLogin", 0)
    }
}