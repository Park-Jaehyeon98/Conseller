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
import com.example.project.di.CustomException
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

    private val _loginState = MutableStateFlow(0)
    val loginState: StateFlow<Int> = _loginState
    private val _getAccessToken = MutableStateFlow(accessToken(""))
    val getaccessToken: StateFlow<accessToken> get() = _getAccessToken

    private val _checkError = MutableStateFlow(false)
    val checkError: StateFlow<Boolean> get() = _checkError

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun getUserIdFromPreference(): Long {
        return sharedPreferencesUtil.getUserId()
    }
    fun initLoginState(){
        _loginState.value=0
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
    _isLoading.value = true
    viewModelScope.launch {
        _isLoading.value = true
        _error.value = null

        try {
            val response = loginService.textLogin(request)
            if (response.isSuccessful) {
                _loginState.value=1
                response.body()?.let {
                    Log.d("TextloginViewModel", "Login successful, saving user data")
                    // SharedPreferences에 로그인 정보 저장
                    sharedPreferencesUtil.setLoggedInStatus(true)
                    sharedPreferencesUtil.setUserId(it.userIdx)
                    sharedPreferencesUtil.setUserNickname(it.userNickname)
                    sharedPreferencesUtil.setUserToken(it.accessToken)

                    _idPwLoginState.value = ResponseState.Success(it)}
            }else{
                _loginState.value=2
            }
        } catch (e: CustomException) {
            _loginState.value=2
            _error.value = e.message
        } catch (e: Exception) {
            _loginState.value=2
            _error.value = e.localizedMessage
        } finally {
            _isLoading.value = false
        }
    }
}
}







sealed class ResponseState<out T> {
    data class Success<out T>(val data: T) : ResponseState<T>()
    data class Error(val message: String) : ResponseState<Nothing>()
}