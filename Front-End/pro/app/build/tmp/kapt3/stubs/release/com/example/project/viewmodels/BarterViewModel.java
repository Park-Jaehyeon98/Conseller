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

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u001e\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u000200J\u001e\u0010!\u001a\u00020<2\u0006\u0010>\u001a\u00020\u00152\u0006\u0010?\u001a\u00020\u00152\u0006\u0010@\u001a\u00020\tJ\u000e\u0010A\u001a\u00020<2\u0006\u0010B\u001a\u00020\u001bJ<\u0010C\u001a\u00020<2\u0006\u0010D\u001a\u00020\u00172\u0006\u0010E\u001a\u00020\u00172\u0006\u0010F\u001a\u00020\u00172\u0006\u0010G\u001a\u00020\u00172\u0006\u0010H\u001a\u00020\u00172\f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00150\u0010J\u000e\u0010J\u001a\u00020<2\u0006\u0010>\u001a\u00020\u0015J\u000e\u0010K\u001a\u00020<2\u0006\u0010>\u001a\u00020\u0015J\u000e\u0010L\u001a\u00020<2\u0006\u0010>\u001a\u00020\u0015J\u0006\u0010M\u001a\u00020<J\u0006\u0010N\u001a\u00020<J\u001a\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00150\u0010J\u0006\u0010Q\u001a\u00020\u0015J\u001c\u0010R\u001a\u00020<2\u0006\u0010>\u001a\u00020\u00152\f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00150\u0010J\u0015\u0010S\u001a\u00020<2\b\u0010T\u001a\u0004\u0018\u00010\u0015\u00a2\u0006\u0002\u0010UJ\u0006\u0010V\u001a\u00020<J\u000e\u0010W\u001a\u00020<2\u0006\u0010X\u001a\u00020\u0017J6\u0010Y\u001a\u00020<2\u0006\u0010>\u001a\u00020\u00152\u0006\u0010D\u001a\u00020\u00172\u0006\u0010E\u001a\u00020\u00172\u0006\u0010F\u001a\u00020\u00172\u0006\u0010G\u001a\u00020\u00172\u0006\u0010H\u001a\u00020\u0017R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0019\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u0019\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u0019\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010 R\u001d\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010 R\u0019\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010 R\u0019\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010 R\u0019\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010 R\u000e\u0010/\u001a\u000200X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0019\u00102\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010 R\u0017\u00104\u001a\b\u0012\u0004\u0012\u00020\t0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010 R\u001d\u00105\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010 R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u00107\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010 R\u0019\u00109\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010 \u00a8\u0006Z"}, d2 = {"Lcom/example/project/viewmodels/BarterViewModel;", "Landroidx/lifecycle/ViewModel;", "service", "Lcom/example/project/api/BarterService;", "sharedPreferencesUtil", "Lcom/example/project/sharedpreferences/SharedPreferencesUtil;", "(Lcom/example/project/api/BarterService;Lcom/example/project/sharedpreferences/SharedPreferencesUtil;)V", "_barterCancelNavi", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_barterConfirm", "Lcom/example/project/api/BarterConfirmPageResponseDTO;", "_barterConfirmNavi", "_barterDetail", "Lcom/example/project/api/BarterDetailResponseDTO;", "_barterItems", "", "Lcom/example/project/viewmodels/BarterItemData;", "_barterNavi", "_barterPopular", "_createBarterNavi", "", "_error", "", "_isLoading", "_myBarterItems", "_totalItems", "", "_updateBarterNavi", "barterCancelNavi", "Lkotlinx/coroutines/flow/StateFlow;", "getBarterCancelNavi", "()Lkotlinx/coroutines/flow/StateFlow;", "barterConfirm", "getBarterConfirm", "barterConfirmNavi", "getBarterConfirmNavi", "barterDetail", "getBarterDetail", "barterItems", "getBarterItems", "barterNavi", "getBarterNavi", "barterPopular", "getBarterPopular", "createBarterNavi", "getCreateBarterNavi", "currentFilter", "Lcom/example/project/api/BarterFilterDTO;", "currentPage", "error", "getError", "isLoading", "myBarterItems", "getMyBarterItems", "totalItems", "getTotalItems", "updateBarterNavi", "getUpdateBarterNavi", "applyFilter", "", "filter", "barterIdx", "userIdx", "confirm", "changePage", "page", "createBarterItem", "kindBigStatus", "kindSmallStatus", "barterName", "barterText", "barterEndDate", "selectedItemIndices", "deleteBarterItem", "fetchBarterConfirmItems", "fetchBarterDetail", "fetchBarterItems", "fetchPopularBarteritems", "getSelectedItems", "indices", "getUserIdFromPreference", "proposeBarterTrade", "proposeCancleBarterTrade", "barterRequestIdx", "(Ljava/lang/Long;)V", "resetNavigation", "searchItems", "query", "updateBarterItem", "app_release"})
public final class BarterViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.project.api.BarterService service = null;
    private final com.example.project.sharedpreferences.SharedPreferencesUtil sharedPreferencesUtil = null;
    private int currentPage = 1;
    private com.example.project.api.BarterFilterDTO currentFilter;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.project.viewmodels.BarterItemData>> _barterItems = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.project.viewmodels.BarterItemData>> barterItems = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _totalItems = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> totalItems = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _error = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> error = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.project.api.BarterDetailResponseDTO> _barterDetail = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.example.project.api.BarterDetailResponseDTO> barterDetail = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.project.viewmodels.BarterItemData>> _myBarterItems = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.project.viewmodels.BarterItemData>> myBarterItems = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Long> _createBarterNavi = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Long> createBarterNavi = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _updateBarterNavi = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> updateBarterNavi = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.project.api.BarterConfirmPageResponseDTO> _barterConfirm = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.example.project.api.BarterConfirmPageResponseDTO> barterConfirm = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _barterConfirmNavi = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> barterConfirmNavi = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _barterCancelNavi = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> barterCancelNavi = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _barterNavi = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> barterNavi = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.project.viewmodels.BarterItemData> _barterPopular = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.example.project.viewmodels.BarterItemData> barterPopular = null;
    
    @javax.inject.Inject
    public BarterViewModel(@org.jetbrains.annotations.NotNull
    com.example.project.api.BarterService service, @org.jetbrains.annotations.NotNull
    com.example.project.sharedpreferences.SharedPreferencesUtil sharedPreferencesUtil) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.project.viewmodels.BarterItemData>> getBarterItems() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getTotalItems() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getError() {
        return null;
    }
    
    public final long getUserIdFromPreference() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.project.api.BarterDetailResponseDTO> getBarterDetail() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.project.viewmodels.BarterItemData>> getMyBarterItems() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Long> getCreateBarterNavi() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getUpdateBarterNavi() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.project.api.BarterConfirmPageResponseDTO> getBarterConfirm() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getBarterConfirmNavi() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getBarterCancelNavi() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getBarterNavi() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.project.viewmodels.BarterItemData> getBarterPopular() {
        return null;
    }
    
    public final void resetNavigation() {
    }
    
    public final void changePage(int page) {
    }
    
    public final void applyFilter(@org.jetbrains.annotations.NotNull
    com.example.project.api.BarterFilterDTO filter) {
    }
    
    public final void searchItems(@org.jetbrains.annotations.NotNull
    java.lang.String query) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.project.viewmodels.BarterItemData> getSelectedItems(@org.jetbrains.annotations.NotNull
    java.util.List<java.lang.Long> indices) {
        return null;
    }
    
    public final void fetchBarterItems() {
    }
    
    public final void createBarterItem(@org.jetbrains.annotations.NotNull
    java.lang.String kindBigStatus, @org.jetbrains.annotations.NotNull
    java.lang.String kindSmallStatus, @org.jetbrains.annotations.NotNull
    java.lang.String barterName, @org.jetbrains.annotations.NotNull
    java.lang.String barterText, @org.jetbrains.annotations.NotNull
    java.lang.String barterEndDate, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.Long> selectedItemIndices) {
    }
    
    public final void updateBarterItem(long barterIdx, @org.jetbrains.annotations.NotNull
    java.lang.String kindBigStatus, @org.jetbrains.annotations.NotNull
    java.lang.String kindSmallStatus, @org.jetbrains.annotations.NotNull
    java.lang.String barterName, @org.jetbrains.annotations.NotNull
    java.lang.String barterText, @org.jetbrains.annotations.NotNull
    java.lang.String barterEndDate) {
    }
    
    public final void deleteBarterItem(long barterIdx) {
    }
    
    public final void fetchBarterDetail(long barterIdx) {
    }
    
    public final void proposeBarterTrade(long barterIdx, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.Long> selectedItemIndices) {
    }
    
    public final void proposeCancleBarterTrade(@org.jetbrains.annotations.Nullable
    java.lang.Long barterRequestIdx) {
    }
    
    public final void fetchBarterConfirmItems(long barterIdx) {
    }
    
    public final void barterConfirm(long barterIdx, long userIdx, boolean confirm) {
    }
    
    public final void fetchPopularBarteritems() {
    }
}