package com.example.project.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.api.MyPageService
import com.example.project.api.myAuctionBidData
import com.example.project.api.myAuctionData
import com.example.project.api.myBarterData
import com.example.project.api.myBarterRequestData
import com.example.project.api.myGifticon
import com.example.project.api.myStoreData
import com.example.project.api.userInfoResponse
import com.example.project.api.userModifyRequest
import com.example.project.api.userModifyResponse
import com.example.project.api.userValidRequest
import com.example.project.di.CustomException
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
    private val service: MyPageService, private val sharedPreferencesUtil: SharedPreferencesUtil
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    //프로필 사진 업로드
    private val _UploadProfileResponse = MutableStateFlow(false)
    val uploadProfileResponse: StateFlow<Boolean> get() = _UploadProfileResponse

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
    fun userDelete() {
        val userIdx = sharedPreferencesUtil.getUserId()
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = service.deleteUser(userIdx)
                if (response.isSuccessful) {
                    _error.value = null
                    _DeleteUserReponse.value=true
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

    fun getUserGifticonInfo(gifticonIdx:Long){
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = service.getUserGifticoninfo(gifticonIdx)
                if (response.isSuccessful) {
                    _error.value = null
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
                if (response.isSuccessful) {
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
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.getUserGifticon(userIdx)
                if (response.isSuccessful) {
                    _GetMyGifticon.value = response.body()?.items ?: listOf()
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


    fun getMyAuction() {
        val userIdx = sharedPreferencesUtil.getUserId()
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.getUserAuction(userIdx)
                if (response.isSuccessful) {
                    _GetMyAuction.value = response.body()?.items ?: listOf()
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
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.getUserBarter(userIdx)
                if (response.isSuccessful) {
                    _GetMyBarter.value = response.body()?.items ?: listOf()
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

    fun getMyBarterRequest() {
        val userIdx = sharedPreferencesUtil.getUserId()
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.getUserBarterRequest(userIdx)
                if (response.isSuccessful) {
                    _GetMyBarterRequest.value = response.body()?.items ?: listOf()
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

    fun getMyStore() {
        val userIdx = sharedPreferencesUtil.getUserId()
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.getUserStore(userIdx)
                if (response.isSuccessful) {
                    _GetMyStore.value = response.body()?.items ?: listOf()
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





    fun profileSend(file: MultipartBody.Part) {
        val userIdx = sharedPreferencesUtil.getUserId()
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.profileSend(userIdx, file)
                if (response.isSuccessful) {
                    _UploadProfileResponse.value=true
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

    fun gifticonUpload(
        request: RequestBody, originalFile: MultipartBody.Part, cropFile: MultipartBody.Part
    ) {
        val userIdx = sharedPreferencesUtil.getUserId()
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.uploadgifiticon(userIdx, request, originalFile, cropFile)
                if (response.isSuccessful) {
                    _UploadGifticonResponse.value = true
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


    fun getMyInfo() {
        val userIdx = sharedPreferencesUtil.getUserId()
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.getUserInfo(userIdx)
                if (response.isSuccessful) {
                    _GetMyInfoResponse.value =
                        response.body() ?: userInfoResponse(null, "", "", "", "", "", "")
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

    fun userValid(request: userValidRequest) {
        _isLoading.value = true
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.checkUserValid(request)
                if (response.isSuccessful) {
                    _ValidUserReponse.value = true
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

    fun userModify(request: userModifyRequest) {
        val userIdx = sharedPreferencesUtil.getUserId()
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.modifyUserInfo(userIdx, request)
                if (response.isSuccessful) {
                    _ModifyUserResponse.value = response.body() ?: userModifyResponse("", "")
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

