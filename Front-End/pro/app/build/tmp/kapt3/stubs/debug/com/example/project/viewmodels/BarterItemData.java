package com.example.project.viewmodels;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.example.project.api.BarterConfirmList;
import com.example.project.api.BarterConfirmPageResponseDTO;
import com.example.project.api.BarterConfirmRequestDTO;
import com.example.project.api.BarterCreateDTO;
import com.example.project.api.BarterDetailResponseDTO;
import com.example.project.api.BarterFilterDTO;
import com.example.project.api.BarterService;
import com.example.project.api.StoreConfirmPageResponseDTO;
import com.example.project.api.StoreConfirmRequestDTO;
import com.example.project.api.TradeBarterRequestDTO;
import com.example.project.api.UpdateBarterDTO;
import com.example.project.di.CustomException;
import com.example.project.sharedpreferences.SharedPreferencesUtil;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\nH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00c6\u0003JY\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010!\u001a\u00020\n2\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010#\u001a\u00020$H\u00d6\u0001J\t\u0010%\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000f\u00a8\u0006&"}, d2 = {"Lcom/example/project/viewmodels/BarterItemData;", "", "barterIdx", "", "gifticonDataImageName", "", "gifticonName", "gifticonEndDate", "barterEndDate", "isDeposit", "", "preper", "barterName", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;)V", "getBarterEndDate", "()Ljava/lang/String;", "getBarterIdx", "()J", "getBarterName", "getGifticonDataImageName", "getGifticonEndDate", "getGifticonName", "()Z", "getPreper", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class BarterItemData {
    private final long barterIdx = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String gifticonDataImageName = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String gifticonName = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String gifticonEndDate = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String barterEndDate = null;
    private final boolean isDeposit = false;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String preper = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String barterName = null;
    
    @org.jetbrains.annotations.NotNull
    public final com.example.project.viewmodels.BarterItemData copy(long barterIdx, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonDataImageName, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonName, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonEndDate, @org.jetbrains.annotations.NotNull
    java.lang.String barterEndDate, boolean isDeposit, @org.jetbrains.annotations.NotNull
    java.lang.String preper, @org.jetbrains.annotations.NotNull
    java.lang.String barterName) {
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
    
    public BarterItemData(long barterIdx, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonDataImageName, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonName, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonEndDate, @org.jetbrains.annotations.NotNull
    java.lang.String barterEndDate, boolean isDeposit, @org.jetbrains.annotations.NotNull
    java.lang.String preper, @org.jetbrains.annotations.NotNull
    java.lang.String barterName) {
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
    public final java.lang.String getGifticonDataImageName() {
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
    public final java.lang.String getGifticonEndDate() {
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
    
    public final boolean component6() {
        return false;
    }
    
    public final boolean isDeposit() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getPreper() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBarterName() {
        return null;
    }
}