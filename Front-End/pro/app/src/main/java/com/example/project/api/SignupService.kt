package com.example.project.api

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SignupService {

    //회원가입 API
    @POST("api/user")
    suspend fun regist(@Body request:RegistRequest ): Response<RegistResponse>

    @POST("api/user/nickname")
    suspend fun checkDuplicateNickName(
        @Body userNickName: String
    ): Response<RegistResponse>

    @POST("api/user/email")
    suspend fun checkDuplicateEmail(
        @Body userEmail: String
    ): Response<RegistResponse>

    @POST("api/user/id")
    suspend fun checkDuplicateId(
        @Body request: CheckuserIdRequest
    ): Response<RegistResponse>

    @POST("api/user/phone-number")
    suspend fun checkDuplicatePhoneNumber(
        @Body userPhoneNumber: String
    ): Response<RegistResponse>





}


//회원가입 요청 DTO
data class RegistRequest(
    val userId: String,
    val userGender: String, // F,M
    val userAge: Int,
    val userName:String,
    val userPassword: String,
    val userEmail: String,
    val userPhoneNumber: String,
    val userNickname: String,
    val userAccount: String,
    val userAccountBank: String
)

//회원가입 응답 DTO
data class  RegistResponse(
    val status: Int,
    val message :String,
)

data class CheckuserNicknameRequest(
    val userNickname:String
)

data class CheckuserEmailRequest(
    val userEmail:String
)

data class CheckuserIdRequest(
    val userId:String
)

data class CheckuserPhoneNumberRequest(
    val userPhoneNumber:String
)