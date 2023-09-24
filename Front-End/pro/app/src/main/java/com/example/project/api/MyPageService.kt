package com.example.project.api

import android.net.Uri
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.intellij.lang.annotations.JdkConstants.BoxLayoutAxis
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Path

interface MyPageService {

    @GET("/api/user/{userIdx}/store")
    suspend fun getUserStore(
        @Path("useridx") useridx: String,
    ): Response<Void>


    @GET("/api/user/{userIdx}/auction-bid")
    suspend fun getUserAuctionBid(
        @Path("useridx") useridx: String,
    ): Response<Void>

    @GET("/api/user/{userIdx}/auction")
    suspend fun getUserAuction(
        @Path("useridx") useridx: String,
    ): Response<Void>

    @GET("/api/user/{userIdx}/barter")
    suspend fun getUserBarter(
        @Path("useridx") useridx: String,
    ): Response<Void>

    @GET("/api/user/{userIdx}/barter-request")
    suspend fun getUserBarterRequest(
        @Path("useridx") useridx: String,
    ): Response<Void>



    // 이미지 업로드
    @Multipart
    @POST("api/image/{userIdx}/profile")
    suspend fun profileSend(
        @Path("userIdx") userIdx: Long,
        @Part file : MultipartBody.Part
    ):Response<Void>
    //기프티콘 업로드
    @Multipart
    @POST("api/gifticon/{userIdx}")
    suspend fun uploadgifiticon(
        @Path("userIdx") userIdx:Long,
        @Part request:  MultipartBody.Part,
        @Part originalFile : MultipartBody.Part,
        @Part cropFile : MultipartBody.Part
    ):Response<Void>

    // 유저 정보 확인
    @GET("api/user/{userIdx}/userInfo")
    suspend fun getUserInfo(
        @Path("userIdx") userIdx:Long,
    ) :Response<userInfoResponse>

    // 유저 비밀번호 확인
    @POST("api/user/valid")
    suspend fun checkUserValid(
        @Body request:userValidRequest
    ,) :Response<uploadImageResponse> // response가 똑같아서 활용
    @PUT("api/user/{userIdx}")
    suspend fun modifyUserInfo(
        @Path("userIdx") userIdx:Long,
        @Body request: userModifyRequest
    ) :Response<userModifyResponse>
}

data class userUploadGifticonResponse(
    val gifticonBarcode: String,
    val gifticonName: String,
    val gifticonEndDate: String,
    val subCategory: Int,
    val mainCategory: Int
)

data class userModifyRequest(
    val userNickname: String,
    val userPassword: String,
    val userEmail: String,
    val userAccount: String,
    val userAccountBank: String
)

data class userModifyResponse(
    val code:String,
    val message:String,
)
data class userValidRequest(
    val userIdx:Long,
    val userPassword: String,
)


// 이미지 업로드 응답 DTO
data class uploadImageResponse(
    val status: Int,
    val message :String,
)

data class userInfoResponse(
    val userProfileUrl: Uri?=null,
    val userPassword: String,
    val userNickname: String,
    val userEmail: String,
    val userAccount: String,
    val userAccountBank: String,
    val userPhoneNumber:String
)
