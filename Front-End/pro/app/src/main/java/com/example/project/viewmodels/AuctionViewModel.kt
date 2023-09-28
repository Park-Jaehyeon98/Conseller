package com.example.project.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.api.AuctionBidRequestDTO
import com.example.project.api.AuctionConfirmBuyPageResponseDTO
import com.example.project.api.AuctionConfirmPageResponseDTO
import com.example.project.api.AuctionConfirmRequestDTO
import com.example.project.api.AuctionDetailResponseDTO
import com.example.project.api.AuctionFilterDTO
import com.example.project.api.AuctionService
import com.example.project.api.AuctionTradeResponseDTO
import com.example.project.api.RegisterAuctionDTO
import com.example.project.api.StoreConfirmPageResponseDTO
import com.example.project.api.StoreConfirmRequestDTO
import com.example.project.api.UpdateAuctionDTO
import com.example.project.di.CustomException
import com.example.project.sharedpreferences.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AuctionViewModel @Inject constructor(
    private val service: AuctionService,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) : ViewModel() {

    private var currentPage = 0
    private var currentFilter = AuctionFilterDTO(0, 0, 0, null, currentPage)

    // 경매글 전체 목록 불러오기
    private val _auctionItems = MutableStateFlow<List<AuctionItemData>>(emptyList())
    val auctionItems: StateFlow<List<AuctionItemData>> = _auctionItems

    private val _totalItems = MutableStateFlow<Long>(0)
    val totalItems: StateFlow<Long> = _totalItems

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // 유저idx 불러오기
    fun getUserIdFromPreference(): Long {
        return sharedPreferencesUtil.getUserId()
    }


    // 경매글 상세보기
    private val _auctionDetail = MutableStateFlow<AuctionDetailResponseDTO?>(null)
    val auctionDetail: StateFlow<AuctionDetailResponseDTO?> = _auctionDetail


    // 내 경매글 목록
    private val _myAuctionItems = MutableStateFlow<List<AuctionItemData>>(emptyList())
    val myAuctionItems: StateFlow<List<AuctionItemData>> = _myAuctionItems

    // 경매 등록후 auctionIdx 받기
    private val _navigateToAuctionDetail = MutableStateFlow<Long?>(null)
    val navigateToAuctionDetail: StateFlow<Long?> = _navigateToAuctionDetail

    // 경매 거래 진행 계좌번호 받기
    private val _auctionTrade = MutableStateFlow<AuctionTradeResponseDTO?>(null)
    val auctionTrades: StateFlow<AuctionTradeResponseDTO?> = _auctionTrade

    // 경매 거래 취소
    private val _cancelTradeSuccessful = MutableStateFlow<Boolean?>(null)
    val cancelTradeSuccessful: StateFlow<Boolean?> get() = _cancelTradeSuccessful


    // 경매 확정 페이지 데이터
    private val _auctionConfirm = MutableStateFlow<AuctionConfirmPageResponseDTO?>(null)
    val auctionConfirm: StateFlow<AuctionConfirmPageResponseDTO?> = _auctionConfirm

    // 경매 확정 페이지 네비게이터
    private val _auctionConfirmNavi = MutableStateFlow<Boolean?>(null)
    val auctionConfirmNavi: StateFlow<Boolean?> = _auctionConfirmNavi

    // 경매 낙찰 페이지 데이터
    private val _auctionConfirmBuy = MutableStateFlow<AuctionConfirmBuyPageResponseDTO?>(null)
    val auctionConfirmBuy: StateFlow<AuctionConfirmBuyPageResponseDTO?> = _auctionConfirmBuy

    // 경매 낙찰 페이지 네비게이터
    private val _auctionConfirmBuyNavi = MutableStateFlow<Boolean?>(false)
    val auctionConfirmBuyNavi: StateFlow<Boolean?> = _auctionConfirmBuyNavi


    // 경매 등록후 네비게이션 리셋
    fun resetNavigation() {
        _navigateToAuctionDetail.value = null
    }

    fun changePage(page: Int) {
        currentPage = page
        currentFilter = currentFilter.copy(page = currentPage)
        fetchAuctionItems()
    }

    fun applyFilter(filter: AuctionFilterDTO) {
        currentFilter = filter.copy(page = 1)
        currentPage = 1
        fetchAuctionItems()
    }

    fun searchItems(query: String) {
        currentFilter = currentFilter.copy(searchQuery = query, page = 1)
        currentPage = 1
        fetchAuctionItems()
    }

    // 경매글 리스트 불러오기
    fun fetchAuctionItems() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.getAllAuctionItems(currentFilter)

                if (response.isSuccessful && response.body() != null) {
                    _auctionItems.value = response.body()!!.items
                    _totalItems.value = response.body()!!.totalElements
                }
            } catch (e: CustomException) {
                _error.value = e.message
                _auctionItems.value = getSampleData()
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _isLoading.value = false
            }
        }
    }

    // 경매글 등록
    fun registerAuctionItem(upperLimit: Int, lowerLimit: Int, postContent: String, gifticonIdx: Long) {
        val userIdx = getUserIdFromPreference()

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.registerAuctionItem(RegisterAuctionDTO(upperLimit, lowerLimit, postContent, gifticonIdx, userIdx))

                if (response.isSuccessful && response.body() != null) {
                    _navigateToAuctionDetail.value = response.body()?.auctionIdx
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

    // 경매글 수정
    fun updateAuctionItem(auctionIdx: Long, endDate: String, postContent: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val updateData = UpdateAuctionDTO(endDate, postContent)
                val response = service.updateAuctionItem(auctionIdx, updateData)

                if (response.isSuccessful) {
                    _error.value = null
                    fetchAuctionDetail(auctionIdx)
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

    // 경매글 삭제
    fun deleteAuctionItem(auctionIdx: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val response = service.deleteAuctionItem(auctionIdx)
                if (response.isSuccessful) {
                    _error.value = null
                    fetchAuctionItems()
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


    // 경매글 상세보기
    fun fetchAuctionDetail(auctionIdx: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.getAuctionDetail(auctionIdx)

                if (response.isSuccessful && response.body() != null) {
                    _auctionDetail.value = response.body()
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

    // 경매 입찰하기
    fun bidOnAuction(auctionIdx: Long, bidPrice: Int) {
        val userIdx = getUserIdFromPreference()
        val bidRequest = AuctionBidRequestDTO(userIdx, bidPrice)

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.bidOnAuction(auctionIdx, bidRequest)
                if (response.isSuccessful) {
                    _error.value = null
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


    // 경매 내 글 목록 불러오기
    fun fetchMyAuctionItems() {
        viewModelScope.launch {
            val userIdx = getUserIdFromPreference()
            _isLoading.value = true
            _error.value = null
            Log.d("@@@@@@@@@@@@@@@2","${userIdx}")
            try {
                val response = service.getMyAuctionItems(userIdx)
                if (response.isSuccessful && response.body() != null) {
                    _myAuctionItems.value = response.body()!!.items
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

    // 경매 거래 진행
    fun fetchAccountDetails(auctionIdx: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.getAuctionTrade(auctionIdx)
                if (response.isSuccessful && response.body() != null) {
                    _auctionTrade.value = response.body()
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

    // 경매 거래 취소
    fun cancelAuctionTrade(auctionIdx: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.cancelAuctionTrade(auctionIdx)
                if (response.isSuccessful) {
                    _cancelTradeSuccessful.value = true
                }
            } catch (e: CustomException) {
                _error.value = e.message
                _cancelTradeSuccessful.value = false
            } catch (e: Exception) {
                _error.value = e.localizedMessage
                _cancelTradeSuccessful.value = false
            } finally {
                _isLoading.value = false
            }
        }
    }
    // 경매 입금완료
    fun completeAuctionPayment(auctionIdx: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            _auctionConfirmBuyNavi.value = false
            try {
                val response = service.completeAuctionPayment(auctionIdx)
                if (response.isSuccessful) {
                    _error.value = null
                    _auctionConfirmBuyNavi.value = true
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

    // 경매 확정 페이지 불러오기
    fun fetchAuctionConfirmItems(auctionIdx: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.getAuctionConfirm(auctionIdx)

                if (response.isSuccessful && response.body() != null) {
                    _auctionConfirm.value = response.body()
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



    // 경매 확정 페이지 확정하기
    fun auctionConfirm(auctionIdx: Long, confirm: Boolean) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.auctionConfirm(AuctionConfirmRequestDTO(auctionIdx,confirm))

                if (response.isSuccessful) {
                    _auctionConfirm.value = null
                    _auctionConfirmNavi.value = true
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

    // 경매 낙찰 페이지 불러오기
    fun fetchAuctionConfirmBuyItems(auctionIdx: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.getAuctionConfirmBuy(auctionIdx)

                if (response.isSuccessful && response.body() != null) {
                    _auctionConfirmBuy.value = response.body()
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


// API response를 위한 데이터 클래스
data class AuctionItemData(
    val auctionIdx: Long,
    val gifticonDataImageName: String,
    val gifticonName: String,
    val gifticonEndDate: String,
    val auctionEndDate: String,
    val deposit: Boolean,
    val upperPrice: Int,
    val lowerPrice: Int,
    val auctionHighestBid: Int
)

// 상세보기용 데이터 클래스
data class AuctionBid(
    val auctionBidIdx: Long,
    val auctionBidPrice: Int,
    val auctionRegistedDate: String,
    val auctionBidStatus: String,
    val userIdx: Long,
    val auctionIdx: Long,
)

// 인터넷 미연결 샘플데이터
private fun getSampleData(): List<AuctionItemData> {
    return listOf(
        AuctionItemData(1,"https://via.placeholder.com/150", "Item1", "20231001235959", "20231001235959", true, 1000, 500, 800),
        AuctionItemData(2,"image2", "Item2", "20231002235959", "20231002235959", true, 2000, 1000, 1500),
        AuctionItemData(3,"image3", "Item3", "20231003235959", "20231003235959", false, 3000,2000, 2500),
        AuctionItemData(4,"image4", "Item4", "20231004235959", "20231004235959", false, 40000,2500, 3500),
        AuctionItemData(5,"image5", "Item5", "20231005235959", "20231005235959", true, 5000,3000, 4500)
    )
}