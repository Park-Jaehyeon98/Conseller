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
    @PATCH("api/store/{storeIdx}")
    suspend fun updateStoreItem(
        @Path("storeIdx") storeIdx: Long,
        @Body updateData: UpdateStoreDTO
    ): Response<Void>

    // 스토어글 삭제 API
    @DELETE("api/store/{storeIdx}")
    suspend fun deleteStoreItem(
        @Path("storeIdx") storeIdx: Long
    ): Response<Void>

    // 스토어글 상세보기 API
    @GET("api/store/{storeIdx}")
    suspend fun getStoreDetail(
        @Path("storeIdx") storeIdx: Long
    ): Response<StoreDetailResponseDTO>

    // 스토어 거래진행 계좌번호 불러오기 API
    @GET("api/store/trade/{storeIdx}/{userIdx}")
    suspend fun getStoreTrade(
        @Path("storeIdx") storeIdx: Long,
        @Path("userIdx") userIdx: Long
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
    ): Response<Void>


    // 스토어 판매자 입금 확정 페이지 요청
    @GET("api/store/Confirm/{storeIdx}")
    suspend fun getStoreConfirm(
        @Path("storeIdx") storeIdx: Long,
    ): Response<StoreConfirmPageResponseDTO>

    // 스토어 판매자 입금 확정 요청
    @PATCH("api/store/Confirm")
    suspend fun storeConfirm(
        @Body confirmData: StoreConfirmRequestDTO
    ): Response<Void>

    //인기 판매 카테고리(Main)
    @GET("api/store/category/main")
    suspend fun getPopularStoreMain(
    ):Response<PopularCategory>

    //인기 판매 카테고리(Sub)
    @GET("api/store/category/sub")
    suspend fun getPopularStoreSub(
    ):Response<PopularCategory>


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
    val totalPages: Int,
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
    val storeIdx: Long,
)

// 상점 수정 요청 DTO
// 상점 수정 응답 DTO = http형식
data class UpdateStoreDTO(
    val storePrice: Int,
    val storeText: String,
)

// 상점 삭제 요청 DTO = Path형식
// 상점 삭제 응답 DTO = http형식


// 상점글 상세보기 요청 DTO = Path형식
// 상점글 상세보기 응답 DTO
data class StoreDetailResponseDTO(
    val postContent: String,
    val storeUserIdx: Long,
    val storeUserNickname: String,
    val storeUserProfileUrl: String,
    val storeUserDeposit: Long,
    val storeIdx: Long,
    val gifticonDataImageName: String,
    val gifticonName: String,
    val gifticonEndDate: String,
    val storeEndDate: String,
    val deposit: Boolean,
    val storePrice: Int,
)

// 스토어 최고가 구매 계좌번호 요청 DTO = Path형식
// 스토어 최고가 구매 계좌번호 응답 DTO
data class StoreTradeResponseDTO(
    val userAccount: String,
    val userAccountBank: String,
    val userName:String,
)

// 스토어 완료 요청 DTO = Path형식
// 스토어 완료 요청 DTO = http형식





// 스토어 판매자 입금 확정 페이지 요청 DTO = path 형식
// 스토어 판매자 입금 확정 페이지 응답 DTO
data class StoreConfirmPageResponseDTO(
    val gifticonDataImageName: String,
    val notificationCreatedDate: String,
    val giftconName: String,
    val storePrice: Int,
    val postContent: String,
    val buyUserImageUrl: String,
    val buyUserNickname: String,
    val buyUserIdx: Long,
    val buyUserName:String
)

// 스토어 판매자 입금 확정 페이지 확정 요청 DTO
// 스토어 판매자 입금 확정 페이지 확정 요청 DTO = http 형식
data class StoreConfirmRequestDTO(
    val storeIdx: Long,
    val confirm: Boolean,
)


data class PopularCategory(
    val items: List<Int>
)





