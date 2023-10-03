package com.example.project.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.MyFirebaseMessagingService
import com.example.project.PatternState
import com.example.project.api.ErrorLoginResponse
import com.example.project.api.IdPwLoginResponse
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

    private val _authenticationState = MutableStateFlow<AuthenticationState?>(AuthenticationState.WAIT)
    val authenticationState: StateFlow<AuthenticationState?> get() = _authenticationState

    // 패턴 데이터 셋
    private val _patternState = MutableStateFlow<PatternState>(PatternState.Initial)
    val patternState: StateFlow<PatternState> get() = _patternState

    // 패턴 넣기
    fun setPatternState(state: PatternState) {
        _patternState.value = state
    }

    // 패턴 확인
    fun verifyPattern(pattern: String) {
        _authenticationState.value = null
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
                    val successResponse = response.body() as IdPwLoginResponse
                    _authenticationState.value = AuthenticationState.SUCCESS
                    sharedPreferencesUtil.setUserToken(successResponse.accessToken)
                    MyFirebaseMessagingService().getFirebaseToken()
                } else{
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = errorBody?.substringAfter("\"message\":\"")?.substringBefore("\"}")
                    _authenticationState.value = AuthenticationState.ERROR(errorMessage!!)
                }
            } catch (e: Exception) {
                _authenticationState.value = AuthenticationState.ERROR(e.localizedMessage)
            }
        }
    }

    // 패턴 생성 후 저장
    fun savePattern(pattern: String) {
        _authenticationState.value = null
        viewModelScope.launch {
            val userIdx = sharedPreferencesUtil.getUserId()
            if (userIdx == null) {
                _authenticationState.value = AuthenticationState.ERROR("정상적인 경로로 회원가입을 진행후 패턴 저장을 시도해주세요.")
                return@launch
            }
            val request = PatternSaveRequest(userIdx, pattern)
            try {
                val response = loginService.savePattern(request)
                if (response.isSuccessful) {
                    sharedPreferencesUtil.setLoggedInStatus(true)
                    _authenticationState.value = AuthenticationState.SUCCESS
                    }
            } catch (e: CustomException) {
                _authenticationState.value = AuthenticationState.ERROR("Exception: ${e.message}")
            } catch (e: Exception) {
                _authenticationState.value = AuthenticationState.ERROR("Exception: ${e.localizedMessage}")
            }
        }
    }

    // 지문로그인시 토큰 받기
    fun authenticateWithBiometrics() {
        _authenticationState.value = null
        viewModelScope.launch {
            val userIdx = sharedPreferencesUtil.getUserId()
            if (userIdx == null) {
                _authenticationState.value = AuthenticationState.ERROR("정상적인 경로로 회원가입을 진행 후 패턴 저장을 시도해주세요.")
                return@launch
            }
            try {
                val response = loginService.fingerLogin(userIdx)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    responseBody?.let {
                        sharedPreferencesUtil.setUserToken(it.accessToken)
                        sharedPreferencesUtil.setLoggedInStatus(true)
                        _authenticationState.value = AuthenticationState.SUCCESS
                        MyFirebaseMessagingService().getFirebaseToken()
                        setAuthenticationState(AuthenticationState.SUCCESS)
                    } ?: run {
                        _authenticationState.value = AuthenticationState.ERROR("응답 본문이 존재하지 않습니다.")
                    }
                } else {
                    _authenticationState.value = AuthenticationState.ERROR("API 호출 실패: ${response.errorBody()?.string()}")
                }
            } catch (e: CustomException) {
                _authenticationState.value = AuthenticationState.ERROR("Exception: ${e.message}")
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
    object WAIT : AuthenticationState()
    data class ERROR(val message: String) : AuthenticationState()
}

