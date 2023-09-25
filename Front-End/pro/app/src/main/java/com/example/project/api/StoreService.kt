package com.example.project.api

import com.example.project.viewmodels.StoreItemData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface StoreService {

    // 전체 목록 API
    @POST("api/store")
    suspend fun getAllStoreItems(
        @Body filter: StoreFilterDTO,
    ): Response<StoreResponse>

    // 내가 등록한 스토어 목록 API
    @GET("api/store/{userIdx}")
    suspend fun getMyStoreItems(
        @Path("userIdx") userIdx: Long,
    ): Response<StoreResponse>

    // 스토어 등록 API
    @POST("api/store/regist")
    suspend fun registerStoreItem(
        @Body registerData: RegisterStoreDTO
    ): Response<RegisterStoreResponse>

    // 스토어글 수정 API
    @PATCH("api/store/update/{storeIdx}")
    suspend fun updateStoreItem(
        @Path("storeIdx") storeIdx: Long,
        @Body updateData: UpdateStoreDTO
    ): Response<UpdateStoreResponse>

    // 스토어글 삭제 API
    @DELETE("api/store/delete/{storeIdx}")
    suspend fun deleteStoreItem(
        @Path("storeIdx") storeIdx: Long
    ): Response<DeleteStoreResponse>

    // 스토어글 상세보기 API
    @GET("api/store/detail/{storeIdx}")
    suspend fun getStoreDetail(
        @Path("storeIdx") storeIdx: Long
    ): Response<StoreDetailResponseDTO>

    // 스토어 거래진행 계좌번호 불러오기 API
    @GET("api/store/trade/{storeIdx}")
    suspend fun getStoreTrade(
        @Path("storeIdx") storeIdx: Long
    ): Response<StoreTradeResponseDTO>

    // 스토어 거래진행 취소 API
    @PATCH("api/store/cancel/{storeIdx}")
    suspend fun cancelStoreTrade(
        @Path("storeIdx") storeIdx: Long
    ): Response<Void>

    // 스토어 거래진행 입금완료 API
    @PATCH("api/store/complete/{storeIdx}")
    suspend fun completeStorePayment(
        @Path("storeIdx") storeIdx: Long
    ): Response<StoreTradeCompleteResponseDTO>

}

// 목록, 검색 요청 DTO
data class StoreFilterDTO(
    val mainCategory : Int,
    val subCategory : Int,
    val status: Int,
    val searchQuery: String? = null,
    val page: Int
)

// 목록, 검색 응답 DTO
data class StoreResponse(
    val totalElements: Long,
    val items: List<StoreItemData>
)

// 상점 등록 요청 DTO
data class RegisterStoreDTO(
    val storePrice: Int,
    val storeText: String,
    val gifticonIdx: Long,
    val userIdx: Long
)

// 상점 등록 응답 DTO
data class RegisterStoreResponse(
    val success: Boolean,
    val message: String,
    val storeIdx: Long,
)

// 상점 수정 요청 DTO
data class UpdateStoreDTO(
    val storeEndDate: String,
    val storeText: String,
)

// 상점 수정 응답 DTO
data class UpdateStoreResponse(
    val success: Boolean,
    val message: String,
)

// 상점 삭제 요청 DTO = Path형식
// 상점 삭제 응답 DTO
data class DeleteStoreResponse(
    val success: Boolean,
    val message: String,
)

// 상점글 상세보기 요청 DTO = Path형식
// 상점글 상세보기 응답 DTO
data class StoreDetailResponseDTO(
    val postContent: String,
    val storeUserIdx: Long,
    val storeUserNickname: String,
    val storeUserProfileUrl: String,
    val storeUserDeposit: Long,
)

// 스토어 최고가 구매 계좌번호 요청 DTO = Path형식
// 스토어 최고가 구매 계좌번호 응답 DTO
data class StoreTradeResponseDTO(
    val success: Boolean,
    val message: String,
    val userAccount: String,
    val userAccountBank: String,
)

// 스토어 완료 요청 DTO = Path형식
// 스토어 완료 요청 DTO

data class StoreTradeCompleteResponseDTO(
    val success: Boolean,
    val message: String,
)