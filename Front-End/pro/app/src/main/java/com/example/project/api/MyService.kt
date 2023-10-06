package com.example.project.api

import com.example.project.viewmodels.GifticonData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface MyService {

    // 기프티콘 불러오는 API (페이지용)
    @POST("api/user/getgifticons")
    suspend fun getGifticons(
        @Body request: GifticonRequestDTO
    ): Response<GifticonPageResponse>

    @GET("api/notification/{userIdx}")
    suspend fun getMyNotifications(
        @Path("userIdx") userIdx: Long
    ): Response<NotificationsResponse>

    // 알람 버튼 선택값 보내기
    @PATCH("api/notification/{userIdx}")
    suspend fun submitNotificationAnswer(
        @Path("userIdx") userIdx: Long,
        @Body request: MyNotificationAnswerRequestDTO
    ): Response<Void>

    @GET("api/user/refresh/{userIdx}")
    fun refreshToken(@Path("userIdx") userIdx: Long): Call<RefreshResponse>
    
    // 신고하기 요청
    @POST("api/inquiry")
    suspend fun registInquiry(
        @Body request: RegistInquiryRequestDTO
    ): Response<Void>

    // 이벤트 요청
    @POST("api/event/{userIdx}")
    suspend fun firstEvent(
        @Path("userIdx") userIdx: Long,
    ): Response<Void>


}

// 기프티콘 목록 요청 DTO
data class GifticonRequestDTO(
    val userIdx: Long,
    val page: Int
)

// 기프티콘 목록 응답 DTO
data class GifticonPageResponse(
    val totalElements: Long,
    val totalPages: Int,
    val items: List<GifticonData>
)

// 경매 입찰 목록 요청 DTO = Path형식
// 경매 입찰 목록 응답 DTO
data class MyAuctionListResponseDTO(
    val auctionIdx: Long,                   // 경매idx
    val gifticonName: String,               // 기프티콘 이름
    val auctionBidPrice: String,            // 내 입찰가격
    val auctionHighestBid: Int,             // 현재 입찰가격
    val gifticonDataImageName: String,      // 기프티콘 이미지
)

// 알람 목록 요청 DTO = Path형식
// 알람 목록 응답 DTO
data class NotificationsResponse(
    val items: List<MyNotificationResponseDTO>
)
data class MyNotificationResponseDTO(
    val notificationIdx: Long,
    val notificationType: Int,
    val notificationCreatedDate: String,
    val notificationStatus: String,
)

// 알람 버튼 선택 요청 DTO
// 알람 버튼 선택 응답 DTO = http 방식
data class MyNotificationAnswerRequestDTO(
    val notificationIdx: Long,
    val notificationType: Int,
    val notificationAnswer: Boolean,
)

// 문의하기 요청 DTO
// 문의하기 응답 DTO = http 방식
data class RegistInquiryRequestDTO(
    val userIdx: Long,
    val inquiryTitle: String,
    val inquiryContent: String,
    val inquiryType: Int,
    val reportedUserIdx: Long,
)

// 이벤트 요청 DTO = Path 방식
// 이벤트 응답 DTO = http 방식


data class RefreshResponse(
    val accessToken: String
)
