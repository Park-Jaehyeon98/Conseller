package com.example.project.api;

import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J!\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\t\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J!\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u000b\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J!\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\r\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J!\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J!\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u0015H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J!\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u0019H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001b"}, d2 = {"Lcom/example/project/api/SignupService;", "", "checkDuplicateEmail", "Lretrofit2/Response;", "Lcom/example/project/api/BasicResponse;", "userEmail", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkDuplicateId", "userId", "checkDuplicateNickName", "userNickName", "checkDuplicatePhoneNumber", "userPhoneNumber", "findMyId", "Lcom/example/project/api/findIdResponse;", "request", "Lcom/example/project/api/findIdRequest;", "(Lcom/example/project/api/findIdRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findMyPwd", "Ljava/lang/Void;", "Lcom/example/project/api/findPwRequest;", "(Lcom/example/project/api/findPwRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "regist", "Lcom/example/project/api/RegistResponse;", "Lcom/example/project/api/RegistRequest;", "(Lcom/example/project/api/RegistRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public abstract interface SignupService {
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/user")
    public abstract java.lang.Object regist(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.RegistRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.RegistResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/user/nickname")
    public abstract java.lang.Object checkDuplicateNickName(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.String userNickName, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.BasicResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/user/email")
    public abstract java.lang.Object checkDuplicateEmail(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.String userEmail, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.BasicResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/user/id")
    public abstract java.lang.Object checkDuplicateId(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.String userId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.BasicResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/user/phone-number")
    public abstract java.lang.Object checkDuplicatePhoneNumber(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.String userPhoneNumber, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.BasicResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.PATCH(value = "api/user/encode/pw")
    public abstract java.lang.Object findMyPwd(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.findPwRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/user/encode/id")
    public abstract java.lang.Object findMyId(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.findIdRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.findIdResponse>> continuation);
}