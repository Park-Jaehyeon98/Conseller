package com.example.project.api

import com.example.project.viewmodels.AuctionItemData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuctionService {

    // 전체 목록 불러오는 API
    @POST("auctionItems/all")
    suspend fun getAllAuctionItems(
        @Body filter: AuctionFilterDTO,
    ): Response<AuctionResponse>

    // 검색으로 불러오는 API
    @POST("auctionItems/search")
    suspend fun searchAuctionItems(
        @Body filter: AuctionFilterDTO
    ): Response<AuctionResponse>

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

