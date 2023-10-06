package com.example.project.api;

import com.example.project.viewmodels.GifticonData;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\bH\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\bH\u00d6\u0001J\t\u0010\u001d\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011\u00a8\u0006\u001e"}, d2 = {"Lcom/example/project/api/RegistInquiryRequestDTO;", "", "userIdx", "", "inquiryTitle", "", "inquiryContent", "inquiryType", "", "reportedUserIdx", "(JLjava/lang/String;Ljava/lang/String;IJ)V", "getInquiryContent", "()Ljava/lang/String;", "getInquiryTitle", "getInquiryType", "()I", "getReportedUserIdx", "()J", "getUserIdx", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class RegistInquiryRequestDTO {
    private final long userIdx = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String inquiryTitle = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String inquiryContent = null;
    private final int inquiryType = 0;
    private final long reportedUserIdx = 0L;
    
    @org.jetbrains.annotations.NotNull
    public final com.example.project.api.RegistInquiryRequestDTO copy(long userIdx, @org.jetbrains.annotations.NotNull
    java.lang.String inquiryTitle, @org.jetbrains.annotations.NotNull
    java.lang.String inquiryContent, int inquiryType, long reportedUserIdx) {
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
    
    public RegistInquiryRequestDTO(long userIdx, @org.jetbrains.annotations.NotNull
    java.lang.String inquiryTitle, @org.jetbrains.annotations.NotNull
    java.lang.String inquiryContent, int inquiryType, long reportedUserIdx) {
        super();
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final long getUserIdx() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getInquiryTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getInquiryContent() {
        return null;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final int getInquiryType() {
        return 0;
    }
    
    public final long component5() {
        return 0L;
    }
    
    public final long getReportedUserIdx() {
        return 0L;
    }
}