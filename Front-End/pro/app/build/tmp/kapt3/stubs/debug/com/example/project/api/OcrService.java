package com.example.project.api;

import com.example.project.viewmodels.GifticonData;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.*;
import java.io.File;
import java.util.Base64;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J+\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\n"}, d2 = {"Lcom/example/project/api/OcrService;", "", "uploadOcrGifticon", "Lretrofit2/Response;", "Lcom/example/project/api/UploadGifticonResponse;", "category", "", "image", "Lokhttp3/MultipartBody$Part;", "(ILokhttp3/MultipartBody$Part;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface OcrService {
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "ocr/gifticon")
    @retrofit2.http.Multipart
    public abstract java.lang.Object uploadOcrGifticon(@retrofit2.http.Part(value = "category")
    int category, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Part
    okhttp3.MultipartBody.Part image, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.UploadGifticonResponse>> continuation);
}