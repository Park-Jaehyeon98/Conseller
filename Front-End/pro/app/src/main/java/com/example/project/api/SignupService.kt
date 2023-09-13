package com.example.project.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SignupService {

    //회원가입 API
    @POST("api/user/regist")
    suspend fun regist(@Body request:RegistRequest ): Response<RegistResponse>

    @GET("api/user/{userNickName}")
    suspend fun checkDuplicateNickName(
        @Path("userNickName") userNickName: String,
    ): Response<RegistResponse>


    @GET("api/user/{userEmail}")
    suspend fun checkDuplicateuEmail(
        @Path("userEmail") userEmail: String,
    ): Response<RegistResponse>

    @GET("api/user/{userId}")
    suspend fun checkDuplicateId(
        @Path("userId") userId: String,
    ): Response<RegistResponse>

    @GET("api/user/{userPhoneNumber}")
    suspend fun checkDuplicatePhoneNumber(
        @Path("userPhoneNumber") userPhoneNumber: String,
    ): Response<RegistResponse>




}


//회원가입 요청 DTO
data class RegistRequest(
    val userId: String,
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