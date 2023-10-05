package com.example.project.api;

import com.example.project.viewmodels.GifticonData;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J!\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ!\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J!\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\n\u001a\u00020\u0013H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J+\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\u0016H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0018"}, d2 = {"Lcom/example/project/api/MyService;", "", "firstEvent", "Lretrofit2/Response;", "Ljava/lang/Void;", "userIdx", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getGifticons", "Lcom/example/project/api/GifticonPageResponse;", "request", "Lcom/example/project/api/GifticonRequestDTO;", "(Lcom/example/project/api/GifticonRequestDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMyNotifications", "Lcom/example/project/api/NotificationsResponse;", "refreshToken", "Lretrofit2/Call;", "Lcom/example/project/api/RefreshResponse;", "registInquiry", "Lcom/example/project/api/RegistInquiryRequestDTO;", "(Lcom/example/project/api/RegistInquiryRequestDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "submitNotificationAnswer", "Lcom/example/project/api/MyNotificationAnswerRequestDTO;", "(JLcom/example/project/api/MyNotificationAnswerRequestDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface MyService {
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/user/getgifticons")
    public abstract java.lang.Object getGifticons(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.GifticonRequestDTO request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.GifticonPageResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/notification/{userIdx}")
    public abstract java.lang.Object getMyNotifications(@retrofit2.http.Path(value = "userIdx")
    long userIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.NotificationsResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.PATCH(value = "api/notification/{userIdx}")
    public abstract java.lang.Object submitNotificationAnswer(@retrofit2.http.Path(value = "userIdx")
    long userIdx, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.MyNotificationAnswerRequestDTO request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "api/user/refresh/{userIdx}")
    public abstract retrofit2.Call<com.example.project.api.RefreshResponse> refreshToken(@retrofit2.http.Path(value = "userIdx")
    long userIdx);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/inquiry")
    public abstract java.lang.Object registInquiry(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.RegistInquiryRequestDTO request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/event/{userIdx}")
    public abstract java.lang.Object firstEvent(@retrofit2.http.Path(value = "userIdx")
    long userIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
}