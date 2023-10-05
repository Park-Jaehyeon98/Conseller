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

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u0010J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u000eH\u00c6\u0003J\t\u0010!\u001a\u00020\u000eH\u00c6\u0003J\t\u0010\"\u001a\u00020\u0005H\u00c6\u0003J\t\u0010#\u001a\u00020\u0005H\u00c6\u0003J\t\u0010$\u001a\u00020\u0005H\u00c6\u0003J\t\u0010%\u001a\u00020\u0005H\u00c6\u0003J\t\u0010&\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0005H\u00c6\u0003J\t\u0010(\u001a\u00020\u0005H\u00c6\u0003J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003Jw\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000eH\u00c6\u0001J\u0013\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010.\u001a\u00020\u000eH\u00d6\u0001J\t\u0010/\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\t\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\n\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u0011\u0010\u000f\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017\u00a8\u00060"}, d2 = {"Lcom/example/project/api/myGifticon;", "", "gifticonIdx", "", "gifticonBarcode", "", "gifticonName", "gifticonStartDate", "gifticonEndDate", "gifticonAllImageUrl", "gifticonDataImageUrl", "gifticonStatus", "userIdx", "subCategoryIdx", "", "mainCategoryIdx", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JII)V", "getGifticonAllImageUrl", "()Ljava/lang/String;", "getGifticonBarcode", "getGifticonDataImageUrl", "getGifticonEndDate", "getGifticonIdx", "()J", "getGifticonName", "getGifticonStartDate", "getGifticonStatus", "getMainCategoryIdx", "()I", "getSubCategoryIdx", "getUserIdx", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_release"})
public final class myGifticon {
    private final long gifticonIdx = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String gifticonBarcode = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String gifticonName = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String gifticonStartDate = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String gifticonEndDate = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String gifticonAllImageUrl = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String gifticonDataImageUrl = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String gifticonStatus = null;
    private final long userIdx = 0L;
    private final int subCategoryIdx = 0;
    private final int mainCategoryIdx = 0;
    
    @org.jetbrains.annotations.NotNull
    public final com.example.project.api.myGifticon copy(long gifticonIdx, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonBarcode, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonName, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonStartDate, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonEndDate, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonAllImageUrl, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonDataImageUrl, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonStatus, long userIdx, int subCategoryIdx, int mainCategoryIdx) {
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
    
    public myGifticon(long gifticonIdx, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonBarcode, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonName, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonStartDate, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonEndDate, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonAllImageUrl, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonDataImageUrl, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonStatus, long userIdx, int subCategoryIdx, int mainCategoryIdx) {
        super();
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final long getGifticonIdx() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGifticonBarcode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGifticonName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGifticonStartDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGifticonEndDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGifticonAllImageUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGifticonDataImageUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getGifticonStatus() {
        return null;
    }
    
    public final long component9() {
        return 0L;
    }
    
    public final long getUserIdx() {
        return 0L;
    }
    
    public final int component10() {
        return 0;
    }
    
    public final int getSubCategoryIdx() {
        return 0;
    }
    
    public final int component11() {
        return 0;
    }
    
    public final int getMainCategoryIdx() {
        return 0;
    }
}