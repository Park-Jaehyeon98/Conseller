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

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u00a2\u0006\u0002\u0010\u0012J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010%\u001a\u0004\u0018\u00010\rH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0014J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0005H\u00c6\u0003J\t\u0010(\u001a\u00020\u0005H\u00c6\u0003J\t\u0010)\u001a\u00020\u0005H\u00c6\u0003J\t\u0010*\u001a\u00020\u0005H\u00c6\u0003J\t\u0010+\u001a\u00020\u0005H\u00c6\u0003J\t\u0010,\u001a\u00020\u0005H\u00c6\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010.\u001a\u00020\rH\u00c6\u0003J\u0086\u0001\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u00c6\u0001\u00a2\u0006\u0002\u00100J\u0013\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00104\u001a\u00020\rH\u00d6\u0001J\t\u00105\u001a\u00020\u0005H\u00d6\u0001R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\r\u00a2\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u0011\u0010\t\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0017R\u0011\u0010\n\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017\u00a8\u00066"}, d2 = {"Lcom/example/project/api/myBarterData;", "", "barterIdx", "", "barterName", "", "barterText", "barterCreatedDate", "barterEndDate", "barterStatus", "subCategory", "preferSubCategory", "barterHostIdx", "", "barterCompleteGuestIdx", "barterHostItems", "", "Lcom/example/project/api/BarterHostItem;", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/util/List;)V", "getBarterCompleteGuestIdx", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getBarterCreatedDate", "()Ljava/lang/String;", "getBarterEndDate", "getBarterHostIdx", "()I", "getBarterHostItems", "()Ljava/util/List;", "getBarterIdx", "()J", "getBarterName", "getBarterStatus", "getBarterText", "getPreferSubCategory", "getSubCategory", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/util/List;)Lcom/example/project/api/myBarterData;", "equals", "", "other", "hashCode", "toString", "app_release"})
public final class myBarterData {
    private final long barterIdx = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String barterName = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String barterText = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String barterCreatedDate = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String barterEndDate = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String barterStatus = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String subCategory = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String preferSubCategory = null;
    private final int barterHostIdx = 0;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer barterCompleteGuestIdx = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.project.api.BarterHostItem> barterHostItems = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.example.project.api.myBarterData copy(long barterIdx, @org.jetbrains.annotations.NotNull
    java.lang.String barterName, @org.jetbrains.annotations.NotNull
    java.lang.String barterText, @org.jetbrains.annotations.NotNull
    java.lang.String barterCreatedDate, @org.jetbrains.annotations.NotNull
    java.lang.String barterEndDate, @org.jetbrains.annotations.NotNull
    java.lang.String barterStatus, @org.jetbrains.annotations.NotNull
    java.lang.String subCategory, @org.jetbrains.annotations.Nullable
    java.lang.String preferSubCategory, int barterHostIdx, @org.jetbrains.annotations.Nullable
    java.lang.Integer barterCompleteGuestIdx, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.project.api.BarterHostItem> barterHostItems) {
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
    
    public myBarterData(long barterIdx, @org.jetbrains.annotations.NotNull
    java.lang.String barterName, @org.jetbrains.annotations.NotNull
    java.lang.String barterText, @org.jetbrains.annotations.NotNull
    java.lang.String barterCreatedDate, @org.jetbrains.annotations.NotNull
    java.lang.String barterEndDate, @org.jetbrains.annotations.NotNull
    java.lang.String barterStatus, @org.jetbrains.annotations.NotNull
    java.lang.String subCategory, @org.jetbrains.annotations.Nullable
    java.lang.String preferSubCategory, int barterHostIdx, @org.jetbrains.annotations.Nullable
    java.lang.Integer barterCompleteGuestIdx, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.project.api.BarterHostItem> barterHostItems) {
        super();
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final long getBarterIdx() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBarterName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBarterText() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBarterCreatedDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBarterEndDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBarterStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getSubCategory() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getPreferSubCategory() {
        return null;
    }
    
    public final int component9() {
        return 0;
    }
    
    public final int getBarterHostIdx() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getBarterCompleteGuestIdx() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.project.api.BarterHostItem> component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.project.api.BarterHostItem> getBarterHostItems() {
        return null;
    }
}