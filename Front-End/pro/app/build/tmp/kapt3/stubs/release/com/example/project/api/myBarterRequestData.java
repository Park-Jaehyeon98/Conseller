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

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\nH\u00c6\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\r0\fH\u00c6\u0003JU\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u00c6\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010%\u001a\u00020\u0003H\u00d6\u0001J\t\u0010&\u001a\u00020\u0005H\u00d6\u0001R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006\'"}, d2 = {"Lcom/example/project/api/myBarterRequestData;", "", "barterRequestIdx", "", "barterRequestStatus", "", "barterIdx", "barterName", "barterStatus", "myBarterResponseDto", "Lcom/example/project/api/myBarterData;", "barterGuestItems", "", "Lcom/example/project/api/myGifticon;", "(ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;Lcom/example/project/api/myBarterData;Ljava/util/List;)V", "getBarterGuestItems", "()Ljava/util/List;", "getBarterIdx", "()I", "getBarterName", "()Ljava/lang/String;", "getBarterRequestIdx", "getBarterRequestStatus", "getBarterStatus", "getMyBarterResponseDto", "()Lcom/example/project/api/myBarterData;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app_release"})
public final class myBarterRequestData {
    private final int barterRequestIdx = 0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String barterRequestStatus = null;
    private final int barterIdx = 0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String barterName = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String barterStatus = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.project.api.myBarterData myBarterResponseDto = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.project.api.myGifticon> barterGuestItems = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.example.project.api.myBarterRequestData copy(int barterRequestIdx, @org.jetbrains.annotations.NotNull
    java.lang.String barterRequestStatus, int barterIdx, @org.jetbrains.annotations.NotNull
    java.lang.String barterName, @org.jetbrains.annotations.NotNull
    java.lang.String barterStatus, @org.jetbrains.annotations.NotNull
    com.example.project.api.myBarterData myBarterResponseDto, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.project.api.myGifticon> barterGuestItems) {
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
    
    public myBarterRequestData(int barterRequestIdx, @org.jetbrains.annotations.NotNull
    java.lang.String barterRequestStatus, int barterIdx, @org.jetbrains.annotations.NotNull
    java.lang.String barterName, @org.jetbrains.annotations.NotNull
    java.lang.String barterStatus, @org.jetbrains.annotations.NotNull
    com.example.project.api.myBarterData myBarterResponseDto, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.project.api.myGifticon> barterGuestItems) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int getBarterRequestIdx() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBarterRequestStatus() {
        return null;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int getBarterIdx() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBarterName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBarterStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.project.api.myBarterData component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.project.api.myBarterData getMyBarterResponseDto() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.project.api.myGifticon> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.project.api.myGifticon> getBarterGuestItems() {
        return null;
    }
}