package com.example.project.api

import com.example.project.viewmodels.GifticonData
import retrofit2.Response
import retrofit2.http.*

interface MyService {

    // 기프티콘 불러오는 API
    @POST("user/getgifticons/")
    suspend fun getGifticons(
        @Body request: GifticonRequestDTO
    ): Response<GifticonResponse>

    // 내 경매 "입찰" 목록 불러오기
    @GET("user/auctions/bid/{userIdx}")
    suspend fun getMyAuctions(
        @Path("userIdx") userIdx: Long
    ): Response<List<MyAuctionListResponseDTO>>

    // 내 알람 목록 불러오기
    @GET("user/notifications/{userIdx}")
    suspend fun getMyNotifications(
        @Path("userIdx") userIdx: Long
    ): Response<List<MyNotificationResponseDTO>>


}

// 기프티콘 목록 요청 DTO
data class GifticonRequestDTO(
    val userIdx: Long,
    val page: Int
)

// 기프티콘 목록 응답 DTO
data class GifticonResponse(
    val total: Long,
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
data class MyNotificationResponseDTO(
    val notificationIdx: Long,
    val notificationCreatedDate: String,
    val notificationStatus: String,
)