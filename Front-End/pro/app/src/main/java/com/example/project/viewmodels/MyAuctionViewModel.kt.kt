package com.example.project.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.api.MyAuctionListResponseDTO
import com.example.project.api.MyNotificationAnswerRequestDTO
import com.example.project.api.MyNotificationResponseDTO
import com.example.project.api.MyService
import com.example.project.api.RegistInquiryRequestDTO
import com.example.project.di.CustomException
import com.example.project.sharedpreferences.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyAuctionViewModel @Inject constructor(
    private val service: MyService,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) : ViewModel() {

    // 내가 "입찰"한 경매목록 불러오기
    private val _myAuctions = MutableStateFlow<List<MyAuctionListResponseDTO>>(emptyList())
    val myAuctions: StateFlow<List<MyAuctionListResponseDTO>> = _myAuctions

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> = _loading

    private val _inquiryNavi = MutableStateFlow<String?>(null)
    val inquiryNavi: StateFlow<String?> = _inquiryNavi

    // 알람 목록 불러오기
    private val _myNotifications = MutableStateFlow<List<MyNotificationResponseDTO>>(emptyList())
    val myNotifications: StateFlow<List<MyNotificationResponseDTO>> = _myNotifications

    // 신고 네비게이트
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // 이벤트 확인
    private val _event = MutableStateFlow<String?>(null)
    val event: StateFlow<String?> = _event


    // 내가 입찰한 경매목록 불러오기
    fun fetchMyAuctions() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            val userIdx = sharedPreferencesUtil.getUserId()
            try {
                val response = service.getMyAuctions(userIdx)
                if (response.isSuccessful) {
                    _myAuctions.value = response.body() ?: emptyList()
                }
            } catch (e: CustomException) {
                _error.value = e.message
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _loading.value = false
            }
        }
    }

    // 내 알람 목록 불러오기
    fun fetchMyNotifications() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            val userIdx = sharedPreferencesUtil.getUserId()
            try {
                val response = service.getMyNotifications(userIdx)
                if (response.isSuccessful) {
                    _myNotifications.value = response.body() ?: emptyList()
                    _myNotifications.value = getSampleData()
                }
            } catch (e: CustomException) {
                _error.value = e.message
                _myNotifications.value = getSampleData()
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _loading.value = false
            }
        }
    }

    // 알람 답변 보내기
    fun sendNotificationAnswer(notificationIdx: Long, notificationType: Int, answer: Boolean) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            val requestDTO = MyNotificationAnswerRequestDTO(notificationIdx, notificationType, answer)
            try {
                val response = service.submitNotificationAnswer(requestDTO)
                if (response.isSuccessful) {
                    fetchMyNotifications()
                }
            } catch (e: CustomException) {
                _error.value = e.message
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _loading.value = false
            }
        }
    }

    // 신고하기
    fun resistInquiry(title: String, content: String, inquiryType: Int, reportedUserIdx: Long,){
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            val userIdx = sharedPreferencesUtil.getUserId()
            try {
                val response = service.registInquiry(RegistInquiryRequestDTO(userIdx, title, content, inquiryType, reportedUserIdx))
                if (response.isSuccessful) {
                    _inquiryNavi.value = "ok"
                }
            } catch (e: CustomException) {
                _error.value = e.message
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _loading.value = false
            }
        }
    }
    // 이벤트하기
    fun resistEvent(){
        viewModelScope.launch {
            _loading.value = true
            _event.value = null
            val userIdx = sharedPreferencesUtil.getUserId()
            try {
                val response = service.firstEvent(userIdx)
                if (response.isSuccessful) {
                    _event.value = "성공"
                }
            } catch (e: CustomException) {
                _event.value = e.message
            } catch (e: Exception) {
                _event.value = e.localizedMessage
            } finally {
                _loading.value = false
            }
        }
    }
}

// 인터넷 미연결 샘플데이터
private fun getSampleData(): List<MyNotificationResponseDTO> {
    return listOf(
        MyNotificationResponseDTO(5,1,"20230921111111","알람테스트 데이터 1"),
        MyNotificationResponseDTO(4,2,"20230922123456","알람테스트 데이터 2"),
        MyNotificationResponseDTO(3,3,"20230923111111","알람테스트 데이터 3"),
        MyNotificationResponseDTO(2,4,"20230924123456","알람테스트 데이터 4"),
        MyNotificationResponseDTO(1,5,"20230925111111","알람테스트 데이터 5"),
    )
}