package com.example.project.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        viewModelScope.launch(Dispatchers.IO) {// 일반적인 API 통신인 경우 IO를 사용해서 IO로 함 , 블로킹 처리 대비
            try {
                val response = service.regist(request)
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _SignupResponse.value = response.body() ?: RegistResponse(-1, "")
                    } else {
                        _error.value = response.message()
                    }
                }
            } catch (e: Exception) {
                _error.value = e.message
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
                val response = service.checkDuplicateuEmail(email)
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _CheckEmailResponse.value = response.body() ?: RegistResponse(-1, "")
                    } else {
                        _error.value = response.message()
                    }
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun checkDuplicateId(userId: String) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.checkDuplicateId(userId)
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _CheckId.value = response.body() ?: RegistResponse(-1, "")
                    } else {
                        _error.value = response.message()
                    }
                }
            } catch (e: Exception) {
                _error.value = e.message
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

