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

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00c6\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\u0006H\u00c6\u0003J=\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006H\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u0003H\u00d6\u0001R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f\u00a8\u0006\u001c"}, d2 = {"Lcom/example/project/api/BarterConfirmPageResponseDTO;", "", "barterName", "", "barterText", "barterConfirmList", "", "Lcom/example/project/api/BarterConfirmList;", "barterTradeAllList", "Lcom/example/project/api/BarterConfirmListOfList;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getBarterConfirmList", "()Ljava/util/List;", "getBarterName", "()Ljava/lang/String;", "getBarterText", "getBarterTradeAllList", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"})
public final class BarterConfirmPageResponseDTO {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String barterName = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String barterText = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.project.api.BarterConfirmList> barterConfirmList = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.project.api.BarterConfirmListOfList> barterTradeAllList = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.example.project.api.BarterConfirmPageResponseDTO copy(@org.jetbrains.annotations.NotNull
    java.lang.String barterName, @org.jetbrains.annotations.NotNull
    java.lang.String barterText, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.project.api.BarterConfirmList> barterConfirmList, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.project.api.BarterConfirmListOfList> barterTradeAllList) {
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
    
    public BarterConfirmPageResponseDTO(@org.jetbrains.annotations.NotNull
    java.lang.String barterName, @org.jetbrains.annotations.NotNull
    java.lang.String barterText, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.project.api.BarterConfirmList> barterConfirmList, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.project.api.BarterConfirmListOfList> barterTradeAllList) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBarterName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBarterText() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.project.api.BarterConfirmList> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.project.api.BarterConfirmList> getBarterConfirmList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.project.api.BarterConfirmListOfList> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.project.api.BarterConfirmListOfList> getBarterTradeAllList() {
        return null;
    }
}