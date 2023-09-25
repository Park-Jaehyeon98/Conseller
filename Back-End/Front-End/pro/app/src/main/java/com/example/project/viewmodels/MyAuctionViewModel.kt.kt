package com.example.project.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.api.MyAuctionListResponseDTO
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
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _loading.value = false
            }
        }
    }
}
