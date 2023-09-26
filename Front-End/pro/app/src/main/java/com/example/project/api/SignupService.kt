package com.example.project.api

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface SignupService {

    //회원가입 API
    @POST("api/user")
    suspend fun regist(@Body request:RegistRequest ): Response<Void>

    @POST("api/user/nickname")
    suspend fun checkDuplicateNickName(
        @Body userNickName: String
    ): Response<BasicResponse>

    @POST("api/user/email")
    suspend fun checkDuplicateEmail(
        @Body userEmail: String
    ): Response<BasicResponse>

    @POST("api/user/id")
    suspend fun checkDuplicateId(
        @Body userId: String
    ): Response<BasicResponse>

    @POST("api/user/phone-number")
    suspend fun checkDuplicatePhoneNumber(
        @Body userPhoneNumber: String
    ): Response<BasicResponse>

    @PATCH("api/user/encode/pw")
    suspend fun findMyPwd(@Body request:findPwRequest):Response<Void>

    @POST("api/user/encode/id")
    suspend fun findMyId(@Body request:findIdRequest):Response<findIdResponse>






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
data class  BasicResponse( // 일반적인 결론일 경우
    val status: Int,
    val message :String,
)


data class findIdRequest(
    val userEmail:String,
    val userName:String,
)

data class findPwRequest(
    val userEmail:String,
    val userId:String,
)

data class findIdResponse(
    val userEncodeId:String
)
