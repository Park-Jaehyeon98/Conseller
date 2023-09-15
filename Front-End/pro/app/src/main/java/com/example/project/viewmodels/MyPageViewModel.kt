package com.example.project.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.api.MyPageService
import com.example.project.api.RegistResponse
import com.example.project.api.RegisterAuctionDTO
import com.example.project.api.uploadImageResponse
import com.example.project.api.userInfoResponse
import com.example.project.sharedpreferences.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val service: MyPageService,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    //프로필 사진 업로드
    private val _UploadProfileResponse = MutableStateFlow(uploadImageResponse(-1, ""))
    val uploadProfileResponse: StateFlow<uploadImageResponse> get() = _UploadProfileResponse

    //기프티콘 사진 업로드
    private val _UploadGifticonResponse = MutableStateFlow(uploadImageResponse(-1, ""))
    val uploadGifticonResponse: StateFlow<uploadImageResponse> get() = _UploadGifticonResponse

    // 내 정보 조회
    private val _GetMyInfoResponse = MutableStateFlow(userInfoResponse("", "", "", "", "","",""))
    val getMyinfoResponse: StateFlow<userInfoResponse> get() = _GetMyInfoResponse

//    fun getUserIdFromPreference(): Long {
//        return sharedPreferencesUtil.getUserId()
//    }

    fun profileSend(imageFile: MultipartBody.Part) {
        val userIdx = sharedPreferencesUtil.getUserId()
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = service.profileSend(userIdx, imageFile)
                if (response.isSuccessful) {
                    _UploadProfileResponse.value = response.body() ?: uploadImageResponse(-1, "")
                } else {
                    _error.value = response.errorBody()?.string() ?: "서버 error"
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun uploadGifticon(userIdx: String, imageFile: MultipartBody.Part) {
        val userIdx = sharedPreferencesUtil.getUserId()
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = service.uploadGifticon(userIdx, imageFile)
                if (response.isSuccessful) {
                    _UploadGifticonResponse.value = response.body() ?: uploadImageResponse(-1, "")
                } else {
                    _error.value = response.errorBody()?.string() ?: "서버 error"
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getMyInfo() {
        val userIdx = sharedPreferencesUtil.getUserId()
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = service.getUserInfo(userIdx)
                if (response.isSuccessful) {
                    _GetMyInfoResponse.value =
                        response.body() ?: userInfoResponse("", "", "", "", "","","")
                } else {
                    _error.value = response.errorBody()?.string() ?: "서버 error"
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
