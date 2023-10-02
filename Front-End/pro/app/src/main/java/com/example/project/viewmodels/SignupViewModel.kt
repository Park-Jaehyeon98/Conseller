package com.example.project.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.api.BasicResponse
import com.example.project.api.RegistRequest
import com.example.project.api.SignupService
import com.example.project.api.findIdRequest
import com.example.project.api.findIdResponse
import com.example.project.api.findPwRequest
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
class SignupViewModel @Inject constructor(
    private val service: SignupService,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) : ViewModel() {

    private val _SignupResponse = MutableStateFlow(0)
    val signupresponse: StateFlow<Int> get() = _SignupResponse


    private val _CheckEmailResponse = MutableStateFlow(BasicResponse(-1, ""))
    val checkEmail: StateFlow<BasicResponse> get() = _CheckEmailResponse


    private val _CheckNickNameResponse = MutableStateFlow(BasicResponse(-1, ""))
    val checkNickname: StateFlow<BasicResponse> get() = _CheckNickNameResponse


    private val _CheckPhoneNumberResponse = MutableStateFlow(BasicResponse(-1, ""))
    val checkPhoneNumber: StateFlow<BasicResponse> get() = _CheckPhoneNumberResponse


    private val _CheckIdResponse = MutableStateFlow(BasicResponse(-1, ""))
    val checkId: StateFlow<BasicResponse> get() = _CheckIdResponse


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
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = service.findMyId(request)
                if (response.isSuccessful&&response.body()!=null) {
                    _FindId.value=response.body()!!
                }
            } catch (e: CustomException) {
                _error.value = e.message
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun findUserPw(request: findPwRequest) {
        _isLoading.value = true
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = service.findMyPwd(request)
                if (response.isSuccessful&&response.body()!=null) {
                    _FindPw.value=true
                }
            } catch (e: CustomException) {
                _error.value = e.message
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _isLoading.value = false
            }
        }
    }


    fun initSignUpResult(){
        _SignupResponse.value=0
    }
    // 회원가입 로직
    fun registerUser(request: RegistRequest) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.regist(request)

                if (response.isSuccessful) {
                    _SignupResponse.value=1
                    sharedPreferencesUtil.setUserId(response.body()!!.userIdx)
                }else{
                    _SignupResponse.value=2
                }
            } catch (e: CustomException) {
                _error.value = e.message
                _SignupResponse.value=2
            } catch (e: Exception) {
                _error.value = e.localizedMessage
                _SignupResponse.value=2
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun checkDuplicateNickname(nickname: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.checkDuplicateNickName(nickname)

                if (response.isSuccessful && response.body() != null) {
                    _CheckNickNameResponse.value=response.body()!!
                }
            } catch (e: CustomException) {
                _error.value = e.message
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun checkDuplicateEmail(email: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.checkDuplicateEmail(email)

                if (response.isSuccessful && response.body() != null) {
                    _CheckEmailResponse.value=response.body()!!
                }
            } catch (e: CustomException) {
                _error.value = e.message
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun checkDuplicateId(userId:String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.checkDuplicateId(userId)

                if (response.isSuccessful && response.body() != null) {
                    _CheckIdResponse.value=response.body()!!
                }
            } catch (e: CustomException) {
                _error.value = e.message
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _isLoading.value = false
            }
        }
    }


    fun checkDuplicatePhoneNumber(phoneNumber: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.checkDuplicatePhoneNumber(phoneNumber)

                if (response.isSuccessful && response.body() != null) {
                    _CheckPhoneNumberResponse.value=response.body()!!
                }
            } catch (e: CustomException) {
                _error.value = e.message
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _isLoading.value = false
            }
        }
    }



}

