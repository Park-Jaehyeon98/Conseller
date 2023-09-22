package com.example.project.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.api.CheckuserIdRequest
import com.example.project.api.RegistRequest
import com.example.project.api.RegistResponse
import com.example.project.api.SignupService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val service: SignupService,
) : ViewModel() {

    private val _SignupResponse = MutableStateFlow(RegistResponse(-1,""))
    val signupresponse: StateFlow<RegistResponse> get() = _SignupResponse


    private val _CheckEmailResponse = MutableStateFlow(RegistResponse(-1, ""))
    val checkEmail: StateFlow<RegistResponse> get() = _CheckEmailResponse


    private val _CheckNickNameResponse = MutableStateFlow(RegistResponse(-1, ""))
    val checkNickname: StateFlow<RegistResponse> get() = _CheckNickNameResponse


    private val _CheckPhoneNumber = MutableStateFlow(RegistResponse(-1, ""))
    val checkPhoneNumber: StateFlow<RegistResponse> get() = _CheckPhoneNumber


    private val _CheckId = MutableStateFlow(RegistResponse(-1, ""))
    val checkId: StateFlow<RegistResponse> get() = _CheckId



    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error


    // 로그인 로직
    fun registerUser(request: RegistRequest) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.regist(request)
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _SignupResponse.value = response.body() ?: RegistResponse(-1, "")
                        Log.d("RegisterUser", "Registration Successful: ${_SignupResponse.value}")
                    } else {
                        _error.value = response.message()
                        // HTTP 응답 코드 및 에러 메시지 로깅
                        Log.d("RegisterUser", "Registration Failed: Response Code = ${response.code()}, Error Message = ${response.errorBody()?.string() ?: "Unknown Error"}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _error.value = e.message
                }
                Log.d("RegisterUser", "Exception Occurred: ${e.message}")
            }
        }
    }

    fun checkDuplicateNickname(nickname: String) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.checkDuplicateNickName(nickname)
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _CheckNickNameResponse.value = response.body() ?: RegistResponse(-1, "")
                    } else {
                        _error.value = response.message()
                    }
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun checkDuplicateEmail(email: String) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.checkDuplicateEmail(email)
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _CheckEmailResponse.value = response.body() ?: RegistResponse(-1, "")
                        Log.d("CheckDuplicateEmail", "Email check successful: ${response.body()}")
                    } else {
                        _error.value = response.message()
                        Log.e("CheckDuplicateEmail", "Email check failed: ${response.message()}")
                    }
                }
            } catch (e: Exception) {
                _error.value = e.message
                Log.e("CheckDuplicateEmail", "Error during email check", e)
            }
        }
    }

    fun checkDuplicateId(request: CheckuserIdRequest) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.checkDuplicateId(request)
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _CheckId.value = response.body() ?: RegistResponse(-1, "")
                        Log.d("CheckDuplicateEmail", "Email check 성공함: ${response.body()}")
                    } else {
                        _error.value = response.message()
                        Log.d("CheckDuplicateEmail", "Email check 문제났음: ${response.message()}")
                    }
                }
            } catch (e: Exception) {
                _error.value = e.message
                Log.d("CheckDuplicateEmail", "에러남:",e)
            }
        }
    }

    fun checkDuplicatePhoneNumber(phoneNumber: String) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.checkDuplicatePhoneNumber(phoneNumber)
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _CheckPhoneNumber.value = response.body() ?: RegistResponse(-1, "")
                    } else {
                        _error.value = response.message()
                    }
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }



}

