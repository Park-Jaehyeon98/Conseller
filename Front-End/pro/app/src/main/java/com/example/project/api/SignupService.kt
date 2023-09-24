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
    suspend fun regist(@Body request:RegistRequest ): Response<HttpResponse>

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
        @Body request: CheckuserIdRequest
    ): Response<BasicResponse>

    @POST("api/user/phone-number")
    suspend fun checkDuplicatePhoneNumber(
        @Body userPhoneNumber: String
    ): Response<BasicResponse>





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

data class HttpResponse(
    val code:Int,
    val message:String
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