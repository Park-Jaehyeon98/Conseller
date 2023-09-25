package com.example.project.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    // 패턴을 검증하기 위한 API
    @POST("user/verifypatternendpoint")
    suspend fun verifyPattern(@Body request: PatternVerificationRequest): Response<PatternVerifyResponse>

    // 패턴을 저장하기 위한 API
    @POST("user/savepatternendpoint")
    suspend fun savePattern(
        @Body request: PatternSaveRequest
    ): Response<PatternSaveResponse>

    // User 일반로그인
    @POST("api/user/login")
    suspend fun textLogin(@Body request:IdPwLoginRequest ): Response<IdPwLoginResponse>

    // firebase 토큰
    @POST("api/user/firebaseToken")
    fun sendToken(@Body token: firebaseToken): Call<Void>

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

// 패턴 저장 요청 DTO
data class PatternSaveRequest(
    val userIdx: Long,
    val pattern: String,
)
// 패턴 저장 응답 DTO
data class PatternSaveResponse(
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
// firebase 토큰
data class firebaseToken(
    val firebaseToken: String
)