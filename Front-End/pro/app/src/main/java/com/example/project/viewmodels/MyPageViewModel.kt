package com.example.project.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.api.AuctionBidRequestDTO
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
import com.example.project.di.CustomException
import com.example.project.sharedpreferences.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONException
import org.json.JSONObject
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

    //기프티콘 삭제 상태
    private val _DeleteGifticonResponse = MutableStateFlow<Boolean>(false)
    val deleteGifticonResponse: StateFlow<Boolean> get() = _DeleteGifticonResponse

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

    private val _GetMyGifticonInfo = MutableStateFlow(myGifticon(0, "","","","","","","",0,0,0))
    val getMyGifticonInfoResponse: StateFlow<myGifticon> get() = _GetMyGifticonInfo
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

    fun getUserGifticonInfo(gifticonIdx:Long){

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.getUserGifticoninfo(gifticonIdx)
                if (response.isSuccessful && response.body() != null) {
                    _GetMyGifticonInfo.value=response.body()!!
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

    fun DeleteUserGifticon(gifticonIdx:Long){

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.getUserGifticonDelete(gifticonIdx)
                if (response.isSuccessful && response.body() != null) {
                    _DeleteGifticonResponse.value=true
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


    fun getUserIdFromPreference(): Long {
        return sharedPreferencesUtil.getUserId()
    }
    fun getMyGifticon() {
        val userIdx = sharedPreferencesUtil.getUserId()
        viewModelScope.launch {
            try {
                val response = service.getUserGifticon(userIdx)
                if (response.isSuccessful) {
                    Log.d("getMyGifticon", "Response: ${response.body()}")
                    _GetMyGifticon.value = response.body()?.items ?: listOf()
                } else {
                    // 실패한 HTTP 응답 코드와 메시지를 로그에 남깁니다.
                    val errorString = "Error Code: ${response.code()}, Error Body: ${
                        response.errorBody()?.string() ?: "Unknown"
                    }"
                    _error.value = errorString
                    Log.e("getMyGifticon", errorString)
                }
            } catch (e: Exception) {
                // 예외의 종류와 메시지, 그리고 스택 트레이스를 로그에 남깁니다.
                val errorMessage =
                    "Exception Type: ${e.javaClass.simpleName}, Message: ${e.message ?: "Unknown"}"
                _error.value = errorMessage
                Log.e("getMyGifticon", errorMessage, e)
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
                    Log.d("getMyAuctionBid", "Response: ${response.body()}")
                    _GetMyAuction.value = response.body()?.items ?: listOf()
                } else {
                    // 실패한 HTTP 응답 코드와 메시지를 로그에 남깁니다.
                    val errorString = "Error Code: ${response.code()}, Error Body: ${
                        response.errorBody()?.string() ?: "Unknown"
                    }"
                    _error.value = errorString
                    Log.e("getMyAuctionBid", errorString)
                }
            } catch (e: Exception) {
                // 예외의 종류와 메시지, 그리고 스택 트레이스를 로그에 남깁니다.
                val errorMessage =
                    "Exception Type: ${e.javaClass.simpleName}, Message: ${e.message ?: "Unknown"}"
                _error.value = errorMessage
                Log.e("getMyAuctionBid", errorMessage, e)
            } finally {
            }
        }
    }

    // 유저가 입찰한 글의 정보 불러오기
    fun getMyAuctionBid() {
        val userIdx = sharedPreferencesUtil.getUserId()
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val response = service.getUserAuctionBid(userIdx)

                if (response.isSuccessful) {
                    _GetMyAuctionBid.value = response.body()?.items ?: listOf()
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

    fun getMyBarter() {
        val userIdx = sharedPreferencesUtil.getUserId()
        viewModelScope.launch {
            try {
                val response = service.getUserBarter(userIdx)
                if (response.isSuccessful) {
                    Log.d("getMyBarter", "Response: ${response.body()}")
                    _GetMyBarter.value = response.body()?.items ?: listOf()
                } else {
                    // 실패한 HTTP 응답 코드와 메시지를 로그에 남깁니다.
                    val errorString = "Error Code: ${response.code()}, Error Body: ${
                        response.errorBody()?.string() ?: "Unknown"
                    }"
                    _error.value = errorString
                    Log.e("getMyBarter", errorString)
                }
            } catch (e: Exception) {
                // 예외의 종류와 메시지, 그리고 스택 트레이스를 로그에 남깁니다.
                val errorMessage =
                    "Exception Type: ${e.javaClass.simpleName}, Message: ${e.message ?: "Unknown"}"
                _error.value = errorMessage
                Log.e("getMyBarter", errorMessage, e)
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
                    Log.d("getMyBarterRequest", "Response: ${response.body()}")
                    _GetMyBarterRequest.value = response.body()?.items ?: listOf()
                } else {
                    // 실패한 HTTP 응답 코드와 메시지를 로그에 남깁니다.
                    val errorString = "Error Code: ${response.code()}, Error Body: ${
                        response.errorBody()?.string() ?: "Unknown"
                    }"
                    _error.value = errorString
                    Log.e("getMyBarterRequest", errorString)
                }
            } catch (e: Exception) {
                // 예외의 종류와 메시지, 그리고 스택 트레이스를 로그에 남깁니다.
                val errorMessage =
                    "Exception Type: ${e.javaClass.simpleName}, Message: ${e.message ?: "Unknown"}"
                _error.value = errorMessage
                Log.e("getMyBarterRequest", errorMessage, e)
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
                    Log.d("getMyStore", "Response: ${response.body()}")
                    _GetMyStore.value = response.body()?.items ?: listOf()
                } else {
                    // 실패한 HTTP 응답 코드와 메시지를 로그에 남깁니다.
                    val errorString = "Error Code: ${response.code()}, Error Body: ${
                        response.errorBody()?.string() ?: "Unknown"
                    }"
                    _error.value = errorString
                    Log.e("getMyStore", errorString)
                }
            } catch (e: Exception) {
                // 예외의 종류와 메시지, 그리고 스택 트레이스를 로그에 남깁니다.
                val errorMessage =
                    "Exception Type: ${e.javaClass.simpleName}, Message: ${e.message ?: "Unknown"}"
                _error.value = errorMessage
                Log.e("getMyStore", errorMessage, e)
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
        request: RequestBody, originalFile: MultipartBody.Part, cropFile: MultipartBody.Part
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
                    val errorBody = response.errorBody()?.string() ?: "Unknown"
                    try {
                        val errorJson = JSONObject(errorBody)
                        val errorCode = errorJson.optString("errorCode", "Unknown")
                        val errorMessage = errorJson.optString("errorMessage", "Unknown")
                        val errorString = "Error Code: ${response.code()}, Error Body: $errorBody, ErrorCode: $errorCode, ErrorMessage: $errorMessage"
                        _error.value = errorString
                        Log.e("GifticonUpload", errorString)
                    } catch (jsonException: JSONException) {
                        val errorString = "Error Code: ${response.code()}, Error Body: $errorBody"
                        _error.value = errorString
                        Log.e("GifticonUpload", errorString, jsonException)
                    }
                }
            } catch (e: Exception) {
                // 예외의 종류와 메시지, 그리고 스택 트레이스를 로그에 남깁니다.
                val errorMessage =
                    "Exception Type: ${e.javaClass.simpleName}, Message: ${e.message ?: "Unknown"}"
                _error.value = errorMessage
                Log.e("GifticonUpload", errorMessage, e)
            } finally {
                // 필요한 경우 finally 블록에 코드를 추가합니다.
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

