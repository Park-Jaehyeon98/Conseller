package com.example.project.api

import com.example.project.viewmodels.GifticonData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import java.io.File
import java.util.Base64

interface OcrService {

    // 이미지 전송(OCR)
    @Multipart
    @POST("ocr/gifticon")
    suspend fun uploadOcrGifticon(
        @Part("category") category: Int,
        @Part image : MultipartBody.Part,
    ): Response<UploadGifticonResponse>





}

data class ocrCategoryRequest(
    val category: Int
)


data class UploadGifticonResponse(
    val gifticonBarcode:String,
    val gifticonName:String,
    val gifticonEndDate: String,
    val gifticonCropImage: String,
)