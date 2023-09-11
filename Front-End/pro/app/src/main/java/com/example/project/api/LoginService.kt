package com.example.project.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    // 패턴을 검증하기 위한 API
    @POST("user/verifypatternendpoint")
    suspend fun verifyPattern(@Body request: PatternVerificationRequest): Response<PatternVerifyResponse>

    // 여기부터 API 추가
    // User IdPw API
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
    val user_id: String,
    val user_password: String
)

// IdPw로그인 응답 DTO
data class  IdPwLoginResponse(
    val user_idx:Long,
    val user_nickname :String,
    val user_accesstoken :String,
    val user_refreshtoken:String,
)