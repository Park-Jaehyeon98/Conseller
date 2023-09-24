package com.example.project.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.api.GifticonRequestDTO
import com.example.project.api.MyService
import com.example.project.api.OcrService
import com.example.project.api.UploadGifticonResponse
import com.example.project.api.uploadImageResponse
import com.example.project.sharedpreferences.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

@HiltViewModel
class OcrViewModel @Inject constructor(
    private val OcrService: OcrService, private val sharedPreferencesUtil: SharedPreferencesUtil
) : ViewModel() {

    //기프티콘 등록(OCR)
    private val _uploadGifticonResponse = MutableStateFlow<UploadGifticonResponse>(
        UploadGifticonResponse(
            "",
            "",
            "",
            ""
        )
    )
    val uploadGifticonResponse: StateFlow<UploadGifticonResponse> get() = _uploadGifticonResponse

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var currentPage = 1

    fun getUserNickName(): String? {
        return sharedPreferencesUtil.getUserNickname()
    }
    //기프티콘 업로드(OCR)
    fun uploadGifticon(category:Int, image: MultipartBody.Part) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = OcrService.uploadOcrGifticon(category, image)
                if (response.isSuccessful) {
                    _uploadGifticonResponse.value = response.body() ?: UploadGifticonResponse(
                        "",
                        "",
                        "",
                        ""
                    )
                    Log.d("OcrViewModel", "Upload successful: ${_uploadGifticonResponse.value}")
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "서버 error"
                    _error.value = errorMessage
                    Log.e("OcrViewModel", "Error uploading gifticon: $errorMessage")
                }
            } catch (e: Exception) {
                _error.value = e.message
                Log.e("OcrViewModel", "Exception while uploading gifticon", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}


