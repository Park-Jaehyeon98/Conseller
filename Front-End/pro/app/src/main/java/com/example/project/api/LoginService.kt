package com.example.project.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    // 패턴을 검증하기 위한 API
    @POST("user/verifypatternendpoint")
    suspend fun verifyPattern(@Body request: PatternVerificationRequest): Response<PatternVerifyResponse>


    // User 일반로그인
    @POST("api/user/login")
    suspend fun textLogin(@Body request:IdPwLoginRequest ): Response<IdPwLoginResponse>

}

// 패턴 검증 요청에 대한 DTO
data class PatternVerificationRequest(
    val userIdx: Long,
    val pattern: String
)

// 패턴 검증 응답에 대한 DTO
data class PatternVerifyResponse(
    val success: Boolean,
    val message: String
)

// IdPw로그인 요청 DTO
data class IdPwLoginRequest(
    val userId: String,
    val userPassword: String
)

// IdPw로그인 응답 DTO
data class  IdPwLoginResponse(
    val userIdx:Long,
    val userNickname :String,
    val accessToken :String,
    val refreshToken:String,
)