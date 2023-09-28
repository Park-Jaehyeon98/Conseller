package com.example.project.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.api.BarterConfirmList
import com.example.project.api.BarterConfirmPageResponseDTO
import com.example.project.api.BarterConfirmRequestDTO
import com.example.project.api.BarterCreateDTO
import com.example.project.api.BarterDetailResponseDTO
import com.example.project.api.BarterFilterDTO
import com.example.project.api.BarterService
import com.example.project.api.StoreConfirmPageResponseDTO
import com.example.project.api.StoreConfirmRequestDTO
import com.example.project.api.TradeBarterRequestDTO
import com.example.project.api.UpdateBarterDTO
import com.example.project.di.CustomException
import com.example.project.sharedpreferences.SharedPreferencesUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BarterViewModel @Inject constructor(
    private val service: BarterService,
    private val sharedPreferencesUtil: SharedPreferencesUtil
) : ViewModel() {

    private var currentPage = 1
    private var currentFilter = BarterFilterDTO(0, 0,0, null, currentPage)

    // 물물교환글 전체 목록 불러오기
    private val _barterItems = MutableStateFlow<List<BarterItemData>>(emptyList())
    val barterItems: StateFlow<List<BarterItemData>> = _barterItems

    private val _totalItems = MutableStateFlow<Int>(0)
    val totalItems: StateFlow<Int> = _totalItems

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // 유저idx 불러오기
    fun getUserIdFromPreference(): Long {
//        return sharedPreferencesUtil.getUserId()
        return 12345
    }

    // 물물교환글 상세보기
    private val _barterDetail = MutableStateFlow<BarterDetailResponseDTO?>(null)
    val barterDetail: StateFlow<BarterDetailResponseDTO?> = _barterDetail

    // 내 물물교환 글 목록
    private val _myBarterItems = MutableStateFlow<List<BarterItemData>>(emptyList())
    val myBarterItems: StateFlow<List<BarterItemData>> = _myBarterItems

    // 물물교환 등록후 barterIdx 받기
    private val _navigateToBarterDetail = MutableStateFlow<Long?>(null)
    val navigateToBarterDetail: StateFlow<Long?> = _navigateToBarterDetail

    // 물물교환 확정 페이지 데이터
    private val _barterConfirm = MutableStateFlow<BarterConfirmPageResponseDTO?>(null)
    val barterConfirm: StateFlow<BarterConfirmPageResponseDTO?> = _barterConfirm

    // 물물교환 확정 페이지 네비게이터
    private val _barterConfirmNavi = MutableStateFlow<Boolean?>(null)
    val barterConfirmNavi: StateFlow<Boolean?> = _barterConfirmNavi

    // 물물교환 제안취소 페이지 네비게이터
    private val _barterCancelNavi = MutableStateFlow<Boolean?>(null)
    val barterCancelNavi: StateFlow<Boolean?> = _barterCancelNavi




    // 물물교환 등록후 네비게이션 리셋
    fun resetNavigation() {
        _navigateToBarterDetail.value = null
    }


    fun changePage(page: Int) {
        currentPage = page
        currentFilter = currentFilter.copy(page = currentPage)
        fetchBarterItems()
    }

    fun applyFilter(filter: BarterFilterDTO) {
        currentFilter = filter.copy(page = 1)
        currentPage = 1 // 초기 페이지로 설정
        fetchBarterItems()
    }

    fun searchItems(query: String) {
        currentFilter = currentFilter.copy(searchQuery = query, page = 1)
        currentPage = 1
        fetchBarterItems()
    }

    // 물물교환 등록할때 이미지 불러오기용
    fun getSelectedItems(indices: List<Long>): List<BarterItemData> {
        return _barterItems.value.filter { it.barterIdx in indices }
    }

    // 물물교환 리스트 불러오기
    private fun fetchBarterItems() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.getAllBarterItems(currentFilter)

                if (response.isSuccessful && response.body() != null) {
                    _barterItems.value = response.body()!!.items
                    _totalItems.value = response.body()!!.totalNum
                }
            } catch (e: CustomException) {
                _error.value = e.message
                _barterItems.value = getSampleData()
            }catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _isLoading.value = false
            }
        }
    }

    // 물물교환 등록
    fun createBarterItem(kindBigStatus:String, kindSmallStatus:String, barterName:String, barterText:String, barterEndDate:String, selectedItemIndices: List<Long>) {
        val userIdx = sharedPreferencesUtil.getUserId()
        val kindBigStatus = kindBigStatus.toInt()
        val kindSmallStatus = kindSmallStatus.toInt()

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.createBarterItem(BarterCreateDTO(kindBigStatus,kindSmallStatus,barterName,barterText,barterEndDate,selectedItemIndices,userIdx))

                if (response.isSuccessful && response.body() != null) {
                    _navigateToBarterDetail.value = response.body()?.barterIdx
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

    // 물물교환 글 수정
    fun updateBarterItem(barterIdx: Long, kindBigStatus: String, kindSmallStatus: String, barterName: String, barterText: String, barterEndDate: String) {

        val kindBigStatus = kindBigStatus.toInt()
        val kindSmallStatus = kindSmallStatus.toInt()

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.updateBarterItem(barterIdx, UpdateBarterDTO(kindBigStatus,kindSmallStatus,barterName,barterText,barterEndDate))

                if (response.isSuccessful) {
                    _error.value = null
                    fetchBarterItems()
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

    // 물물교환 글 삭제
    fun deleteBarterItem(barterIdx: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.deleteBarterItem(barterIdx)

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

    // 물물교환 글 상세보기
    fun fetchBarterDetail(barterIdx: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.getBarterDetail(barterIdx)

                if (response.isSuccessful && response.body() != null) {
                    _barterDetail.value = response.body()
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

    // 물물교환 거래 제안
    fun proposeBarterTrade(barterIdx: Long, selectedItemIndices: List<Long>) {
        viewModelScope.launch {
            val userIdx = sharedPreferencesUtil.getUserId()
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.proposeBarterTrade(barterIdx,
                    TradeBarterRequestDTO(userIdx,selectedItemIndices)
                )

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

    // 물물교환 거래 제안 취소
    fun proposeCancleBarterTrade(barterRequestIdx: Long?) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            _barterCancelNavi.value = null
            try {
                val response = service.proposeCancleBarterTrade(barterRequestIdx!!)

                if (response.isSuccessful) {
                    _error.value = null
                    _barterCancelNavi.value = true
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

    // 물물교환 확정 페이지 불러오기
    fun fetchBarterConfirmItems(barterIdx: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.getBarterConfirm(barterIdx)

                if (response.isSuccessful && response.body() != null) {
                    _barterConfirm.value = response.body()
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

    // 물물교환 확정 페이지 확정하기
    fun barterConfirm(barterIdx: Long, userIdx:Long, confirm: Boolean) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = service.barterConfirm(BarterConfirmRequestDTO(barterIdx,userIdx,confirm))

                if (response.isSuccessful) {
                    _barterConfirm.value = null
                    _barterConfirmNavi.value = true
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
data class BarterItemData(
    val barterIdx: Long,
    val gifticonDataImageName: String,
    val giftconName: String,
    val gifticonEndDate: String,
    val barterEndDate: String,
    val isDeposit: Boolean,
    val preper: String,
    val barterName: String,
)

// 인터넷 미연결 샘플데이터
private fun getSampleData(): List<BarterItemData> {
    return listOf(
        BarterItemData(1,"image1", "Item1", "20231001235959", "20231001235959", true, "치킨","글1"),
        BarterItemData(2,"https://via.placeholder.com/150", "Item2", "20231002235959", "20231002235959", false, "커피","글2"),
        BarterItemData(3,"image3", "Item3", "20231003235959", "20231003235959", true, "피자","글3"),
        BarterItemData(4,"image4", "Item4", "20231004235959", "20231004235959", false, "물건","글4"),
        BarterItemData(5,"image5", "Item5", "20231005235959", "20231005235959", true, "등등","글5")
    )
}