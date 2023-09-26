package com.example.project.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.api.BasicResponse
import com.example.project.api.CheckuserIdRequest
import com.example.project.api.HttpResponse
import com.example.project.api.RegistRequest
import com.example.project.api.SignupService
import com.example.project.api.findIdRequest
import com.example.project.api.findIdResponse
import com.example.project.api.findPwRequest
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

    private val _SignupResponse = MutableStateFlow(HttpResponse(0,""))
    val signupresponse: StateFlow<HttpResponse> get() = _SignupResponse


    private val _CheckEmailResponse = MutableStateFlow(BasicResponse(-1, ""))
    val checkEmail: StateFlow<BasicResponse> get() = _CheckEmailResponse


    private val _CheckNickNameResponse = MutableStateFlow(BasicResponse(-1, ""))
    val checkNickname: StateFlow<BasicResponse> get() = _CheckNickNameResponse


    private val _CheckPhoneNumber = MutableStateFlow(BasicResponse(-1, ""))
    val checkPhoneNumber: StateFlow<BasicResponse> get() = _CheckPhoneNumber


    private val _CheckId = MutableStateFlow(BasicResponse(-1, ""))
    val checkId: StateFlow<BasicResponse> get() = _CheckId


    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _FindId = MutableStateFlow(findIdResponse( ""))
    val findId: StateFlow<findIdResponse> get() = _FindId

    private val _FindPw = MutableStateFlow(false)
    val findPw: StateFlow<Boolean> get() = _FindPw




    fun findUserId(request: findIdRequest) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.findMyId(request)
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            _FindId.value = body
                            Log.d("FindUserId", "Success: ${response.code()}, Body: $body")
                        } else {
                            _error.value = "Response body is null"
                            Log.d("FindUserId", "Error: Response body is null, Code: ${response.code()}")
                        }
                    } else {
                        _error.value = response.message()
                        Log.d("FindUserId", "Error: ${response.message()}, Code: ${response.code()}")
                    }
                }
            } catch (e: Exception) {
                _error.value = e.message
                Log.d("FindUserId", "Exception: ${e.message}")
            }
        }
    }

    fun findUserPw(request: findPwRequest) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.findMyPwd(request)
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _FindPw.value = true
                        Log.d("FindUserPw", "Success: ${response.code()}")
                    } else {
                        _error.value = response.message()
                        Log.d("FindUserPw", "Error: ${response.message()}, Code: ${response.code()}")
                    }
                }
            } catch (e: Exception) {
                _error.value = e.message
                Log.d("FindUserPw", "Exception: ${e.message}")
            }
        }
    }



    // 회원가입 로직
    fun registerUser(request: RegistRequest) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = service.regist(request)
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _SignupResponse.value = HttpResponse(code = response.code(), message = response.message())
                        Log.d("RegisterUser", "Registration Successful: ${_SignupResponse.value}")
                    } else {
                        _error.value = response.message()
                        _SignupResponse.value = HttpResponse(code = response.code(), message = response.message())
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
                        _CheckNickNameResponse.value = response.body() ?: BasicResponse(-1, "")
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
                        _CheckEmailResponse.value = response.body() ?: BasicResponse(-1, "")
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
                    Log.d("CheckDuplicateId", "HTTP Status Code: ${response.code()}")

                    if (response.isSuccessful) {
                        _CheckId.value = response.body() ?: BasicResponse(-1, "")
                        Log.d("CheckDuplicateId", "Id check 성공함: ${response.body()}")
                    } else {
                        _error.value = response.message()
                        Log.e("CheckDuplicateId", "Id check 문제났음: ${response.message()}, HTTP Status Code: ${response.code()}, Response Body: ${response.errorBody()?.string()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _error.value = e.message
                    Log.e("CheckDuplicateId", "에러남:", e)
                }
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
                        _CheckPhoneNumber.value = response.body() ?: BasicResponse(-1, "")
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

