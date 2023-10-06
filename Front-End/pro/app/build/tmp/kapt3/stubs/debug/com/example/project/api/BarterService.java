package com.example.project.api;

import com.example.project.viewmodels.BarterItemData;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J!\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ!\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J!\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00032\b\b\u0001\u0010\n\u001a\u00020\u0013H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J!\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00032\b\b\u0001\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J+\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u00032\b\b\u0001\u0010\u000e\u001a\u00020\u000f2\b\b\u0001\u0010\u0019\u001a\u00020\u000fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ+\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u000e\u001a\u00020\u000f2\b\b\u0001\u0010\u001f\u001a\u00020 H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!J!\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010#\u001a\u00020\u000fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J+\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u000e\u001a\u00020\u000f2\b\b\u0001\u0010%\u001a\u00020&H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\'\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006("}, d2 = {"Lcom/example/project/api/BarterService;", "", "barterConfirm", "Lretrofit2/Response;", "Ljava/lang/Void;", "confirmData", "Lcom/example/project/api/BarterConfirmRequestDTO;", "(Lcom/example/project/api/BarterConfirmRequestDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createBarterItem", "Lcom/example/project/api/CreateBarterResponse;", "filter", "Lcom/example/project/api/BarterCreateDTO;", "(Lcom/example/project/api/BarterCreateDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteBarterItem", "barterIdx", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllBarterItems", "Lcom/example/project/api/BarterResponse;", "Lcom/example/project/api/BarterFilterDTO;", "(Lcom/example/project/api/BarterFilterDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBarterConfirm", "Lcom/example/project/api/BarterConfirmPageResponseDTO;", "getBarterDetail", "Lcom/example/project/api/BarterDetailResponseDTO;", "userIdx", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPopularBarter", "Lcom/example/project/viewmodels/BarterItemData;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "proposeBarterTrade", "tradeRequest", "Lcom/example/project/api/TradeBarterRequestDTO;", "(JLcom/example/project/api/TradeBarterRequestDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "proposeCancleBarterTrade", "barterRequestIdx", "updateBarterItem", "updateData", "Lcom/example/project/api/UpdateBarterDTO;", "(JLcom/example/project/api/UpdateBarterDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface BarterService {
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/barter/all")
    public abstract java.lang.Object getAllBarterItems(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.BarterFilterDTO filter, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.BarterResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/barter/new")
    public abstract java.lang.Object createBarterItem(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.BarterCreateDTO filter, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.CreateBarterResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.PATCH(value = "api/barter/{barterIdx}")
    public abstract java.lang.Object updateBarterItem(@retrofit2.http.Path(value = "barterIdx")
    long barterIdx, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.UpdateBarterDTO updateData, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.DELETE(value = "api/barter/{barterIdx}")
    public abstract java.lang.Object deleteBarterItem(@retrofit2.http.Path(value = "barterIdx")
    long barterIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/barter/{barterIdx}/{userIdx}")
    public abstract java.lang.Object getBarterDetail(@retrofit2.http.Path(value = "barterIdx")
    long barterIdx, @retrofit2.http.Path(value = "userIdx")
    long userIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.BarterDetailResponseDTO>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/barterRequest/{barterIdx}")
    public abstract java.lang.Object proposeBarterTrade(@retrofit2.http.Path(value = "barterIdx")
    long barterIdx, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.TradeBarterRequestDTO tradeRequest, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.DELETE(value = "api/barterRequest/{barterRequestIdx}")
    public abstract java.lang.Object proposeCancleBarterTrade(@retrofit2.http.Path(value = "barterRequestIdx")
    long barterRequestIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/barter/Confirm/{barterIdx}")
    public abstract java.lang.Object getBarterConfirm(@retrofit2.http.Path(value = "barterIdx")
    long barterIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.BarterConfirmPageResponseDTO>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.PATCH(value = "api/barter/Confirm")
    public abstract java.lang.Object barterConfirm(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.BarterConfirmRequestDTO confirmData, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/barter/popular")
    public abstract java.lang.Object getPopularBarter(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.viewmodels.BarterItemData>> continuation);
}