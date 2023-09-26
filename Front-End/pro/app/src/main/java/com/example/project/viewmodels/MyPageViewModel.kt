package com.example.project.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.api.MyPageService
import com.example.project.api.myAuctionBidData
import com.example.project.api.myAuctionData
import com.example.project.api.myBarterData
import com.example.project.api.myBarterRequestData
import com.example.project.api.myGifticon
import com.example.project.api.myGifticonResponse
import com.example.project.api.myStoreData
import com.example.project.api.uploadImageResponse
import com.example.project.api.userInfoResponse
import com.example.project.api.userModifyRequest
import com.example.project.api.userModifyResponse
import com.example.project.api.userUploadGifticonResponse
import com.example.project.api.userValidRequest
import com.example.project.sharedpreferences.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val service: MyPageService, private val sharedPreferencesUtil: SharedPreferencesUtil
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    //프로필 사진 업로드
    private val _UploadProfileResponse = MutableStateFlow(uploadImageResponse(-1, ""))
    val uploadProfileResponse: StateFlow<uploadImageResponse> get() = _UploadProfileResponse

    //기프티콘 사진 업로드
    private val _UploadGifticonResponse = MutableStateFlow<Boolean>(false)
    val uploadGifticonResponse: StateFlow<Boolean> get() = _UploadGifticonResponse

    // 내 정보 조회
    private val _GetMyInfoResponse = MutableStateFlow(
        userInfoResponse(
            null, "", "통신에러", "SSAFY@naver.com", "국민은행", "12345678910", ""
        )
    )
    val getMyinfoResponse: StateFlow<userInfoResponse> get() = _GetMyInfoResponse

    // 내 비밀번호 확인
    private val _ValidUserReponse = MutableStateFlow(false)
    val validUserResponse: StateFlow<Boolean> get() = _ValidUserReponse

    // 내 수정 확인
    private val _ModifyUserResponse = MutableStateFlow(userModifyResponse("", ""))
    val modifyUserResponse: StateFlow<userModifyResponse> get() = _ModifyUserResponse

    private val _GetMyGifticon = MutableStateFlow<List<myGifticon>>(emptyList())
    val getMyGifticonResponse: StateFlow<List<myGifticon>> get() = _GetMyGifticon

    private val _GetMyAuction = MutableStateFlow<List<myAuctionData>>(emptyList())
    val getMyAuctionResponse: StateFlow<List<myAuctionData>> get() = _GetMyAuction

    private val _GetMyAuctionBid = MutableStateFlow<List<myAuctionBidData>>(emptyList())
    val getMyAuctionBidResponse: StateFlow<List<myAuctionBidData>> get() = _GetMyAuctionBid

    private val _GetMyBarter = MutableStateFlow<List<myBarterData>>(emptyList())
    val getMyBarterResponse: StateFlow<List<myBarterData>> get() = _GetMyBarter

    private val _GetMyBarterRequest = MutableStateFlow<List<myBarterRequestData>>(emptyList())
    val getMyBarterRequestResponse: StateFlow<List<myBarterRequestData>> get() = _GetMyBarterRequest

    private val _GetMyStore = MutableStateFlow<List<myStoreData>>(emptyList())
    val getMyStoreResponse: StateFlow<List<myStoreData>> get() = _GetMyStore

    private val _DeleteUserReponse = MutableStateFlow(false)
    val deleteUserResponse: StateFlow<Boolean> get() = _DeleteUserReponse

    companion object {
        const val TAG = "UserViewModel"
    }
    fun userDelete() {
        val userIdx = sharedPreferencesUtil.getUserId()
        Log.d(TAG, "userDelete started for userIdx: $userIdx")
        _isLoading.value = true

        viewModelScope.launch {
            try {
                Log.d(TAG, "Sending request to deleteUser service")
                val response = service.deleteUser(userIdx)

                _isLoading.value = false
                if (response.isSuccessful) {
                    Log.d(TAG, "User deletion successful")
                    _DeleteUserReponse.value=true
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "서버 error"
                    Log.e(TAG, "User deletion failed: $errorMessage")
                    _error.value = errorMessage
                }
            } catch (e: Exception) {
                Log.e(TAG, "Exception occurred: ${e.message}", e)
                _error.value = e.message
            } finally {
                Log.d(TAG, "userDelete completed")
                _isLoading.value = false
            }
        }
    }

    fun getUserIdFromPreference(): Long {
        return sharedPreferencesUtil.getUserId()
    }
    fun getMyGifticon() {
        val userIdx = sharedPreferencesUtil.getUserId()
        viewModelScope.launch {
            try {
                val response = service.getUserGifticon(userIdx)
                if (response.isSuccessful) {
                    Log.d("GifticonUpload", "Response: ${response.body()}")
                    _GetMyGifticon.value = response.body()?.items ?: listOf()
                } else {
                    // 실패한 HTTP 응답 코드와 메시지를 로그에 남깁니다.
                    val errorString = "Error Code: ${response.code()}, Error Body: ${
                        response.errorBody()?.string() ?: "Unknown"
                    }"
                    _error.value = errorString
                    Log.e("GifticonUpload", errorString)
                }
            } catch (e: Exception) {
                // 예외의 종류와 메시지, 그리고 스택 트레이스를 로그에 남깁니다.
                val errorMessage =
                    "Exception Type: ${e.javaClass.simpleName}, Message: ${e.message ?: "Unknown"}"
                _error.value = errorMessage
                Log.e("GifticonUpload", errorMessage, e)
            } finally {
            }
        }
    }

    fun getMyAuction() {
        val userIdx = sharedPreferencesUtil.getUserId()
        viewModelScope.launch {
            try {
                val response = service.getUserAuction(userIdx)
                if (response.isSuccessful) {
                    Log.d("GifticonUpload", "Response: ${response.body()}")
                    _GetMyAuction.value = response.body()?.items ?: listOf()
                } else {
                    // 실패한 HTTP 응답 코드와 메시지를 로그에 남깁니다.
                    val errorString = "Error Code: ${response.code()}, Error Body: ${
                        response.errorBody()?.string() ?: "Unknown"
                    }"
                    _error.value = errorString
                    Log.e("GifticonUpload", errorString)
                }
            } catch (e: Exception) {
                // 예외의 종류와 메시지, 그리고 스택 트레이스를 로그에 남깁니다.
                val errorMessage =
                    "Exception Type: ${e.javaClass.simpleName}, Message: ${e.message ?: "Unknown"}"
                _error.value = errorMessage
                Log.e("GifticonUpload", errorMessage, e)
            } finally {
            }
        }
    }

    fun getMyAuctionBid() {
        val userIdx = sharedPreferencesUtil.getUserId()
        viewModelScope.launch {
            try {
                val response = service.getUserAuctionBid(userIdx)
                if (response.isSuccessful) {
                    Log.d("GifticonUpload", "Response: ${response.body()}")
                    _GetMyAuctionBid.value = response.body()?.items ?: listOf()
                } else {
                    // 실패한 HTTP 응답 코드와 메시지를 로그에 남깁니다.
                    val errorString = "Error Code: ${response.code()}, Error Body: ${
                        response.errorBody()?.string() ?: "Unknown"
                    }"
                    _error.value = errorString
                    Log.e("GifticonUpload", errorString)
                }
            } catch (e: Exception) {
                // 예외의 종류와 메시지, 그리고 스택 트레이스를 로그에 남깁니다.
                val errorMessage =
                    "Exception Type: ${e.javaClass.simpleName}, Message: ${e.message ?: "Unknown"}"
                _error.value = errorMessage
                Log.e("GifticonUpload", errorMessage, e)
            } finally {
            }
        }
    }

    fun getMyBarter() {
        val userIdx = sharedPreferencesUtil.getUserId()
        viewModelScope.launch {
            try {
                val response = service.getUserBarter(userIdx)
                if (response.isSuccessful) {
                    Log.d("GifticonUpload", "Response: ${response.body()}")
                    _GetMyBarter.value = response.body()?.items ?: listOf()
                } else {
                    // 실패한 HTTP 응답 코드와 메시지를 로그에 남깁니다.
                    val errorString = "Error Code: ${response.code()}, Error Body: ${
                        response.errorBody()?.string() ?: "Unknown"
                    }"
                    _error.value = errorString
                    Log.e("GifticonUpload", errorString)
                }
            } catch (e: Exception) {
                // 예외의 종류와 메시지, 그리고 스택 트레이스를 로그에 남깁니다.
                val errorMessage =
                    "Exception Type: ${e.javaClass.simpleName}, Message: ${e.message ?: "Unknown"}"
                _error.value = errorMessage
                Log.e("GifticonUpload", errorMessage, e)
            } finally {
            }
        }
    }

    fun getMyBarterRequest() {
        val userIdx = sharedPreferencesUtil.getUserId()
        viewModelScope.launch {
            try {
                val response = service.getUserBarterRequest(userIdx)
                if (response.isSuccessful) {
                    Log.d("GifticonUpload", "Response: ${response.body()}")
                    _GetMyBarterRequest.value = response.body()?.items ?: listOf()
                } else {
                    // 실패한 HTTP 응답 코드와 메시지를 로그에 남깁니다.
                    val errorString = "Error Code: ${response.code()}, Error Body: ${
                        response.errorBody()?.string() ?: "Unknown"
                    }"
                    _error.value = errorString
                    Log.e("GifticonUpload", errorString)
                }
            } catch (e: Exception) {
                // 예외의 종류와 메시지, 그리고 스택 트레이스를 로그에 남깁니다.
                val errorMessage =
                    "Exception Type: ${e.javaClass.simpleName}, Message: ${e.message ?: "Unknown"}"
                _error.value = errorMessage
                Log.e("GifticonUpload", errorMessage, e)
            } finally {
            }
        }
    }

    fun getMyStore() {
        val userIdx = sharedPreferencesUtil.getUserId()
        viewModelScope.launch {
            try {
                val response = service.getUserStore(userIdx)
                if (response.isSuccessful) {
                    Log.d("GifticonUpload", "Response: ${response.body()}")
                    _GetMyStore.value = response.body()?.items ?: listOf()
                } else {
                    // 실패한 HTTP 응답 코드와 메시지를 로그에 남깁니다.
                    val errorString = "Error Code: ${response.code()}, Error Body: ${
                        response.errorBody()?.string() ?: "Unknown"
                    }"
                    _error.value = errorString
                    Log.e("GifticonUpload", errorString)
                }
            } catch (e: Exception) {
                // 예외의 종류와 메시지, 그리고 스택 트레이스를 로그에 남깁니다.
                val errorMessage =
                    "Exception Type: ${e.javaClass.simpleName}, Message: ${e.message ?: "Unknown"}"
                _error.value = errorMessage
                Log.e("GifticonUpload", errorMessage, e)
            } finally {
            }
        }
    }





    fun profileSend(file: MultipartBody.Part) {
        val userIdx = sharedPreferencesUtil.getUserId()
        viewModelScope.launch {
            try {
                val response = service.profileSend(userIdx, file)
                if (response.isSuccessful) {
                    Log.d("ProfileSend", "Response: ${response.body()}")
                } else {
                    // 실패한 HTTP 응답 코드와 메시지를 로그에 남깁니다.
                    val errorString = "Error Code: ${response.code()}, Error Body: ${
                        response.errorBody()?.string() ?: "Unknown"
                    }"
                    _error.value = errorString
                    Log.e("ProfileSend", errorString)
                }
            } catch (e: Exception) {
                // 예외의 종류와 메시지, 그리고 스택 트레이스를 로그에 남깁니다.
                val errorMessage =
                    "Exception Type: ${e.javaClass.simpleName}, Message: ${e.message ?: "Unknown"}"
                _error.value = errorMessage
                Log.e("ProfileSend", errorMessage, e)
            } finally {
            }
        }
    }

    fun gifticonUpload(
        request: MultipartBody.Part, originalFile: MultipartBody.Part, cropFile: MultipartBody.Part
    ) {
        val userIdx = sharedPreferencesUtil.getUserId()
        viewModelScope.launch {
            try {
                val response = service.uploadgifiticon(userIdx, request, originalFile, cropFile)
                _UploadGifticonResponse.value = false
                if (response.isSuccessful) {
                    _UploadGifticonResponse.value = true
                    Log.d("GifticonUpload", "Response: ${response.body()}")
                } else {
                    // 실패한 HTTP 응답 코드와 메시지를 로그에 남깁니다.
                    val errorString = "Error Code: ${response.code()}, Error Body: ${
                        response.errorBody()?.string() ?: "Unknown"
                    }"
                    _error.value = errorString
                    Log.e("GifticonUpload", errorString)
                }
            } catch (e: Exception) {
                // 예외의 종류와 메시지, 그리고 스택 트레이스를 로그에 남깁니다.
                val errorMessage =
                    "Exception Type: ${e.javaClass.simpleName}, Message: ${e.message ?: "Unknown"}"
                _error.value = errorMessage
                Log.e("GifticonUpload", errorMessage, e)
            } finally {
            }
        }
    }


    fun getMyInfo() {
        val userIdx = sharedPreferencesUtil.getUserId()
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = service.getUserInfo(userIdx)

                // Log the response data
                Log.d("GetMyInfoModel", "Response Code: ${response.code()}")
                Log.d("GetMyInfoModel", "Response Message: ${response.message()}")

                if (response.isSuccessful) {
                    _GetMyInfoResponse.value =
                        response.body() ?: userInfoResponse(null, "", "", "", "", "", "")
                    Log.d("GetMyInfoModel", "Response Body: ${response.body()}")
                } else {
                    val errorBody = response.errorBody()?.string() ?: "서버 error"
                    _error.value = errorBody
                    Log.d("GetMyInfoModel", "Error Body: $errorBody")
                }
            } catch (e: Exception) {
                _error.value = e.message
                Log.e("GetMyInfoModel", "Exception occurred:", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun userValid(request: userValidRequest) {
        _isLoading.value = true
        Log.d("userValid", "Function userValid is called with request: $request")

        viewModelScope.launch {
            try {
                Log.d("userValid", "Trying to call service.checkUserValid")
                val response = service.checkUserValid(request)

                if (response.isSuccessful) {
                    Log.d("userValid", "Response is successful: ${response.body()}")
                    _ValidUserReponse.value = true
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "서버 error"
                    Log.e("userValid", "Error in response: $errorMessage")
                    _error.value = errorMessage
                }
            } catch (e: Exception) {
                Log.e("userValid", "Exception occurred: ${e.message}")
                _error.value = e.message
            } finally {
                Log.d("userValid", "Setting _isLoading to false")
                _isLoading.value = false
            }
        }
    }

    fun userModify(request: userModifyRequest) {
        val userIdx = sharedPreferencesUtil.getUserId()
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = service.modifyUserInfo(userIdx, request)
                _isLoading.value = false
                if (response.isSuccessful) {
                    _ModifyUserResponse.value = response.body() ?: userModifyResponse("", "")
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

private fun getSampleData(): List<myGifticon> {
    return listOf(
        myGifticon(1L, "123456789012", "커피 기프티콘", "2023-09-26", "2023-12-31",
            "https://example.com/images/all_image1.png", "https://example.com/images/data_image1.png",
            "보관", 1, 10, 1),
        myGifticon(2L, "223456789012", "케이크 기프티콘", "2023-09-27", "2023-12-31",
            "https://example.com/images/all_image2.png", "https://example.com/images/data_image2.png",
            "보관", 1, 11, 2),
        myGifticon(3L, "323456789012", "피자 기프티콘", "2023-09-28", "2023-12-31",
            "https://example.com/images/all_image3.png", "https://example.com/images/data_image3.png",
            "보관", 1, 12, 3),
        myGifticon(4L, "423456789012", "버거 기프티콘", "2023-09-29", "2023-12-31",
            "https://example.com/images/all_image4.png", "https://example.com/images/data_image4.png",
            "보관", 1, 13, 4),
        myGifticon(5L, "523456789012", "아이스크림 기프티콘", "2023-09-30", "2023-12-31",
            "https://example.com/images/all_image5.png", "https://example.com/images/data_image5.png",
            "보관", 1, 14, 5),
        myGifticon(6L, "623456789012", "도넛 기프티콘", "2023-10-01", "2023-12-31",
            "https://example.com/images/all_image6.png", "https://example.com/images/data_image6.png",
            "보관", 1, 15, 1),
        myGifticon(7L, "723456789012", "샐러드 기프티콘", "2023-10-02", "2023-12-31",
            "https://example.com/images/all_image7.png", "https://example.com/images/data_image7.png",
            "보관", 1, 16, 2),
        myGifticon(8L, "823456789012", "스무디 기프티콘", "2023-10-03", "2023-12-31",
            "https://example.com/images/all_image8.png", "https://example.com/images/data_image8.png",
            "보관", 1, 17, 3),
        myGifticon(9L, "923456789012", "샌드위치 기프티콘", "2023-10-04", "2023-12-31",
            "https://example.com/images/all_image9.png", "https://example.com/images/data_image9.png",
            "보관", 1, 18, 4),
        myGifticon(10L, "023456789012", "파스타 기프티콘", "2023-10-05", "2023-12-31",
            "https://example.com/images/all_image10.png", "https://example.com/images/data_image10.png",
            "보관", 1, 19, 5)
    )
}
