package com.example.project.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.api.LoginService
import com.example.project.api.PatternVerificationRequest
import com.example.project.sharedpreferences.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BiometricViewModel @Inject constructor(
    private val loginService: LoginService,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) : ViewModel() {

    private val _authenticationState = MutableLiveData<AuthenticationState>()
    val authenticationState: LiveData<AuthenticationState> get() = _authenticationState

    fun verifyPattern(pattern: String) {
        viewModelScope.launch {
            val userId = sharedPreferencesUtil.getUserId().toLong()
            if (userId == null) {
                _authenticationState.value = AuthenticationState.ERROR("정상적인 경로로 회원가입을 진행후 로그인 해주세요.")
                return@launch
            }
            val request = PatternVerificationRequest(userId, pattern)
            try {
                val response = loginService.verifyPattern(request)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody?.success == true) {
                        _authenticationState.value = AuthenticationState.SUCCESS
                    } else {
                        _authenticationState.value = AuthenticationState.ERROR(responseBody?.message ?: "Unknown error")
                    }
                } else {
                    _authenticationState.value = AuthenticationState.ERROR("Network Error: ${response.code()}")
                }
            } catch (e: Exception) {
                _authenticationState.value = AuthenticationState.ERROR("Exception: ${e.localizedMessage}")
            }
        }
    }


    // 인증 상태
    fun setAuthenticationState(state: AuthenticationState) {
        _authenticationState.value = state
    }
}

sealed class AuthenticationState {
    object SUCCESS : AuthenticationState()
    object FAILURE : AuthenticationState()
    data class ERROR(val message: String) : AuthenticationState()
}
