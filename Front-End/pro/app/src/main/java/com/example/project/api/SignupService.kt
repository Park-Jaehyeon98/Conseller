package com.example.project.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupService {

    //회원가입 API
    @POST("api/user/regist")
    suspend fun verifyPattern(@Body request:RegistRequest ):
            Response<RegistResponse>



}


//회원가입 요청 DTO
data class RegistRequest(
    val userId: String,
    val userPassword: String,
    val userEmail: String,
    val userPhoneNumber: Number,
    val userNickname: String,
    val userAccount: String,
    val userAccountBank: Number
)

//회원가입 응답 DTO
data class  RegistResponse(
    val status:String,
    val message :String,
)