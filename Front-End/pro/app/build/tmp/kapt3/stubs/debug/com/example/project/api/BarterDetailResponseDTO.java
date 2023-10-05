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

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BS\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u000fJ\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0006H\u00c6\u0003J\t\u0010 \u001a\u00020\u0006H\u00c6\u0003J\t\u0010!\u001a\u00020\bH\u00c6\u0003J\t\u0010\"\u001a\u00020\u0006H\u00c6\u0003J\t\u0010#\u001a\u00020\bH\u00c6\u0003J\t\u0010$\u001a\u00020\u0006H\u00c6\u0003Ji\u0010%\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010&\u001a\u00020\'2\b\u0010(\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010)\u001a\u00020*H\u00d6\u0001J\t\u0010+\u001a\u00020\u0006H\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\r\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0011\u0010\u000e\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0011\u0010\f\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013\u00a8\u0006,"}, d2 = {"Lcom/example/project/api/BarterDetailResponseDTO;", "", "barterImageList", "", "Lcom/example/project/api/BarterConfirmList;", "preper", "", "barterRequestIdx", "", "barterName", "barterText", "barterUserIdx", "barterUserProfileUrl", "barterUserDeposit", "barterUserNickname", "(Ljava/util/List;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;JLjava/lang/String;JLjava/lang/String;)V", "getBarterImageList", "()Ljava/util/List;", "getBarterName", "()Ljava/lang/String;", "getBarterRequestIdx", "()J", "getBarterText", "getBarterUserDeposit", "getBarterUserIdx", "getBarterUserNickname", "getBarterUserProfileUrl", "getPreper", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class BarterDetailResponseDTO {
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.project.api.BarterConfirmList> barterImageList = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String preper = null;
    private final long barterRequestIdx = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String barterName = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String barterText = null;
    private final long barterUserIdx = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String barterUserProfileUrl = null;
    private final long barterUserDeposit = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String barterUserNickname = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.example.project.api.BarterDetailResponseDTO copy(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.project.api.BarterConfirmList> barterImageList, @org.jetbrains.annotations.NotNull
    java.lang.String preper, long barterRequestIdx, @org.jetbrains.annotations.NotNull
    java.lang.String barterName, @org.jetbrains.annotations.NotNull
    java.lang.String barterText, long barterUserIdx, @org.jetbrains.annotations.NotNull
    java.lang.String barterUserProfileUrl, long barterUserDeposit, @org.jetbrains.annotations.NotNull
    java.lang.String barterUserNickname) {
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
    
    public BarterDetailResponseDTO(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.project.api.BarterConfirmList> barterImageList, @org.jetbrains.annotations.NotNull
    java.lang.String preper, long barterRequestIdx, @org.jetbrains.annotations.NotNull
    java.lang.String barterName, @org.jetbrains.annotations.NotNull
    java.lang.String barterText, long barterUserIdx, @org.jetbrains.annotations.NotNull
    java.lang.String barterUserProfileUrl, long barterUserDeposit, @org.jetbrains.annotations.NotNull
    java.lang.String barterUserNickname) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.project.api.BarterConfirmList> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.project.api.BarterConfirmList> getBarterImageList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getPreper() {
        return null;
    }
    
    public final long component3() {
        return 0L;
    }
    
    public final long getBarterRequestIdx() {
        return 0L;
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
    public final java.lang.String getBarterText() {
        return null;
    }
    
    public final long component6() {
        return 0L;
    }
    
    public final long getBarterUserIdx() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBarterUserProfileUrl() {
        return null;
    }
    
    public final long component8() {
        return 0L;
    }
    
    public final long getBarterUserDeposit() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBarterUserNickname() {
        return null;
    }
}