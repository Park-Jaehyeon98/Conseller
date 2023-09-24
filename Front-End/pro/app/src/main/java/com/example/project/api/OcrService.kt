package com.example.project.api

import com.example.project.viewmodels.GifticonData
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*
import java.io.File
import java.util.Base64

interface OcrService {

    // 이미지 전송(OCR)
    @Multipart
    @POST("gifticon") //OCR URL 변경되어야함
    suspend fun uploadOcrGifticon(
        @Part("category") category: Int,
        @Part image : MultipartBody.Part
    ):Response<UploadGifticonResponse>





}





data class UploadGifticonResponse(
    val gitriconBarcode:String,
    val gifticonName:String,
    val gifticonEndData: String,
    val gifticonCropImage: String,
)