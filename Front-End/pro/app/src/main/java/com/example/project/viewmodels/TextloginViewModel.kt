package com.example.project.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.MyFirebaseMessagingService
import com.example.project.api.LoginService
import com.example.project.api.IdPwLoginRequest
import com.example.project.api.IdPwLoginResponse
import com.example.project.api.accessToken
import com.example.project.sharedpreferences.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TextloginViewModel @Inject constructor(
    private val loginService: LoginService,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) : ViewModel() {

    val firebaseService = MyFirebaseMessagingService()

    private val _idPwLoginState = MutableLiveData<ResponseState<IdPwLoginResponse>>()
    val idPwLoginState: LiveData<ResponseState<IdPwLoginResponse>> = _idPwLoginState


    private val _getAccessToken = MutableStateFlow(accessToken(""))
    val getaccessToken: StateFlow<accessToken> get() = _getAccessToken

    private val _checkError = MutableStateFlow(false)
    val checkError: StateFlow<Boolean> get() = _checkError
    fun getUserIdFromPreference(): Long {
        return sharedPreferencesUtil.getUserId()
    }
    fun reSetPreference(){
        return sharedPreferencesUtil.resetPreferences()
    }
    suspend fun getAccessToken(): Boolean {
        val userIdx = getUserIdFromPreference()
        return try {
            val response = loginService.accessToken(userIdx)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.accessToken?.let { token ->
                        sharedPreferencesUtil.setUserToken(token)
                        return@withContext true
                    } ?: run {
                        // accessToken이 null인 경우의 처리 로직
                        return@withContext false
                    }
                } else {
                    // 응답이 성공적이지 않은 경우의 처리 로직
                    _checkError.value=true
                    return@withContext false
                }
            }
        } catch (e: Exception) {
            // 예외 처리 로직
            false
        }
    }

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
                    val token=getAccessToken()
                    if(token){
                        loginWithIdPw(request)
                    }else{
                        _idPwLoginState.value = ResponseState.Error("ID/PW login failed")
                    }
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