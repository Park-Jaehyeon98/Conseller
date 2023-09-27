package com.example.project.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.PatternState
import com.example.project.api.LoginService
import com.example.project.api.PatternSaveRequest
import com.example.project.api.PatternVerificationRequest
import com.example.project.di.CustomException
import com.example.project.sharedpreferences.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BiometricViewModel @Inject constructor(
    private val loginService: LoginService,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) : ViewModel() {

    private val _authenticationState = MutableStateFlow<AuthenticationState?>(null)
    val authenticationState: StateFlow<AuthenticationState?> get() = _authenticationState

    // 패턴 데이터 셋
    private val _patternState = MutableStateFlow<PatternState>(PatternState.Initial)
    val patternState: StateFlow<PatternState> get() = _patternState

    // 패턴 넣기
    fun setPatternState(state: PatternState) {
        _patternState.value = state
    }

    fun verifyPattern(pattern: String) {
        viewModelScope.launch {
            val userId = sharedPreferencesUtil.getUserId()
            if (userId == null) {
                _authenticationState.value = AuthenticationState.ERROR("정상적인 경로로 회원가입을 진행후 로그인 해주세요.")
                return@launch
            }
            val request = PatternVerificationRequest(userId, pattern)

            try {
                val response = loginService.verifyPattern(request)
                if (response.isSuccessful) {
                    _authenticationState.value = AuthenticationState.SUCCESS
                }
            } catch (e: CustomException) {
                _authenticationState.value = AuthenticationState.ERROR("Exception:${e.message}")
            } catch (e: Exception) {
                _authenticationState.value = AuthenticationState.ERROR("Exception: ${e.localizedMessage}")
            }
        }
    }

    fun savePattern(pattern: String) {
        viewModelScope.launch {
            val userId = sharedPreferencesUtil.getUserId()
            if (userId == null) {
                _authenticationState.value = AuthenticationState.ERROR("정상적인 경로로 회원가입을 진행후 패턴 저장을 시도해주세요.")
                return@launch
            }
            val request = PatternSaveRequest(userId, pattern)
            try {
                val response = loginService.savePattern(request)
                if (response.isSuccessful) {
                    _authenticationState.value = AuthenticationState.SUCCESS
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

