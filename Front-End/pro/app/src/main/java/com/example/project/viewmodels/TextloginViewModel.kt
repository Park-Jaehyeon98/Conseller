package com.example.project.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.api.LoginService
import com.example.project.api.IdPwLoginRequest
import com.example.project.api.IdPwLoginResponse
import com.example.project.sharedpreferences.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TextloginViewModel @Inject constructor(
    private val loginService: LoginService,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) : ViewModel() {

    private val _idPwLoginState = MutableLiveData<ResponseState<IdPwLoginResponse>>()
    val idPwLoginState: LiveData<ResponseState<IdPwLoginResponse>> = _idPwLoginState

    fun loginWithIdPw(request: IdPwLoginRequest) {
        viewModelScope.launch {
            try {
                Log.d("TextloginViewModel", "Starting ID/PW login")
                val response = loginService.textLogin(request)

                // Log the response data
                Log.d("TextloginViewModel", "Response Code: ${response.code()}")
                Log.d("TextloginViewModel", "Response Message: ${response.message()}")
                Log.d("TextloginViewModel", "Response Headers: ${response.headers()}")
                Log.d("TextloginViewModel", "Response Body: ${response.body()}")

                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d("TextloginViewModel", "Login successful, saving user data")

                        // SharedPreferences에 로그인 정보 저장
                        sharedPreferencesUtil.setLoggedInStatus(true)
                        sharedPreferencesUtil.setUserId(it.userIdx)
                        sharedPreferencesUtil.setUserNickname(it.userNickname)
                        sharedPreferencesUtil.setUserToken(it.accessToken)

                        _idPwLoginState.value = ResponseState.Success(it)
                    } ?: run {
                        Log.d("TextloginViewModel", "Invalid response body")
                        _idPwLoginState.value = ResponseState.Error("Invalid response body")
                    }
                } else {
                    Log.d("TextloginViewModel", "ID/PW login failed with response code: ${response.code()}")
                    _idPwLoginState.value = ResponseState.Error("ID/PW login failed")
                }
            } catch (e: Exception) {
                Log.e("TextloginViewModel", "Exception occurred during ID/PW login", e)
                _idPwLoginState.value = ResponseState.Error(e.message ?: "Unknown error")
            }
        }
    }
}







sealed class ResponseState<out T> {
    data class Success<out T>(val data: T) : ResponseState<T>()
    data class Error(val message: String) : ResponseState<Nothing>()
}