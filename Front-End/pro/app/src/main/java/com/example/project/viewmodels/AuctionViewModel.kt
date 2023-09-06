package com.example.project.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.api.AuctionFilterDTO
import com.example.project.api.AuctionService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuctionViewModel @Inject constructor(
    private val service: AuctionService
) : ViewModel() {

    private val _auctionItems = MutableStateFlow<List<AuctionItemData>>(emptyList())
    val auctionItems: StateFlow<List<AuctionItemData>> = _auctionItems

    private val _totalItems = MutableStateFlow<Int>(0)
    val totalItems: StateFlow<Int> = _totalItems

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private var currentPage = 1
    private var currentFilter = AuctionFilterDTO("", "", "", null, currentPage)

    init {
        fetchAuctionItems(currentPage)
    }

    fun changePage(page: Int) {
        currentPage = page
        fetchAuctionItems(currentPage)
    }

    fun applyFilter(filter: AuctionFilterDTO) {
        currentFilter = filter
        currentPage = 1 // 초기 페이지로 설정
        fetchAuctionItems(currentPage)
    }

    fun searchItems(query: String) {
        currentFilter = currentFilter.copy(searchQuery = query)
        fetchAuctionItems(currentPage)
    }


    private fun fetchAuctionItems(page: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = if (currentFilter.searchQuery != null) {
                    service.searchAuctionItems(currentFilter)
                } else {
                    service.getAllAuctionItems(currentFilter)
                }
                if (response.isSuccessful && response.body() != null) {
                    _auctionItems.value = response.body()!!.items
                    _totalItems.value = response.body()!!.total
                } else {
                    _error.value = "Failed to load data: ${response.message()}"
                    _auctionItems.value = getSampleData()
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage
                _auctionItems.value = getSampleData()
            } finally {
                _isLoading.value = false
            }
        }
    }
}


// API response를 위한 데이터 클래스
data class AuctionItemData(
    val index: Int,
    val image: String,
    val name: String,
    val gifticonTime: String,
    val auctionTime: String,
    val popular: String,
    val upperprice: String,
    val nowprice: String
)

// 인터넷 미연결 샘플데이터
private fun getSampleData(): List<AuctionItemData> {
    return listOf(
        AuctionItemData(1,"image1", "Item1", "2일", "5시간", "서버미연결", "1000원", "800원"),
        AuctionItemData(2,"image2", "Item2", "3일", "4시간", "중간", "2000원", "1500원"),
        AuctionItemData(3,"image3", "Item3", "1일", "2시간", "낮음", "3000원", "2500원"),
        AuctionItemData(4,"image4", "Item4", "4일", "6시간", "높음", "4000원", "3500원"),
        AuctionItemData(5,"image5", "Item5", "5일", "3시간", "중간", "5000원", "4500원")
    )
}