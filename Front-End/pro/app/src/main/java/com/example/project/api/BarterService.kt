package com.example.project.api

import com.example.project.viewmodels.BarterItemData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface BarterService {

    // 전체 목록 불러오는 API
    @POST("barterItems/all")
    suspend fun getAllBarterItems(
        @Body filter: BarterFilterDTO,
    ): Response<BarterResponse>

    // 검색으로 불러오는 API
    @POST("barterItems/search")
    suspend fun searchBarterItems(
        @Body filter: BarterFilterDTO
    ): Response<BarterResponse>

    // 물물교환 등록 API
    @POST("barterItems/create")
    suspend fun createBarterItem(
        @Body filter: BarterCreateDTO
    ): Response<CreateBarterResponse>

}

// 목록, 검색 요청 DTO
data class BarterFilterDTO(
    val majorCategory: String,    // 대분류
    val minorCategory: String,    // 소분류
    val searchQuery: String? = null, // 검색 쿼리. 검색 API 사용 시에만 값이 있음.
    val page: Int                 // 페이지 정보
)

// 목록, 검색 응답 DTO
data class BarterResponse(
    val total: Int,
    val items: List<BarterItemData>
)

// 물물교환 등록 요청 DTO
data class BarterCreateDTO(
    val filter1: String,
    val filter2: String,
    val postTitle: String,
    val postContent: String,
    val selectedItemIndices: List<Long>,
)

// 물물교환 등록 응답 DTO
data class CreateBarterResponse(
    val success: Boolean,
    val message: String,
    val barter_idx: Int? = null // 생성된 물물교환 게시글의 idx주세요
)
