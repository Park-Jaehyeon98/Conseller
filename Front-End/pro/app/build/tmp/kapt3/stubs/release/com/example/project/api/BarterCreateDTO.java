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

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0006H\u00c6\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u000bH\u00c6\u0003JU\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0002\u0010\f\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020\u0003H\u00d6\u0001J\t\u0010%\u001a\u00020\u0006H\u00d6\u0001R\u0011\u0010\b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\f\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006&"}, d2 = {"Lcom/example/project/api/BarterCreateDTO;", "", "mainCategory", "", "subCategory", "barterName", "", "barterText", "barterEndDate", "selectedItemIndices", "", "", "userIdx", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;J)V", "getBarterEndDate", "()Ljava/lang/String;", "getBarterName", "getBarterText", "getMainCategory", "()I", "getSelectedItemIndices", "()Ljava/util/List;", "getSubCategory", "getUserIdx", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app_release"})
public final class BarterCreateDTO {
    private final int mainCategory = 0;
    private final int subCategory = 0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String barterName = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String barterText = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String barterEndDate = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<java.lang.Long> selectedItemIndices = null;
    private final long userIdx = 0L;
    
    @org.jetbrains.annotations.NotNull
    public final com.example.project.api.BarterCreateDTO copy(int mainCategory, int subCategory, @org.jetbrains.annotations.NotNull
    java.lang.String barterName, @org.jetbrains.annotations.NotNull
    java.lang.String barterText, @org.jetbrains.annotations.NotNull
    java.lang.String barterEndDate, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.Long> selectedItemIndices, long userIdx) {
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
    
    public BarterCreateDTO(int mainCategory, int subCategory, @org.jetbrains.annotations.NotNull
    java.lang.String barterName, @org.jetbrains.annotations.NotNull
    java.lang.String barterText, @org.jetbrains.annotations.NotNull
    java.lang.String barterEndDate, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.Long> selectedItemIndices, long userIdx) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int getMainCategory() {
        return 0;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int getSubCategory() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBarterName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBarterText() {
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
    public final java.util.List<java.lang.Long> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.Long> getSelectedItemIndices() {
        return null;
    }
    
    public final long component7() {
        return 0L;
    }
    
    public final long getUserIdx() {
        return 0L;
    }
}