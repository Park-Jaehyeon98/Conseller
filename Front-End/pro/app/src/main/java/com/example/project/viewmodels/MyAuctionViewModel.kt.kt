package com.example.project.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.api.MyAuctionListResponseDTO
import com.example.project.api.MyNotificationAnswerRequestDTO
import com.example.project.api.MyNotificationResponseDTO
import com.example.project.api.MyService
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

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // 알람 목록 불러오기
    private val _myNotifications = MutableStateFlow<List<MyNotificationResponseDTO>>(emptyList())
    val myNotifications: StateFlow<List<MyNotificationResponseDTO>> = _myNotifications

    init {
        fetchMyAuctions()
        fetchMyNotifications() // 알람 목록도 초기화 시점에 불러옵니다.
    }

    // 내가 입찰한 경매목록 불러오기
    fun fetchMyAuctions() {
        viewModelScope.launch {
            _loading.value = true
            val userIdx = sharedPreferencesUtil.getUserId() // Note: Assuming this function retrieves the required userIdx
            try {
                val response = service.getMyAuctions(userIdx)
                if (response.isSuccessful) {
                    _myAuctions.value = response.body() ?: emptyList()
                } else {
                    _error.value = response.message()
                }
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
            val userIdx = sharedPreferencesUtil.getUserId()
            try {
                val response = service.getMyNotifications(userIdx)
                if (response.isSuccessful) {
                    _myNotifications.value = response.body() ?: emptyList()
                } else {
                    _error.value = response.message()
                    _myNotifications.value = getSampleData()
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _loading.value = false
            }
        }
    }

    // 알람 답변 보내기
    fun sendNotificationAnswer(notificationIdx: Long, notificationType: String, answer: Boolean) {
        viewModelScope.launch {
            _loading.value = true
            val requestDTO = MyNotificationAnswerRequestDTO(notificationIdx, notificationType, answer)
            try {
                val response = service.submitNotificationAnswer(requestDTO)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody?.success == true) {
                        // 응답이 성공적이면 다시 갱신
                        fetchMyNotifications()
                    } else {
                        _error.value = responseBody?.message ?: "Unknown error"
                    }
                } else {
                    _error.value = response.message()
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _loading.value = false
            }
        }
    }
}

// 인터넷 미연결 샘플데이터
private fun getSampleData(): List<MyNotificationResponseDTO> {
    return listOf(
        MyNotificationResponseDTO(5,"입금확인","20230921111111","알람테스트 데이터 1"),
        MyNotificationResponseDTO(4,"물물신청","20230922123456","알람테스트 데이터 2"),
        MyNotificationResponseDTO(3,"기프티콘시간","20230923111111","알람테스트 데이터 3"),
        MyNotificationResponseDTO(2,"게시글시간","20230924123456","알람테스트 데이터 4"),
        MyNotificationResponseDTO(1,"6","20230925111111","알람테스트 데이터 5"),
    )
}