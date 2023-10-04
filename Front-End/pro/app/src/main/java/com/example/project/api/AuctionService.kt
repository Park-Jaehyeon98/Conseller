package com.example.project.api

import com.example.project.viewmodels.AuctionBid
import com.example.project.viewmodels.AuctionItemData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AuctionService {

    // 전체 목록 API
    @POST("api/auction")
    suspend fun getAllAuctionItems(
        @Body filter: AuctionFilterDTO,
    ): Response<AuctionResponse>

    // 경매 등록 API
    @POST("api/auction/regist")
    suspend fun registerAuctionItem(
        @Body registerData: RegisterAuctionDTO
    ): Response<RegisterAuctionResponse>

    // 경매글 수정 API
    @PATCH("api/auction/{auctionIdx}")
    suspend fun updateAuctionItem(
        @Path("auctionIdx") auctionIdx: Long,
        @Body updateData: UpdateAuctionDTO
    ): Response<Void>

    // 경매글 삭제 API
    @DELETE("api/auction/{auctionIdx}")
    suspend fun deleteAuctionItem(
        @Path("auctionIdx") auctionIdx: Long
    ): Response<Void>

    // 경매글 상세보기 API
    @GET("api/auction/{auctionIdx}")
    suspend fun getAuctionDetail(
        @Path("auctionIdx") auctionIdx: Long
    ): Response<AuctionDetailResponseDTO>

    // 경매 입찰하기 API
    @POST("api/auction/bid/{auctionIdx}")
    suspend fun bidOnAuction(
        @Path("auctionIdx") auctionIdx: Long,
        @Body bidRequest: AuctionBidRequestDTO
    ): Response<Void>


    // 경매진행 계좌번호 불러오기 API
    @GET("api/auction/trade/{auctionIdx}/{userIdx}")
    suspend fun getAuctionTrade(
        @Path("auctionIdx") auctionIdx: Long,
        @Path("userIdx") userIdx: Long,
    ): Response<AuctionTradeResponseDTO>

    // 경매진행 취소 API
    @PATCH("api/auction/cancel/{auctionIdx}")
    suspend fun cancelAuctionTrade(
        @Path("auctionIdx") auctionIdx: Long
    ): Response<Void>

    // 경매진행 입금완료 API
    @PATCH("api/auction/complete/{auctionIdx}")
    suspend fun completeAuctionPayment(
        @Path("auctionIdx") auctionIdx: Long
    ): Response<Void>


    // 내 경매 내역 불러오기 API
    @GET("api/user/{userIdx}/auction")
    suspend fun getUserAuction(
        @Path("userIdx") useridx: Long,
    ): Response<MyAuctionResponse>

    // 내 입찰 내역 불러오기 API
    @GET("api/user/{userIdx}/auction-bid")
    suspend fun getUserAuctionBid(
        @Path("userIdx") useridx: Long,
    ): Response<myAuctionBidItems>



    // 경매 판매자 입금 확정 페이지 요청
    @GET("api/auction/Confirm/{auctionIdx}")
    suspend fun getAuctionConfirm(
        @Path("auctionIdx") auctionIdx: Long,
    ): Response<AuctionConfirmPageResponseDTO>

    // 경매 판매자 입금 확정 요청
    @PATCH("api/auction/Confirm")
    suspend fun auctionConfirm(
        @Body confirmData: AuctionConfirmRequestDTO
    ): Response<Void>

    // 경매 입찰자 낙찰 페이지 요청
    @GET("api/auction/ConfirmBuy/{auctionIdx}")
    suspend fun getAuctionConfirmBuy(
        @Path("auctionIdx") auctionIdx: Long,
    ): Response<AuctionConfirmBuyPageResponseDTO>

    // 인기 경매 카테고리(Main)
    @GET("api/auction/category/main")
    suspend fun getPopularAuctionMain(
    ):Response<PopularCategory>

    //인기 경매 카테고리(Sub)
    @GET("api/auction/category/sub")
    suspend fun getPopularAuctionSub(
    ):Response<PopularCategory>

    //인기 경매
    @GET("api/auction/popular")
    suspend fun getPopularAuction(
    ):Response<MyAuctionResponse>









}

// 목록, 검색 요청 DTO
data class AuctionFilterDTO(
    val mainCategory: Int,    // 대분류
    val subCategory: Int,    // 소분류
    val status: Int,           // 상태
    val searchQuery: String? = null, // 검색 쿼리. 검색 API 사용 시에만 값이 있음.
    val page: Int                 // 페이지 정보
)

// 목록, 검색 응답 DTO
data class AuctionResponse(
    val totalElements: Long,
    val totalPages: Int,
    val items: List<AuctionItemData>
)

data class MyAuctionResponse(
    val items:List<AuctionItemData>
)

// 경매 등록 요청 DTO
data class RegisterAuctionDTO(
    val upperPrice: Int,         // 상한가
    val lowerPrice: Int,         // 하한가
    val auctionText: String,     // 게시글 내용
    val gifticonIdx: Long,       // gifticon의 인덱스
    val userIdx: Long            // 사용자의 인덱스
)

// 경매 등록 응답 DTO
data class RegisterAuctionResponse(
    val auctionIdx: Long,
)

// 경매 수정 요청 DTO
// 경매 수정 응답 DTO = http형식
data class UpdateAuctionDTO(
    val auctionEndDate: String,
    val auctionText: String,
)

// 경매 삭제 요청 DTO = Path형식
// 경매 삭제 응답 DTO = http형식


// 경매글 상세보기 요청 DTO = Path형식
// 경매글 상세보기 응답 DTO
data class AuctionDetailResponseDTO(
    val postContent: String,              // 게시글 내용
    val auctionUserIdx: Long,           // 게시글 유저 idx
    val auctionUserNickname: String,    // 게시글 유저 닉네임
    val auctionUserProfileUrl: String,  // 게시글 유저 사진
    val auctionUserDeposit: Long,       // 게시글 유저 보증금
    val auctionBidList: List<AuctionBid>,  // 경매입찰정보
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

// 경매 입찰하기 요청 DTO
// 경매 입찰하기 응답 DTO = http형식
data class AuctionBidRequestDTO(
    val userIdx: Long,
    val auctionBidPrice: Int,
)

// 경매 최고가 구매 계좌번호 요청 DTO = Path형식
// 경매 최고가 구매 계좌번호 응답 DTO
data class AuctionTradeResponseDTO(
    val success: Boolean,
    val message: String,
    val userAccount: String,
    val userAccountBank: String,
)

// 입금 완료 요청 DTO = Path형식
// 입금 완료 요청 DTO = Path형식




// 경매 판매자 입금 확정 페이지 요청 DTO = path 형식
// 경매 판매자 입금 확정 페이지 응답 DTO
data class AuctionConfirmPageResponseDTO(
    val gifticonDataImageName: String,
    val notificationCreatedDate: String,
    val giftconName: String,
    val auctionPrice: Int,
    val postContent: String,
    val buyUserImageUrl: String,
    val buyUserNickname: String,
    val buyUserIdx: Long
)

// 경매 판매자 입금 확정 페이지 확정 요청 DTO
// 경매 판매자 입금 확정 페이지 확정 응답 DTO = http 형식
data class AuctionConfirmRequestDTO(
    val auctionIdx: Long,
    val confirm: Boolean,
)



// 경매 입찰자 낙찰 페이지 요청 DTO = path 형식
// 경매 입찰자 낙찰 페이지 응답 DTO
data class AuctionConfirmBuyPageResponseDTO(
    val gifticonDataImageName: String,
    val giftconName: String,
    val auctionPrice: Int,
    val postContent: String,
    val userAccount: String,
    val userAccountBank: String,
    val buyUserImageUrl: String,
    val buyUserNickname: String,
    val buyUserIdx: Long
)





