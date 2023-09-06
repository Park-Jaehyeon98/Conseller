package com.example.project.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    // 패턴을 검증하기 위한 API
    @POST("user/verifypatternendpoint")
    suspend fun verifyPattern(@Body request: PatternVerificationRequest): Response<PatternVerifyResponse>

    // 여기부터 API 추가
}

// 패턴 검증 요청에 대한 DTO
data class PatternVerificationRequest(
    val userId: String,
    val pattern: String
)

// 패턴 검증 응답에 대한 DTO
data class PatternVerifyResponse(
    val success: Boolean,
    val message: String
)
