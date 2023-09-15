package com.example.project.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path

interface MyPageService {

//    @GET("api/user/auction/{useridx}/{status}")
//    suspend fun getUserAuction(
//        @Path("useridx") useridx: String,
//        @Path("status") status: String,
//    ): Response<RegistResponse>
//
//
//    @GET("api/user/barter/{useridx}/{status}")
//    suspend fun getUserBarter(
//        @Path("useridx") useridx: String,
//        @Path("status") status: String,
//    ): Response<RegistResponse>
//
//    @GET("api/user/store/{useridx}/{status}")
//    suspend fun getUserStore(
//        @Path("useridx") useridx: String,
//        @Path("status") status: String,
//    ): Response<RegistResponse>
//
//    @GET("api/user/gifticon/{useridx}/{status}")
//    suspend fun getUserGifticon(
//        @Path("useridx") useridx: String,
//        @Path("status") status: String,
//    ): Response<RegistResponse>

    // 이미지 업로드
    @Multipart
    @POST("api/user/profile")
    suspend fun profileSend(
        @Part("userIdx") userIdx: Long,
        @Part imageFile : MultipartBody.Part
    ):Response<uploadImageResponse>

    @Multipart
    @POST("api/gifticon")
    suspend fun uploadGifticon(
        @Part("userIdx") userIdx: Long,
        @Part imageFile : MultipartBody.Part
    ):Response<uploadImageResponse>


    @GET("api/user/{userIdx}/userinfo")
    suspend fun getUserInfo(
        @Path("userIdx") userIdx:Long,
    ) :Response<userInfoResponse>

    @POST("api/user/valid")
    suspend fun checkPwd(
        @Body request:userValidRequest
    ,) :Response<uploadImageResponse> // response가 똑같아서 활용

}


data class userValidRequest(
    val userIdx:String,
    val userPassword: String,
)


// 이미지 업로드 응답 DTO
data class uploadImageResponse(
    val status: Int,
    val message :String,
)

data class userInfoResponse(
    val userProfileUrl: String,
    val userPassword: String,
    val userNickname: String,
    val userEmail: String,
    val userAccount: String,
    val userAccountBank: String,
    val userPhoneNumber:String
)
