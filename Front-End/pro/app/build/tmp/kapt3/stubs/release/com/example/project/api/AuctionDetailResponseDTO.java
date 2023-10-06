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

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b,\b\u0087\b\u0018\u00002\u00020\u0001B\u0083\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u0016\u001a\u00020\u0014\u00a2\u0006\u0002\u0010\u0017J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\t\u0010/\u001a\u00020\u0012H\u00c6\u0003J\t\u00100\u001a\u00020\u0014H\u00c6\u0003J\t\u00101\u001a\u00020\u0014H\u00c6\u0003J\t\u00102\u001a\u00020\u0014H\u00c6\u0003J\t\u00103\u001a\u00020\u0005H\u00c6\u0003J\t\u00104\u001a\u00020\u0003H\u00c6\u0003J\t\u00105\u001a\u00020\u0003H\u00c6\u0003J\t\u00106\u001a\u00020\u0005H\u00c6\u0003J\u000f\u00107\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0003J\t\u00108\u001a\u00020\u0005H\u00c6\u0003J\t\u00109\u001a\u00020\u0003H\u00c6\u0003J\t\u0010:\u001a\u00020\u0003H\u00c6\u0003J\u00a5\u0001\u0010;\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u0014H\u00c6\u0001J\u0013\u0010<\u001a\u00020\u00122\b\u0010=\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010>\u001a\u00020\u0014H\u00d6\u0001J\t\u0010?\u001a\u00020\u0003H\u00d6\u0001R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0010\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0016\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001bR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001bR\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001bR\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001bR\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001bR\u0011\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001dR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001bR\u0011\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001d\u00a8\u0006@"}, d2 = {"Lcom/example/project/api/AuctionDetailResponseDTO;", "", "postContent", "", "auctionUserIdx", "", "auctionUserNickname", "auctionUserProfileUrl", "auctionUserDeposit", "auctionBidList", "", "Lcom/example/project/viewmodels/AuctionBid;", "auctionIdx", "gifticonDataImageName", "gifticonName", "gifticonEndDate", "auctionEndDate", "deposit", "", "upperPrice", "", "lowerPrice", "auctionHighestBid", "(Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;JLjava/util/List;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZIII)V", "getAuctionBidList", "()Ljava/util/List;", "getAuctionEndDate", "()Ljava/lang/String;", "getAuctionHighestBid", "()I", "getAuctionIdx", "()J", "getAuctionUserDeposit", "getAuctionUserIdx", "getAuctionUserNickname", "getAuctionUserProfileUrl", "getDeposit", "()Z", "getGifticonDataImageName", "getGifticonEndDate", "getGifticonName", "getLowerPrice", "getPostContent", "getUpperPrice", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_release"})
public final class AuctionDetailResponseDTO {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String postContent = null;
    private final long auctionUserIdx = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String auctionUserNickname = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String auctionUserProfileUrl = null;
    private final long auctionUserDeposit = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.project.viewmodels.AuctionBid> auctionBidList = null;
    private final long auctionIdx = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String gifticonDataImageName = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String gifticonName = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String gifticonEndDate = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String auctionEndDate = null;
    private final boolean deposit = false;
    private final int upperPrice = 0;
    private final int lowerPrice = 0;
    private final int auctionHighestBid = 0;
    
    @org.jetbrains.annotations.NotNull
    public final com.example.project.api.AuctionDetailResponseDTO copy(@org.jetbrains.annotations.NotNull
    java.lang.String postContent, long auctionUserIdx, @org.jetbrains.annotations.NotNull
    java.lang.String auctionUserNickname, @org.jetbrains.annotations.NotNull
    java.lang.String auctionUserProfileUrl, long auctionUserDeposit, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.project.viewmodels.AuctionBid> auctionBidList, long auctionIdx, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonDataImageName, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonName, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonEndDate, @org.jetbrains.annotations.NotNull
    java.lang.String auctionEndDate, boolean deposit, int upperPrice, int lowerPrice, int auctionHighestBid) {
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
    
    public AuctionDetailResponseDTO(@org.jetbrains.annotations.NotNull
    java.lang.String postContent, long auctionUserIdx, @org.jetbrains.annotations.NotNull
    java.lang.String auctionUserNickname, @org.jetbrains.annotations.NotNull
    java.lang.String auctionUserProfileUrl, long auctionUserDeposit, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.project.viewmodels.AuctionBid> auctionBidList, long auctionIdx, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonDataImageName, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonName, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonEndDate, @org.jetbrains.annotations.NotNull
    java.lang.String auctionEndDate, boolean deposit, int upperPrice, int lowerPrice, int auctionHighestBid) {
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
    
    public final long getAuctionUserIdx() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAuctionUserNickname() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAuctionUserProfileUrl() {
        return null;
    }
    
    public final long component5() {
        return 0L;
    }
    
    public final long getAuctionUserDeposit() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.project.viewmodels.AuctionBid> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.project.viewmodels.AuctionBid> getAuctionBidList() {
        return null;
    }
    
    public final long component7() {
        return 0L;
    }
    
    public final long getAuctionIdx() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGifticonDataImageName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGifticonName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGifticonEndDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAuctionEndDate() {
        return null;
    }
    
    public final boolean component12() {
        return false;
    }
    
    public final boolean getDeposit() {
        return false;
    }
    
    public final int component13() {
        return 0;
    }
    
    public final int getUpperPrice() {
        return 0;
    }
    
    public final int component14() {
        return 0;
    }
    
    public final int getLowerPrice() {
        return 0;
    }
    
    public final int component15() {
        return 0;
    }
    
    public final int getAuctionHighestBid() {
        return 0;
    }
}