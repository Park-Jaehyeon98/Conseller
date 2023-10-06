package com.example.project.viewmodels;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.example.project.api.MyPageService;
import com.example.project.api.myAuctionBidData;
import com.example.project.api.myAuctionData;
import com.example.project.api.myBarterData;
import com.example.project.api.myBarterRequestData;
import com.example.project.api.myGifticon;
import com.example.project.api.myPurchaseData;
import com.example.project.api.myStoreData;
import com.example.project.api.userInfoResponse;
import com.example.project.api.userModifyRequest;
import com.example.project.api.userValidRequest;
import com.example.project.di.CustomException;
import com.example.project.sharedpreferences.SharedPreferencesUtil;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020JJ\u0006\u0010K\u001a\u00020HJ\u0006\u0010L\u001a\u00020HJ\u0006\u0010M\u001a\u00020HJ\u0006\u0010N\u001a\u00020HJ\u0006\u0010O\u001a\u00020HJ\u0006\u0010P\u001a\u00020HJ\u0006\u0010Q\u001a\u00020HJ\u0006\u0010R\u001a\u00020HJ\u000e\u0010S\u001a\u00020H2\u0006\u0010I\u001a\u00020JJ\u0006\u0010T\u001a\u00020JJ\u001e\u0010U\u001a\u00020H2\u0006\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020YJ\u000e\u0010[\u001a\u00020H2\u0006\u0010\\\u001a\u00020YJ\u0006\u0010]\u001a\u00020HJ\u000e\u0010^\u001a\u00020H2\u0006\u0010V\u001a\u00020_J\u000e\u0010`\u001a\u00020H2\u0006\u0010V\u001a\u00020aR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\t0%8F\u00a2\u0006\u0006\u001a\u0004\b&\u0010\'R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\t0%8F\u00a2\u0006\u0006\u001a\u0004\b)\u0010\'R\u0019\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0%\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\'R\u001d\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\f0%8F\u00a2\u0006\u0006\u001a\u0004\b-\u0010\'R\u001d\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0%8F\u00a2\u0006\u0006\u001a\u0004\b/\u0010\'R\u001d\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\f0%8F\u00a2\u0006\u0006\u001a\u0004\b1\u0010\'R\u001d\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\f0%8F\u00a2\u0006\u0006\u001a\u0004\b3\u0010\'R\u0017\u00104\u001a\b\u0012\u0004\u0012\u00020\u00150%8F\u00a2\u0006\u0006\u001a\u0004\b5\u0010\'R\u001d\u00106\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\f0%8F\u00a2\u0006\u0006\u001a\u0004\b7\u0010\'R\u001d\u00108\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\f0%8F\u00a2\u0006\u0006\u001a\u0004\b9\u0010\'R\u001d\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\f0%8F\u00a2\u0006\u0006\u001a\u0004\b;\u0010\'R\u0017\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00180%8F\u00a2\u0006\u0006\u001a\u0004\b=\u0010\'R\u0017\u0010>\u001a\b\u0012\u0004\u0012\u00020\t0%\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010\'R\u0017\u0010?\u001a\b\u0012\u0004\u0012\u00020\t0%8F\u00a2\u0006\u0006\u001a\u0004\b@\u0010\'R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010A\u001a\b\u0012\u0004\u0012\u00020\t0%8F\u00a2\u0006\u0006\u001a\u0004\bB\u0010\'R\u0017\u0010C\u001a\b\u0012\u0004\u0012\u00020\t0%8F\u00a2\u0006\u0006\u001a\u0004\bD\u0010\'R\u0017\u0010E\u001a\b\u0012\u0004\u0012\u00020\t0%8F\u00a2\u0006\u0006\u001a\u0004\bF\u0010\'\u00a8\u0006b"}, d2 = {"Lcom/example/project/viewmodels/MyPageViewModel;", "Landroidx/lifecycle/ViewModel;", "service", "Lcom/example/project/api/MyPageService;", "sharedPreferencesUtil", "Lcom/example/project/sharedpreferences/SharedPreferencesUtil;", "(Lcom/example/project/api/MyPageService;Lcom/example/project/sharedpreferences/SharedPreferencesUtil;)V", "_DeleteGifticonResponse", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_DeleteUserReponse", "_GetMyAuction", "", "Lcom/example/project/api/myAuctionData;", "_GetMyAuctionBid", "Lcom/example/project/api/myAuctionBidData;", "_GetMyBarter", "Lcom/example/project/api/myBarterData;", "_GetMyBarterRequest", "Lcom/example/project/api/myBarterRequestData;", "_GetMyGifticon", "Lcom/example/project/api/myGifticon;", "_GetMyGifticonInfo", "_GetMyInfoResponse", "Lcom/example/project/api/userInfoResponse;", "_GetMyPurchase", "Lcom/example/project/api/myPurchaseData;", "_GetMyStore", "Lcom/example/project/api/myStoreData;", "_ModifyUserResponse", "_UploadGifticonResponse", "_UploadProfileResponse", "_ValidUserReponse", "_error", "", "_isLoading", "deleteGifticonResponse", "Lkotlinx/coroutines/flow/StateFlow;", "getDeleteGifticonResponse", "()Lkotlinx/coroutines/flow/StateFlow;", "deleteUserResponse", "getDeleteUserResponse", "error", "getError", "getMyAuctionBidResponse", "getGetMyAuctionBidResponse", "getMyAuctionResponse", "getGetMyAuctionResponse", "getMyBarterRequestResponse", "getGetMyBarterRequestResponse", "getMyBarterResponse", "getGetMyBarterResponse", "getMyGifticonInfoResponse", "getGetMyGifticonInfoResponse", "getMyGifticonResponse", "getGetMyGifticonResponse", "getMyPurchaseResponse", "getGetMyPurchaseResponse", "getMyStoreResponse", "getGetMyStoreResponse", "getMyinfoResponse", "getGetMyinfoResponse", "isLoading", "modifyUserResponse", "getModifyUserResponse", "uploadGifticonResponse", "getUploadGifticonResponse", "uploadProfileResponse", "getUploadProfileResponse", "validUserResponse", "getValidUserResponse", "DeleteUserGifticon", "", "gifticonIdx", "", "getMyAuction", "getMyAuctionBid", "getMyBarter", "getMyBarterRequest", "getMyGifticon", "getMyInfo", "getMyPurchase", "getMyStore", "getUserGifticonInfo", "getUserIdFromPreference", "gifticonUpload", "request", "Lokhttp3/RequestBody;", "originalFile", "Lokhttp3/MultipartBody$Part;", "cropFile", "profileSend", "file", "userDelete", "userModify", "Lcom/example/project/api/userModifyRequest;", "userValid", "Lcom/example/project/api/userValidRequest;", "app_release"})
public final class MyPageViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.project.api.MyPageService service = null;
    private final com.example.project.sharedpreferences.SharedPreferencesUtil sharedPreferencesUtil = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _error = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> error = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _UploadProfileResponse = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _UploadGifticonResponse = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _DeleteGifticonResponse = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.project.api.userInfoResponse> _GetMyInfoResponse = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _ValidUserReponse = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _ModifyUserResponse = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.project.api.myGifticon>> _GetMyGifticon = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.project.api.myAuctionData>> _GetMyAuction = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.project.api.myAuctionBidData>> _GetMyAuctionBid = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.project.api.myBarterData>> _GetMyBarter = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.project.api.myBarterRequestData>> _GetMyBarterRequest = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.project.api.myStoreData>> _GetMyStore = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.project.api.myPurchaseData>> _GetMyPurchase = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _DeleteUserReponse = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.project.api.myGifticon> _GetMyGifticonInfo = null;
    
    @javax.inject.Inject
    public MyPageViewModel(@org.jetbrains.annotations.NotNull
    com.example.project.api.MyPageService service, @org.jetbrains.annotations.NotNull
    com.example.project.sharedpreferences.SharedPreferencesUtil sharedPreferencesUtil) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getError() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getUploadProfileResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getUploadGifticonResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getDeleteGifticonResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.project.api.userInfoResponse> getGetMyinfoResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getValidUserResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getModifyUserResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.project.api.myGifticon>> getGetMyGifticonResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.project.api.myAuctionData>> getGetMyAuctionResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.project.api.myAuctionBidData>> getGetMyAuctionBidResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.project.api.myBarterData>> getGetMyBarterResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.project.api.myBarterRequestData>> getGetMyBarterRequestResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.project.api.myStoreData>> getGetMyStoreResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.project.api.myPurchaseData>> getGetMyPurchaseResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getDeleteUserResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.project.api.myGifticon> getGetMyGifticonInfoResponse() {
        return null;
    }
    
    public final void userDelete() {
    }
    
    public final void getUserGifticonInfo(long gifticonIdx) {
    }
    
    public final void DeleteUserGifticon(long gifticonIdx) {
    }
    
    public final long getUserIdFromPreference() {
        return 0L;
    }
    
    public final void getMyGifticon() {
    }
    
    public final void getMyAuction() {
    }
    
    public final void getMyAuctionBid() {
    }
    
    public final void getMyBarter() {
    }
    
    public final void getMyBarterRequest() {
    }
    
    public final void getMyStore() {
    }
    
    public final void getMyPurchase() {
    }
    
    public final void profileSend(@org.jetbrains.annotations.NotNull
    okhttp3.MultipartBody.Part file) {
    }
    
    public final void gifticonUpload(@org.jetbrains.annotations.NotNull
    okhttp3.RequestBody request, @org.jetbrains.annotations.NotNull
    okhttp3.MultipartBody.Part originalFile, @org.jetbrains.annotations.NotNull
    okhttp3.MultipartBody.Part cropFile) {
    }
    
    public final void getMyInfo() {
    }
    
    public final void userValid(@org.jetbrains.annotations.NotNull
    com.example.project.api.userValidRequest request) {
    }
    
    public final void userModify(@org.jetbrains.annotations.NotNull
    com.example.project.api.userModifyRequest request) {
    }
}