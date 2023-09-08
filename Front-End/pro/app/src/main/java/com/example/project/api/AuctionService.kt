package com.example.project.api

import com.example.project.viewmodels.ActuonVidData
import com.example.project.viewmodels.AuctionItemData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AuctionService {

    // 전체 목록 불러오는 API
    @POST("api/auction")
    suspend fun getAllAuctionItems(
        @Body filter: AuctionFilterDTO,
    ): Response<AuctionResponse>

    // 경매 등록 API
    @POST("api/auction/resist")
    suspend fun registerAuctionItem(
        @Body registerData: RegisterAuctionDTO
    ): Response<RegisterAuctionResponse>

    // 경매글 상세보기 API
    @GET("api/auction/detail/{auction_idx}")
    suspend fun getAuctionDetail(
        @Path("auction_idx") auction_idx: Long
    ): Response<AuctionDetailResponseDTO>

}

// 목록, 검색 요청 DTO
data class AuctionFilterDTO(
    val majorCategory: String,    // 대분류
    val minorCategory: String,    // 소분류
    val status: String,           // 상태
    val searchQuery: String? = null, // 검색 쿼리. 검색 API 사용 시에만 값이 있음.
    val page: Int                 // 페이지 정보
)

// 목록, 검색 응답 DTO
data class AuctionResponse(
    val total: Int,
    val items: List<AuctionItemData>
)

// 경매 등록 요청 DTO
data class RegisterAuctionDTO(
    val upperLimit: Int,         // 상한가
    val lowerLimit: Int,         // 하한가
    val postContent: String,     // 게시글 내용
    val gifticon_idx: Long,       // gifticon의 인덱스
    val user_idx: Int            // 사용자의 인덱스
)

// 경매 등록 응답 DTO
data class RegisterAuctionResponse(
    val success: Boolean,
    val message: String
)

// 경매글 상세보기 요청 DTO = Path형식
// 경매글 상세보기 응답 DTO
data class AuctionDetailResponseDTO(
    val postContent: String,              // 게시글 내용
    val auction_user_idx: Long,           // 게시글 유저 idx
    val auction_user_nickname: String,    // 게시글 유저 닉네임
    val actuon_vid: List<ActuonVidData>,  // 경매입찰정보
)