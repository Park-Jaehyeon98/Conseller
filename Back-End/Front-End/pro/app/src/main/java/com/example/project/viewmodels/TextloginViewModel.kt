package com.example.project.viewmodels

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
                val response = loginService.textLogin(request)
                if (response.isSuccessful) {
                    response.body()?.let {
                        // SharedPreferences에 로그인 정보 저장
                        sharedPreferencesUtil.setLoggedInStatus(true)
                        sharedPreferencesUtil.setUserId(it.user_idx)
                        sharedPreferencesUtil.setUserNickname(it.user_nickname)
                        sharedPreferencesUtil.setUserToken(it.user_accesstoken)

                        _idPwLoginState.value = ResponseState.Success(it)
                    } ?: run {
                        _idPwLoginState.value = ResponseState.Error("Invalid response body")
                    }
                } else {
                    _idPwLoginState.value = ResponseState.Error("ID/PW login failed")
                }
            } catch (e: Exception) {
                _idPwLoginState.value = ResponseState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class ResponseState<out T> {
    data class Success<out T>(val data: T) : ResponseState<T>()
    data class Error(val message: String) : ResponseState<Nothing>()
}