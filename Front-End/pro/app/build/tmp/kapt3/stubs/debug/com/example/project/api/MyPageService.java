package com.example.project.api;

import com.example.project.viewmodels.AuctionItemData;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J!\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ!\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\b\b\u0001\u0010\u000f\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ!\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\b\b\u0001\u0010\u000f\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ!\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\b\b\u0001\u0010\u000f\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ!\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00032\b\b\u0001\u0010\u000f\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ!\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u00032\b\b\u0001\u0010\u000f\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ!\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\u0019\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ!\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00032\b\b\u0001\u0010\u0019\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ!\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00032\b\b\u0001\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ!\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00032\b\b\u0001\u0010\u000f\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ!\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u00032\b\b\u0001\u0010\u000f\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ+\u0010\"\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\u0005\u001a\u00020#H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010$J+\u0010%\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010&\u001a\u00020\'H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010(J?\u0010)\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010*\u001a\u00020+2\b\b\u0001\u0010,\u001a\u00020\'2\b\b\u0001\u0010-\u001a\u00020\'H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010.\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006/"}, d2 = {"Lcom/example/project/api/MyPageService;", "", "checkUserValid", "Lretrofit2/Response;", "Lcom/example/project/api/uploadImageResponse;", "request", "Lcom/example/project/api/userValidRequest;", "(Lcom/example/project/api/userValidRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteUser", "Ljava/lang/Void;", "userIdx", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserAuction", "Lcom/example/project/api/myAuctionItems;", "useridx", "getUserAuctionBid", "Lcom/example/project/api/myAuctionBidItems;", "getUserBarter", "Lcom/example/project/api/myBarterItems;", "getUserBarterRequest", "Lcom/example/project/api/myBarterRequestItems;", "getUserGifticon", "Lcom/example/project/api/myGifticonResponse;", "getUserGifticonDelete", "gifticonIdx", "getUserGifticoninfo", "Lcom/example/project/api/myGifticon;", "getUserInfo", "Lcom/example/project/api/userInfoResponse;", "getUserPurchase", "Lcom/example/project/api/myPurchaseItems;", "getUserStore", "Lcom/example/project/api/myStoreItems;", "modifyUserInfo", "Lcom/example/project/api/userModifyRequest;", "(JLcom/example/project/api/userModifyRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "profileSend", "file", "Lokhttp3/MultipartBody$Part;", "(JLokhttp3/MultipartBody$Part;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadgifiticon", "gifticonPostRequest", "Lokhttp3/RequestBody;", "originalFile", "cropFile", "(JLokhttp3/RequestBody;Lokhttp3/MultipartBody$Part;Lokhttp3/MultipartBody$Part;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface MyPageService {
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/gifticon/{gifticonIdx}")
    public abstract java.lang.Object getUserGifticoninfo(@retrofit2.http.Path(value = "gifticonIdx")
    long gifticonIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.myGifticon>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.DELETE(value = "api/gifticon/{gifticonIdx}")
    public abstract java.lang.Object getUserGifticonDelete(@retrofit2.http.Path(value = "gifticonIdx")
    long gifticonIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/user/{userIdx}/gifticons")
    public abstract java.lang.Object getUserGifticon(@retrofit2.http.Path(value = "userIdx")
    long useridx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.myGifticonResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/user/{userIdx}/store")
    public abstract java.lang.Object getUserStore(@retrofit2.http.Path(value = "userIdx")
    long useridx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.myStoreItems>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/user/{userIdx}/store/purchase")
    public abstract java.lang.Object getUserPurchase(@retrofit2.http.Path(value = "userIdx")
    long useridx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.myPurchaseItems>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/user/{userIdx}/auction")
    public abstract java.lang.Object getUserAuction(@retrofit2.http.Path(value = "userIdx")
    long useridx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.myAuctionItems>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/user/{userIdx}/auction-bid")
    public abstract java.lang.Object getUserAuctionBid(@retrofit2.http.Path(value = "userIdx")
    long useridx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.myAuctionBidItems>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/user/{userIdx}/barter")
    public abstract java.lang.Object getUserBarter(@retrofit2.http.Path(value = "userIdx")
    long useridx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.myBarterItems>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/user/{userIdx}/barter-request")
    public abstract java.lang.Object getUserBarterRequest(@retrofit2.http.Path(value = "userIdx")
    long useridx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.myBarterRequestItems>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/image/{userIdx}/profile")
    @retrofit2.http.Multipart
    public abstract java.lang.Object profileSend(@retrofit2.http.Path(value = "userIdx")
    long userIdx, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Part
    okhttp3.MultipartBody.Part file, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/gifticon/{userIdx}")
    @retrofit2.http.Multipart
    public abstract java.lang.Object uploadgifiticon(@retrofit2.http.Path(value = "userIdx")
    long userIdx, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Part(value = "gifticonPostRequest")
    okhttp3.RequestBody gifticonPostRequest, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Part
    okhttp3.MultipartBody.Part originalFile, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Part
    okhttp3.MultipartBody.Part cropFile, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/user/{userIdx}/userInfo")
    public abstract java.lang.Object getUserInfo(@retrofit2.http.Path(value = "userIdx")
    long userIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.userInfoResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.POST(value = "api/user/valid")
    public abstract java.lang.Object checkUserValid(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.userValidRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.example.project.api.uploadImageResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.PUT(value = "api/user/{userIdx}")
    public abstract java.lang.Object modifyUserInfo(@retrofit2.http.Path(value = "userIdx")
    long userIdx, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.example.project.api.userModifyRequest request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.DELETE(value = "api/user/{userIdx}")
    public abstract java.lang.Object deleteUser(@retrofit2.http.Path(value = "userIdx")
    long userIdx, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Void>> continuation);
}