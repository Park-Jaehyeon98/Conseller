package com.example.project.api;

import com.example.project.viewmodels.AuctionBid;
import com.example.project.viewmodels.AuctionItemData;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J+\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ!\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ!\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ!\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ!\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\b\b\u0001\u0010\u0014\u001a\u00020\u0015H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J!\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u00032\b\b\u0001\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ!\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00032\b\b\u0001\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ!\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00032\b\b\u0001\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ+\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00032\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u001f\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010#J\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010#J\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020%0\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010#J!\u0010\'\u001a\b\u0012\u0004\u0012\u00020\"0\u00032\b\b\u0001\u0010(\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ!\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u00032\b\b\u0001\u0010(\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ!\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u00032\b\b\u0001\u0010-\u001a\u00020.H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010/J+\u00100\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u00101\u001a\u000202H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00103\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00064"}, d2 = {"Lcom/example/project/api/AuctionService;", "", "auctionConfirm", "Lretrofit2/Response;", "Ljava/lang/Void;", "confirmData", "Lcom/example/project/api/AuctionConfirmRequestDTO;", "(Lcom/example/project/api/AuctionConfirmRequestDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "bidOnAuction", "auctionIdx", "", "bidRequest", "Lcom/example/project/api/AuctionBidRequestDTO;", "(JLcom/example/project/api/AuctionBidRequestDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancelAuctionTrade", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "completeAuctionPayment", "deleteAuctionItem", "getAllAuctionItems", "Lcom/example/project/api/AuctionResponse;", "filter", "Lcom/example/project/api/AuctionFilterDTO;", "(Lcom/example/project/api/AuctionFilterDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAuctionConfirm", "Lcom/example/project/api/AuctionConfirmPageResponseDTO;", "getAuctionConfirmBuy", "Lcom/example/project/api/AuctionConfirmBuyPageResponseDTO;", "getAuctionDetail", "Lcom/example/project/api/AuctionDetailResponseDTO;", "getAuctionTrade", "Lcom/example/project/api/AuctionTradeResponseDTO;", "userIdx", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPopularAuction", "Lcom/example/project/api/MyAuctionResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPopularAuctionMain", "Lcom/example/project/api/PopularCategory;", "getPopularAuctionSub", "getUserAuction", "useridx", "getUserAuctionBid", "Lcom/example/project/api/myAuctionBidItems;", "registerAuctionItem", "Lcom/example/project/api/RegisterAuctionResponse;", "registerData", "Lcom/example/project/api/RegisterAuctionDTO;", "(Lcom/example/project/api/RegisterAuctionDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAuctionItem", "updateData", "Lcom/example/project/api/UpdateAuctionDTO;", "(JLcom/example/project/api/UpdateAuctionDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface AuctionService {
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/auction")
    public abstract java.lang.Object getAllAuctionItems(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.AuctionFilterDTO filter, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.AuctionResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/auction/regist")
    public abstract java.lang.Object registerAuctionItem(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.RegisterAuctionDTO registerData, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.RegisterAuctionResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.PATCH(value = "api/auction/{auctionIdx}")
    public abstract java.lang.Object updateAuctionItem(@retrofit2.http.Path(value = "auctionIdx")
    long auctionIdx, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.UpdateAuctionDTO updateData, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.DELETE(value = "api/auction/{auctionIdx}")
    public abstract java.lang.Object deleteAuctionItem(@retrofit2.http.Path(value = "auctionIdx")
    long auctionIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/auction/{auctionIdx}")
    public abstract java.lang.Object getAuctionDetail(@retrofit2.http.Path(value = "auctionIdx")
    long auctionIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.AuctionDetailResponseDTO>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/auction/bid/{auctionIdx}")
    public abstract java.lang.Object bidOnAuction(@retrofit2.http.Path(value = "auctionIdx")
    long auctionIdx, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.AuctionBidRequestDTO bidRequest, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/auction/trade/{auctionIdx}/{userIdx}")
    public abstract java.lang.Object getAuctionTrade(@retrofit2.http.Path(value = "auctionIdx")
    long auctionIdx, @retrofit2.http.Path(value = "userIdx")
    long userIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.AuctionTradeResponseDTO>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.PATCH(value = "api/auction/cancel/{auctionIdx}")
    public abstract java.lang.Object cancelAuctionTrade(@retrofit2.http.Path(value = "auctionIdx")
    long auctionIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.PATCH(value = "api/auction/complete/{auctionIdx}")
    public abstract java.lang.Object completeAuctionPayment(@retrofit2.http.Path(value = "auctionIdx")
    long auctionIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/user/{userIdx}/auction")
    public abstract java.lang.Object getUserAuction(@retrofit2.http.Path(value = "userIdx")
    long useridx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.MyAuctionResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/user/{userIdx}/auction-bid")
    public abstract java.lang.Object getUserAuctionBid(@retrofit2.http.Path(value = "userIdx")
    long useridx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.myAuctionBidItems>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/auction/Confirm/{auctionIdx}")
    public abstract java.lang.Object getAuctionConfirm(@retrofit2.http.Path(value = "auctionIdx")
    long auctionIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.AuctionConfirmPageResponseDTO>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.PATCH(value = "api/auction/Confirm")
    public abstract java.lang.Object auctionConfirm(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.AuctionConfirmRequestDTO confirmData, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/auction/ConfirmBuy/{auctionIdx}")
    public abstract java.lang.Object getAuctionConfirmBuy(@retrofit2.http.Path(value = "auctionIdx")
    long auctionIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.AuctionConfirmBuyPageResponseDTO>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/auction/category/main")
    public abstract java.lang.Object getPopularAuctionMain(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.PopularCategory>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/auction/category/sub")
    public abstract java.lang.Object getPopularAuctionSub(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.PopularCategory>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/auction/popular")
    public abstract java.lang.Object getPopularAuction(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.MyAuctionResponse>> continuation);
}