package com.example.project.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.project.api.LoginService
import com.example.project.api.firebaseToken
import com.example.project.sharedpreferences.SharedPreferencesUtil
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FireBaseViewModel @Inject constructor(
    private val service: LoginService,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) : ViewModel() {

    private val TAG = "FirebaseTest"

    // 토큰을 서버로 전송하는 함수
    fun sendRegistrationToServer(token: String) {
        val userIdx = sharedPreferencesUtil.getUserId()
        val call = service.sendToken(userIdx, firebaseToken(token))
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "Token sent successfully!")
                } else {
                    Log.e(TAG, "Failed to send token: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e(TAG, "Failed to send token", t)
            }
        })
    }

    // Firebase Token 가져오기
    fun getFirebaseToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            val firebaseToken = task.result ?: return@OnCompleteListener
            sendRegistrationToServer(firebaseToken)
        })
    }

}