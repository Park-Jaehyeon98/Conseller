package com.example.project.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.api.*
import com.example.project.sharedpreferences.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val service: StoreService,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) : ViewModel() {

    private var currentPage = 1
    private var currentFilter = StoreFilterDTO(0, 0, 0, null, currentPage)

    private val _storeItems = MutableStateFlow<List<StoreItemData>>(emptyList())
    val storeItems: StateFlow<List<StoreItemData>> = _storeItems

    private val _totalItems = MutableStateFlow<Long>(0)
    val totalItems: StateFlow<Long> = _totalItems

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun getUserIdFromPreference(): Long {
        return sharedPreferencesUtil.getUserId()
    }

    private val _storeDetail = MutableStateFlow<StoreDetailResponseDTO?>(null)
    val storeDetail: StateFlow<StoreDetailResponseDTO?> = _storeDetail

    private val _myStoreItems = MutableStateFlow<List<StoreItemData>>(emptyList())
    val myStoreItems: StateFlow<List<StoreItemData>> = _myStoreItems

    private val _navigateToStoreDetail = MutableStateFlow<Long?>(null)
    val navigateToStoreDetail: StateFlow<Long?> = _navigateToStoreDetail

    // 스토어 거래 진행 계좌번호 받기
    private val _storeTrade = MutableStateFlow<StoreTradeResponseDTO?>(null)
    val storeTrades: StateFlow<StoreTradeResponseDTO?> = _storeTrade

    // 스토어 거래 취소
    private val _cancelTradeSuccessful = MutableStateFlow<Boolean?>(null)
    val cancelTradeSuccessful: StateFlow<Boolean?> get() = _cancelTradeSuccessful

    // 스토어 거래 입금완료
    private val _paymentCompleted = MutableStateFlow<StoreTradeCompleteResponseDTO?>(null)
    val paymentCompleted: StateFlow<StoreTradeCompleteResponseDTO?> get() = _paymentCompleted


    fun resetNavigation() {
        _navigateToStoreDetail.value = null
    }

    init {
        fetchStoreItems()
    }

    fun changePage(page: Int) {
        currentPage = page
        currentFilter = currentFilter.copy(page = currentPage)
        fetchStoreItems()
    }

    fun applyFilter(filter: StoreFilterDTO) {
        currentFilter = filter.copy(page = 1)
        currentPage = 1
        fetchStoreItems()
    }

    fun searchItems(query: String) {
        currentFilter = currentFilter.copy(searchQuery = query, page = 1)
        currentPage = 1
        fetchStoreItems()
    }

    fun fetchStoreItems() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                Log.d("@@@@@","$")
                val response = service.getAllStoreItems(currentFilter)

                if (response.isSuccessful && response.body() != null) {
                    _storeItems.value = response.body()!!.items
                    _totalItems.value = response.body()!!.totalElements
                    Log.d("@@@@@","222222222222222222222")
                    Log.d("#####","${response.body()}")
                    Log.d("$$$$$","${response.body()!!.items}")
                    Log.d("%%%%%","${response.body()!!.totalElements}")
                } else {
                    _error.value = "Failed to load data: ${response.message()}"
                    Log.d("@@@@@","1111111111111111111")
                    _storeItems.value = getSampleData()
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage
                _storeItems.value = getSampleData()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun registerStoreItem(storePrice: Int, storeText: String, gifticonIdx: Long) {
        val userIdx = sharedPreferencesUtil.getUserId()

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.registerStoreItem(RegisterStoreDTO(storePrice, storeText, gifticonIdx, userIdx))

                if (response.isSuccessful && response.body() != null) {
                    _navigateToStoreDetail.value = response.body()?.storeIdx
                } else {
                    _error.value = "Failed to register item: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateStoreItem(storeIdx: Long, endDate: String, storeText: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val updateData = UpdateStoreDTO(endDate, storeText)
                val response = service.updateStoreItem(storeIdx, updateData)

                if (response.isSuccessful) {
                    fetchStoreDetail(storeIdx)
                    _error.value = null
                } else {
                    _error.value = "Failed to update item: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteStoreItem(storeIdx: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = service.deleteStoreItem(storeIdx)

                if (response.isSuccessful) {
                    val deleteResponse: DeleteStoreResponse? = response.body()

                    if (deleteResponse?.success == true) {
                        fetchStoreItems()
                    } else {
                        _error.value = deleteResponse?.message ?: "Unknown error occurred"
                    }
                } else {
                    _error.value = "Failed to delete item: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchStoreDetail(storeIdx: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.getStoreDetail(storeIdx)

                if (response.isSuccessful && response.body() != null) {
                    _storeDetail.value = response.body()
                } else {
                    _error.value = "Failed to load store detail: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchMyStoreItems(userIdx: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.getMyStoreItems(userIdx)
                if (response.isSuccessful && response.body() != null) {
                    _myStoreItems.value = response.body()!!.items
                } else {
                    _error.value = "Failed to load my store items: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _isLoading.value = false
            }
        }
    }
    // 스토어 거래 진행
    fun fetchAccountDetails(storeIdx: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.getStoreTrade(storeIdx)
                if (response.isSuccessful && response.body() != null) {
                    _storeTrade.value = response.body()
                } else {
                    _error.value = "Failed to load account details: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _isLoading.value = false
            }
        }
    }

    // 스토어 거래 취소
    fun cancelStoreTrade(storeIdx: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.cancelStoreTrade(storeIdx)
                if (response.isSuccessful) {
                    _cancelTradeSuccessful.value = true
                } else {
                    _error.value = "Failed to cancel store trade: ${response.message()}"
                    _cancelTradeSuccessful.value = false
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage
                _cancelTradeSuccessful.value = false
            } finally {
                _isLoading.value = false
            }
        }
    }
    // 스토어 거래 입금완료
    fun completeStorePayment(storeIdx: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.completeStorePayment(storeIdx)
                if (response.isSuccessful && response.body() != null) {
                    _paymentCompleted.value = response.body()
                } else {
                    _error.value = "Failed to complete store payment: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _isLoading.value = false
            }
        }
    }

}

// API response를 위한 데이터 클래스
data class StoreItemData(
    val storeIdx: Long,
    val gifticonDataImageName: String,
    val giftconName: String,
    val gifticonEndDate: String,
    val storeEndDate: String,
    val isDeposit: Boolean,
    val storePrice: Int,
)

// 인터넷 미연결 샘플데이터
private fun getSampleData(): List<StoreItemData> {
    return listOf(
        StoreItemData(1,"image1", "Item1", "20231001235959", "20231001235959", true, 10000),
        StoreItemData(2,"image2", "Item2", "20231002235959", "20231002235959", false, 20000),
        StoreItemData(3,"https://via.placeholder.com/150", "Item3", "20231003235959", "20231003235959", true, 30000),
        StoreItemData(4,"image4", "Item4", "20231004235959", "20231004235959", false, 40000),
        StoreItemData(5,"image5", "Item5", "20231005235959", "20231005235959", true, 50000)
    )
}
