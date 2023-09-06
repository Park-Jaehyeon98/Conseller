package com.example.project.api

import com.example.project.viewmodels.GifticonData
import retrofit2.Response
import retrofit2.http.*

interface MyService {

    // 기프티콘 불러오는 API
    @POST("user/getgifticons/")
    suspend fun getGifticons(
        @Body request: GifticonRequestDTO
    ): Response<GifticonResponse>

    // 여기부터 다른 API 추가

}

// Request DTO
data class GifticonRequestDTO(
    val user_idx: Int,
    val page: Int
)

// 목록, 검색 응답 DTO
data class GifticonResponse(
    val total: Int,
    val items: List<GifticonData>
)
