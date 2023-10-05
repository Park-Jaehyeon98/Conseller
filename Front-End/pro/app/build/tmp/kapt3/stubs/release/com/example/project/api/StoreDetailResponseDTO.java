package com.example.project.api;

import com.example.project.viewmodels.StoreItemData;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b#\b\u0087\b\u0018\u00002\u00020\u0001Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0002\u0010\u0012J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u000fH\u00c6\u0003J\t\u0010&\u001a\u00020\u0011H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0005H\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\t\u0010*\u001a\u00020\u0005H\u00c6\u0003J\t\u0010+\u001a\u00020\u0005H\u00c6\u0003J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\u0081\u0001\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u00c6\u0001J\u0013\u00100\u001a\u00020\u000f2\b\u00101\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00102\u001a\u00020\u0011H\u00d6\u0001J\t\u00103\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0011\u0010\t\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0016\u00a8\u00064"}, d2 = {"Lcom/example/project/api/StoreDetailResponseDTO;", "", "postContent", "", "storeUserIdx", "", "storeUserNickname", "storeUserProfileUrl", "storeUserDeposit", "storeIdx", "gifticonDataImageName", "gifticonName", "gifticonEndDate", "storeEndDate", "deposit", "", "storePrice", "", "(Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZI)V", "getDeposit", "()Z", "getGifticonDataImageName", "()Ljava/lang/String;", "getGifticonEndDate", "getGifticonName", "getPostContent", "getStoreEndDate", "getStoreIdx", "()J", "getStorePrice", "()I", "getStoreUserDeposit", "getStoreUserIdx", "getStoreUserNickname", "getStoreUserProfileUrl", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_release"})
public final class StoreDetailResponseDTO {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String postContent = null;
    private final long storeUserIdx = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String storeUserNickname = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String storeUserProfileUrl = null;
    private final long storeUserDeposit = 0L;
    private final long storeIdx = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String gifticonDataImageName = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String gifticonName = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String gifticonEndDate = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String storeEndDate = null;
    private final boolean deposit = false;
    private final int storePrice = 0;
    
    @org.jetbrains.annotations.NotNull
    public final com.example.project.api.StoreDetailResponseDTO copy(@org.jetbrains.annotations.NotNull
    java.lang.String postContent, long storeUserIdx, @org.jetbrains.annotations.NotNull
    java.lang.String storeUserNickname, @org.jetbrains.annotations.NotNull
    java.lang.String storeUserProfileUrl, long storeUserDeposit, long storeIdx, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonDataImageName, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonName, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonEndDate, @org.jetbrains.annotations.NotNull
    java.lang.String storeEndDate, boolean deposit, int storePrice) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public StoreDetailResponseDTO(@org.jetbrains.annotations.NotNull
    java.lang.String postContent, long storeUserIdx, @org.jetbrains.annotations.NotNull
    java.lang.String storeUserNickname, @org.jetbrains.annotations.NotNull
    java.lang.String storeUserProfileUrl, long storeUserDeposit, long storeIdx, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonDataImageName, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonName, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonEndDate, @org.jetbrains.annotations.NotNull
    java.lang.String storeEndDate, boolean deposit, int storePrice) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getPostContent() {
        return null;
    }
    
    public final long component2() {
        return 0L;
    }
    
    public final long getStoreUserIdx() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getStoreUserNickname() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getStoreUserProfileUrl() {
        return null;
    }
    
    public final long component5() {
        return 0L;
    }
    
    public final long getStoreUserDeposit() {
        return 0L;
    }
    
    public final long component6() {
        return 0L;
    }
    
    public final long getStoreIdx() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGifticonDataImageName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGifticonName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGifticonEndDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getStoreEndDate() {
        return null;
    }
    
    public final boolean component11() {
        return false;
    }
    
    public final boolean getDeposit() {
        return false;
    }
    
    public final int component12() {
        return 0;
    }
    
    public final int getStorePrice() {
        return 0;
    }
}