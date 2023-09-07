package com.example.project.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.api.BarterFilterDTO
import com.example.project.api.BarterService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BarterViewModel @Inject constructor(
    private val service: BarterService
) : ViewModel() {

    private val _barterItems = MutableStateFlow<List<BarterItemData>>(emptyList())
    val barterItems: StateFlow<List<BarterItemData>> = _barterItems

    private val _totalItems = MutableStateFlow<Int>(0)
    val totalItems: StateFlow<Int> = _totalItems

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private var currentPage = 1
    private var currentFilter = BarterFilterDTO("", "", null, currentPage)

    init {
        fetchBarterItems(currentPage)
    }

    fun changePage(page: Int) {
        currentPage = page
        fetchBarterItems(currentPage)
    }

    fun applyFilter(filter: BarterFilterDTO) {
        currentFilter = filter
        currentPage = 1 // 초기 페이지로 설정
        fetchBarterItems(currentPage)
    }

    fun searchItems(query: String) {
        currentFilter = currentFilter.copy(searchQuery = query)
        fetchBarterItems(currentPage)
    }


    private fun fetchBarterItems(page: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = if (currentFilter.searchQuery != null) {
                    service.searchBarterItems(currentFilter)
                } else {
                    service.getAllBarterItems(currentFilter)
                }
                if (response.isSuccessful && response.body() != null) {
                    _barterItems.value = response.body()!!.items
                    _totalItems.value = response.body()!!.total
                } else {
                    _error.value = "Failed to load data: ${response.message()}"
                    _barterItems.value = getSampleData()
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage
                _barterItems.value = getSampleData()
            } finally {
                _isLoading.value = false
            }
        }
    }
}


// API response를 위한 데이터 클래스
data class BarterItemData(
    val index: Int,
    val image: String,
    val name: String,
    val gifticonTime: String,
    val barterTime: String,
    val popular: String,
    val preper: String,
)

// 인터넷 미연결 샘플데이터
private fun getSampleData(): List<BarterItemData> {
    return listOf(
        BarterItemData(1,"image1", "Item1", "2일", "5시간", "서버미연결", "치킨"),
        BarterItemData(2,"image2", "Item2", "3일", "4시간", "중간", "커피"),
        BarterItemData(3,"image3", "Item3", "1일", "2시간", "낮음", "피자"),
        BarterItemData(4,"image4", "Item4", "4일", "6시간", "높음", "물건"),
        BarterItemData(5,"image5", "Item5", "5일", "3시간", "중간", "등등")
    )
}