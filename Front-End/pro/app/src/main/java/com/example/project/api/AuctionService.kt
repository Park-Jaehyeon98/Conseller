package com.example.project.api

import com.example.project.viewmodels.AuctionItemData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

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
    val upperLimit: String,      // 상한가
    val lowerLimit: String,      // 하한가
    val postContent: String,     // 게시글 내용
    val gifticon_idx: Int,       // gifticon의 인덱스
    val user_idx: Int            // 사용자의 인덱스
)

// 경매 등록 응답 DTO
data class RegisterAuctionResponse(
    val success: Boolean,
    val message: String
)
