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

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\tH\u00c6\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\tH\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u001d\u001a\u00020\u0006H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000f\u00a8\u0006\u001e"}, d2 = {"Lcom/example/project/api/myAuctionBidData;", "", "auctionBidIdx", "", "auctionBidPrice", "auctionRegistedDate", "", "auctionBidStatus", "auctionItemData", "Lcom/example/project/viewmodels/AuctionItemData;", "(IILjava/lang/String;Ljava/lang/String;Lcom/example/project/viewmodels/AuctionItemData;)V", "getAuctionBidIdx", "()I", "getAuctionBidPrice", "getAuctionBidStatus", "()Ljava/lang/String;", "getAuctionItemData", "()Lcom/example/project/viewmodels/AuctionItemData;", "getAuctionRegistedDate", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class myAuctionBidData {
    private final int auctionBidIdx = 0;
    private final int auctionBidPrice = 0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String auctionRegistedDate = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String auctionBidStatus = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.project.viewmodels.AuctionItemData auctionItemData = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.example.project.api.myAuctionBidData copy(int auctionBidIdx, int auctionBidPrice, @org.jetbrains.annotations.NotNull
    java.lang.String auctionRegistedDate, @org.jetbrains.annotations.NotNull
    java.lang.String auctionBidStatus, @org.jetbrains.annotations.NotNull
    com.example.project.viewmodels.AuctionItemData auctionItemData) {
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
    
    public myAuctionBidData(int auctionBidIdx, int auctionBidPrice, @org.jetbrains.annotations.NotNull
    java.lang.String auctionRegistedDate, @org.jetbrains.annotations.NotNull
    java.lang.String auctionBidStatus, @org.jetbrains.annotations.NotNull
    com.example.project.viewmodels.AuctionItemData auctionItemData) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int getAuctionBidIdx() {
        return 0;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int getAuctionBidPrice() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAuctionRegistedDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAuctionBidStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.project.viewmodels.AuctionItemData component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.project.viewmodels.AuctionItemData getAuctionItemData() {
        return null;
    }
}