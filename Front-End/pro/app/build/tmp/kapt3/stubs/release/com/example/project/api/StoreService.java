package com.example.project.api;

import com.example.project.viewmodels.StoreItemData;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J!\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J!\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J!\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\b\b\u0001\u0010\f\u001a\u00020\rH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ!\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00120\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J!\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J!\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J+\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0010\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001bJ!\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00032\b\b\u0001\u0010\u001e\u001a\u00020\u001fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J!\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\"\u001a\u00020#H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010$J+\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010&\u001a\u00020\'H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010(\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006)"}, d2 = {"Lcom/example/project/api/StoreService;", "", "cancelStoreTrade", "Lretrofit2/Response;", "Ljava/lang/Void;", "storeIdx", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "completeStorePayment", "deleteStoreItem", "getAllStoreItems", "Lcom/example/project/api/StoreResponse;", "filter", "Lcom/example/project/api/StoreFilterDTO;", "(Lcom/example/project/api/StoreFilterDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMyStoreItems", "userIdx", "getPopularStoreMain", "Lcom/example/project/api/PopularCategory;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPopularStoreSub", "getStoreConfirm", "Lcom/example/project/api/StoreConfirmPageResponseDTO;", "getStoreDetail", "Lcom/example/project/api/StoreDetailResponseDTO;", "getStoreTrade", "Lcom/example/project/api/StoreTradeResponseDTO;", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerStoreItem", "Lcom/example/project/api/RegisterStoreResponse;", "registerData", "Lcom/example/project/api/RegisterStoreDTO;", "(Lcom/example/project/api/RegisterStoreDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "storeConfirm", "confirmData", "Lcom/example/project/api/StoreConfirmRequestDTO;", "(Lcom/example/project/api/StoreConfirmRequestDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateStoreItem", "updateData", "Lcom/example/project/api/UpdateStoreDTO;", "(JLcom/example/project/api/UpdateStoreDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public abstract interface StoreService {
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/store")
    public abstract java.lang.Object getAllStoreItems(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.StoreFilterDTO filter, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.StoreResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/store/{userIdx}")
    public abstract java.lang.Object getMyStoreItems(@retrofit2.http.Path(value = "userIdx")
    long userIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.StoreResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/store/regist")
    public abstract java.lang.Object registerStoreItem(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.RegisterStoreDTO registerData, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.RegisterStoreResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.PATCH(value = "api/store/{storeIdx}")
    public abstract java.lang.Object updateStoreItem(@retrofit2.http.Path(value = "storeIdx")
    long storeIdx, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.UpdateStoreDTO updateData, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.DELETE(value = "api/store/{storeIdx}")
    public abstract java.lang.Object deleteStoreItem(@retrofit2.http.Path(value = "storeIdx")
    long storeIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/store/{storeIdx}")
    public abstract java.lang.Object getStoreDetail(@retrofit2.http.Path(value = "storeIdx")
    long storeIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.StoreDetailResponseDTO>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/store/trade/{storeIdx}/{userIdx}")
    public abstract java.lang.Object getStoreTrade(@retrofit2.http.Path(value = "storeIdx")
    long storeIdx, @retrofit2.http.Path(value = "userIdx")
    long userIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.StoreTradeResponseDTO>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.PATCH(value = "api/store/cancel/{storeIdx}")
    public abstract java.lang.Object cancelStoreTrade(@retrofit2.http.Path(value = "storeIdx")
    long storeIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.PATCH(value = "api/store/complete/{storeIdx}")
    public abstract java.lang.Object completeStorePayment(@retrofit2.http.Path(value = "storeIdx")
    long storeIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/store/Confirm/{storeIdx}")
    public abstract java.lang.Object getStoreConfirm(@retrofit2.http.Path(value = "storeIdx")
    long storeIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.StoreConfirmPageResponseDTO>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.PATCH(value = "api/store/Confirm")
    public abstract java.lang.Object storeConfirm(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.StoreConfirmRequestDTO confirmData, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/store/category/main")
    public abstract java.lang.Object getPopularStoreMain(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.PopularCategory>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/store/category/sub")
    public abstract java.lang.Object getPopularStoreSub(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.PopularCategory>> continuation);
}