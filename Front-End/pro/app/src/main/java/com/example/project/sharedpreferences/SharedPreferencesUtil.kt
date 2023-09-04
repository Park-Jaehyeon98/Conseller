package com.example.project.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesUtil @Inject constructor(private val context: Context) {

    companion object {
        private const val PREF_NAME = "my_pref"
        private const val IS_LOGGED_IN = "is_logged_in"
        private const val USER_ID = "user_id"
    }

    private val sharedPreferences: SharedPreferences
        get() = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    // 로그인 상태를 저장하는 메서드
    fun setLoggedInStatus(isLoggedIn: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(IS_LOGGED_IN, isLoggedIn)
            apply()
        }
    }

    // 현재 로그인 상태를 확인하는 메서드
    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false)
    }

    // 사용자 아이디를 저장하는 메서드
    fun setUserId(userId: String) {
        with(sharedPreferences.edit()) {
            putString(USER_ID, userId)
            apply()
        }
    }

    // 저장된 사용자 아이디를 가져오는 메서드
    fun getUserId(): String? {
        return sharedPreferences.getString(USER_ID, null)
    }
}
