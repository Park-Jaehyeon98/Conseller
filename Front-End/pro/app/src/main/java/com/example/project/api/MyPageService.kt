package com.example.project.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface MyPageService {

    // 기프티콘 상세보기
    @GET("api/gifticon/{gifticonIdx}")
    suspend fun getUserGifticoninfo(
        @Path("gifticonIdx") gifticonIdx: Long,
    ): Response<myGifticon>
    // 기프티콘 전체 보기

    // 기프티콘 삭제(사용완료)
    @DELETE("api/gifticon/{gifticonIdx}")
    suspend fun getUserGifticonDelete(
        @Path("gifticonIdx") gifticonIdx: Long,
    ): Response<Void>
    // 기프티콘 전체 보기
    @GET("api/user/{userIdx}/gifticons")
    suspend fun getUserGifticon(
        @Path("userIdx") useridx: Long,
    ): Response<myGifticonResponse>
    // 내 스토어 보기
    @GET("api/user/{userIdx}/store")
    suspend fun getUserStore(
        @Path("userIdx") useridx: Long,
    ): Response<myStoreItems>
    // 내 경매 보기
    @GET("api/user/{userIdx}/auction")
    suspend fun getUserAuction(
        @Path("userIdx") useridx: Long,
    ): Response<myAuctionItems>
    // 내 입찰보기
    @GET("api/user/{userIdx}/auction-bid")
    suspend fun getUserAuctionBid(
        @Path("userIdx") useridx: Long,
    ): Response<myAuctionBidItems>
    // 내 물물교환 보기
    @GET("api/user/{userIdx}/barter")
    suspend fun getUserBarter(
        @Path("userIdx") useridx: Long,
    ): Response<myBarterItems>
    // 내 물물 교환 요청 보기
    @GET("api/user/{userIdx}/barter-request")
    suspend fun getUserBarterRequest(
        @Path("userIdx") useridx: Long,
    ): Response<myBarterRequestItems>



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
        @Part("gifticonPostRequest") gifticonPostRequest:RequestBody ,
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

    @DELETE("api/user/{userIdx}")
    suspend fun deleteUser(
        @Path("userIdx") userIdx:Long,
    ) :Response<Void>
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
    val userProfileUrl: String?,
    val userPassword: String,
    val userNickname: String,
    val userEmail: String,
    val userAccount: String,
    val userAccountBank: String,
    val userPhoneNumber:String
)

data class myGifticon(
    val gifticonIdx: Long,
    val gifticonBarcode: String,
    val gifticonName: String,
    val gifticonStartDate: String,
    val gifticonEndDate: String,
    val gifticonAllImageUrl: String,
    val gifticonDataImageUrl: String,
    val gifticonStatus: String, // 보관 // 경매//판매//물물교환
    val userIdx: Long,
    val subCategoryIdx: Int,
    val mainCategoryIdx: Int
)

data class myGifticonResponse(
    val items : List<myGifticon>
)

// 경매
data class myAuctionItems(
    val items : List<myAuctionData>
)
data class myAuctionData(
    val auctionIdx: Long,
    val gifticonDataImageName: String,
    val gifticonName: String,
    val gifticonEndDate: String,
    val auctionEndDate: String,
    val deposit: Boolean,
    val upperPrice: Int,
    val lowerPrice: Int,
    val auctionHighestBid: Int
)

data class myAuctionBidItems(
    val items: List<myAuctionBidData>
)

data class myAuctionBidData(
    val auctionBidIdx: Long,
    val auctionBidPrice: Int,
    val auctionRegistedDate: String,
    val auctionBidStatus: String,
    val userIdx: Long,
    val auctionIdx: Long,
    val gifticonDataImageName: String,
    val gifticonName: String,
    val gifticonEndDate: String,
    val auctionEndDate: String,
    val deposit: Boolean,
    val upperPrice: Int,
    val lowerPrice: Int,
    val auctionHighestBid: Int
)

data class myBarterItems(
    val items: List<myBarterData>
)
data class myBarterData(
    val barterIdx: Long,
    val barterName: String,
    val barterText: String,
    val barterCreatedDate: String,
    val barterEndDate: String,
    val barterStatus: String,
    val subCategory: String,
    val preferSubCategory: String, // 판매자가 원하는 거
    val barterHostIdx: Long,
    val barterCompleteGuest: Long,
    val barterHostItems: List<myGifticon>
)

data class myBarterRequestItems(
    val items:List<myBarterRequestData>
)
data class myBarterRequestData(
    val barterRequestIdx: Long,
    val barterRequestStatus: String,
    val barterIdx: Long,
    val barterGuestItems: List<myGifticon>
)

data  class myStoreItems(
    val items:List<myStoreData>
)
data class myStoreData(
    val storeIdx: Long,
    val gifticonDataImageName: String,
    val giftconName: String,
    val gifticonEndDate: String,
    val storeEndDate: String,
    val isDeposit: Boolean,
    val storePrice: Int,
)

