package com.example.project.api

import com.example.project.viewmodels.ActuonVidData
import com.example.project.viewmodels.BarterItemData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BarterService {

    // 전체 목록 API
    @POST("barterItems/all")
    suspend fun getAllBarterItems(
        @Body filter: BarterFilterDTO,
    ): Response<BarterResponse>

    // 내가 등록한 물물 목록 API
    @GET("api/barter/{userIdx}")
    suspend fun getMyBarterItems(
        @Path("userIdx") userIdx: Long,
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

    // 물물교환 상세보기 API
    @GET("api/batter/detail/{batterIdx}")
    suspend fun getAuctionDetail(
        @Path("batterIdx") batterIdx: Long
    ): Response<BarterDetailResponseDTO>

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
    val kindBigStatus: String,
    val kindSmallStatus: String,
    val barterName: String,
    val barterText: String,
    val selectedItemIndices: List<Long>,
)

// 물물교환 등록 응답 DTO
data class CreateBarterResponse(
    val success: Boolean,
    val message: String,
    val barterIdx: Int? = null // 생성된 물물교환 게시글의 idx주세요
)

// 물물교환 상세보기 요청 DTO = Path형식
// 물물교환 상세보기 응답 DTO
data class BarterDetailResponseDTO(
    val barterName: String,                // 게시글 제목
    val barterText: String,              // 게시글 내용
    val barterUserIdx: Long,           // 게시글 유저 idx
    val barterUserNickname: String,    // 게시글 유저 닉네임
)