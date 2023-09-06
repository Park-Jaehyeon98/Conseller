package com.example.project.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface EventService {

    // 여기부터 API 추가
    @POST("user/verifypatternendpoint")
    suspend fun verifyPattern(@Body request: PatternVerificationRequest): Response<PatternVerifyResponse>


}