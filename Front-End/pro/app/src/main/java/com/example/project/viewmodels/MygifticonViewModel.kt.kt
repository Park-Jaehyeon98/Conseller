package com.example.project.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.api.GifticonRequestDTO
import com.example.project.api.MyService
import com.example.project.sharedpreferences.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MygifticonViewModel @Inject constructor(
    private val myService: MyService,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) : ViewModel() {

    private val _gifticonItems = MutableStateFlow<List<GifticonData>>(emptyList())
    val gifticonItems: StateFlow<List<GifticonData>> get() = _gifticonItems

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private var currentPage = 1

    init {
        val user_idx = sharedPreferencesUtil.getUserId()
        getUserGifticons(user_idx, currentPage)
    }

    fun changePage(page: Int) {
        currentPage = page
        val user_idx = sharedPreferencesUtil.getUserId()
        getUserGifticons(user_idx, currentPage)
    }

    fun getUserGifticons(user_idx: Int, page: Int) {
        viewModelScope.launch {
            try {
                val requestDTO = GifticonRequestDTO(user_idx, currentPage)
                val response = myService.getGifticons(requestDTO)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _gifticonItems.value = responseBody.items
                    } else {
                        _error.value = "No data received"
                    }
                } else {
                    _error.value = "Network Error: ${response.code()}"
                    // 실패시 예시 데이터 추가
                    _gifticonItems.value = getSampleData()
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage
                // 예외 발생 시 예시 데이터 추가
                _gifticonItems.value = getSampleData()
            }
        }
    }

}

// API response를 위한 데이터 클래스
data class GifticonData(
    val index: Int,
    val image: String,
    val name: String,
    val gifticonTime: String,
)

// 인터넷 미연결 샘플데이터
private fun getSampleData(): List<GifticonData> {
    return listOf(
        GifticonData(1,"image1", "Item1", "2일",),
        GifticonData(2,"image2", "Item2", "3일",),
        GifticonData(3,"image3", "Item3", "1일",),
        GifticonData(4,"image4", "Item4", "4일",),
        GifticonData(5,"image5", "Item5", "4일",),
        GifticonData(6,"image6", "Item6", "4일",),
        GifticonData(7,"image7", "Item7", "4일",),
        GifticonData(8,"image8", "Item8", "5일",)
    )
}