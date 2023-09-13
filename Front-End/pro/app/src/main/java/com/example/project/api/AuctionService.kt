package com.example.project.api

import com.example.project.viewmodels.AuctionBidData
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

    // 내가 등록한 경매 목록 API
    @GET("api/auction/{userIdx}")
    suspend fun getMyAuctionItems(
        @Path("userIdx") userIdx: Long,
    ): Response<AuctionResponse>

    // 경매 등록 API
    @POST("api/auction/resist")
    suspend fun registerAuctionItem(
        @Body registerData: RegisterAuctionDTO
    ): Response<RegisterAuctionResponse>

    // 경매글 수정 API
    @PATCH("api/auction/update/{auctionIdx}")
    suspend fun updateAuctionItem(
        @Path("auctionIdx") auctionIdx: Long,
        @Body updateData: UpdateAuctionDTO
    ): Response<UpdateAuctionResponse>

    // 경매글 삭제 API
    @DELETE("api/auction/delete/{auctionIdx}")
    suspend fun deleteAuctionItem(
        @Path("auctionIdx") auctionIdx: Long
    ): Response<DeleteAuctionResponse>


    // 경매글 상세보기 API
    @GET("api/auction/detail/{auctionIdx}")
    suspend fun getAuctionDetail(
        @Path("auctionIdx") auctionIdx: Long
    ): Response<AuctionDetailResponseDTO>

}

// 목록, 검색 요청 DTO
data class AuctionFilterDTO(
    val kindBigStatus: String,    // 대분류
    val kindSmallStatus: String,    // 소분류
    val status: String,           // 상태
    val searchQuery: String? = null, // 검색 쿼리. 검색 API 사용 시에만 값이 있음.
    val page: Int                 // 페이지 정보
)

// 목록, 검색 응답 DTO
data class AuctionResponse(
    val totalNum: Int,
    val items: List<AuctionItemData>
)

// 경매 등록 요청 DTO
data class RegisterAuctionDTO(
    val upperLimit: Int,         // 상한가
    val lowerLimit: Int,         // 하한가
    val auctionText: String,     // 게시글 내용
    val gifticonIdx: Long,       // gifticon의 인덱스
    val userIdx: Long            // 사용자의 인덱스
)

// 경매 등록 응답 DTO
data class RegisterAuctionResponse(
    val success: Boolean,
    val message: String,
    val auctionIdx: Long,
)

// 경매 수정 요청 DTO
data class UpdateAuctionDTO(
    val auctionEndDate: String,
    val auctionText: String,
)

// 경매 수정 응답 DTO
data class UpdateAuctionResponse(
    val success: Boolean,
    val message: String,
)

// 경매 삭제 요청 DTO = Path형식
// 경매 삭제 응답 DTO
data class DeleteAuctionResponse(
    val success: Boolean,
    val message: String,
)

// 경매글 상세보기 요청 DTO = Path형식
// 경매글 상세보기 응답 DTO
data class AuctionDetailResponseDTO(
    val postContent: String,              // 게시글 내용
    val auctionUserIdx: Long,           // 게시글 유저 idx
    val auctionUserNickname: String,    // 게시글 유저 닉네임
    val auctionBid: List<AuctionBidData>,  // 경매입찰정보
)