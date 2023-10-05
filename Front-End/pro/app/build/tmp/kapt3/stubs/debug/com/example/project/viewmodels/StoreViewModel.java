package com.example.project.viewmodels;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.example.project.api.*;
import com.example.project.di.CustomException;
import com.example.project.sharedpreferences.SharedPreferencesUtil;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u0002\n\u0002\b\u001a\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020$J\u000e\u0010B\u001a\u00020@2\u0006\u0010C\u001a\u00020\u0011J\u000e\u0010D\u001a\u00020@2\u0006\u0010E\u001a\u00020\u0019J\u000e\u0010F\u001a\u00020@2\u0006\u0010C\u001a\u00020\u0011J\u000e\u0010G\u001a\u00020@2\u0006\u0010C\u001a\u00020\u0011J\u000e\u0010H\u001a\u00020@2\u0006\u0010C\u001a\u00020\u0011J\u000e\u0010I\u001a\u00020@2\u0006\u0010J\u001a\u00020\u0011J\u0006\u0010K\u001a\u00020@J\u0006\u0010L\u001a\u00020@J\u000e\u0010M\u001a\u00020@2\u0006\u0010C\u001a\u00020\u0011J\u000e\u0010N\u001a\u00020@2\u0006\u0010C\u001a\u00020\u0011J\u0006\u0010O\u001a\u00020@J\u0006\u0010P\u001a\u00020\u0011J\u001e\u0010Q\u001a\u00020@2\u0006\u0010R\u001a\u00020\u00192\u0006\u0010S\u001a\u00020\u000b2\u0006\u0010T\u001a\u00020\u0011J\u0006\u0010U\u001a\u00020@J\u000e\u0010V\u001a\u00020@2\u0006\u0010W\u001a\u00020\u000bJ\u0016\u0010-\u001a\u00020@2\u0006\u0010C\u001a\u00020\u00112\u0006\u0010X\u001a\u00020\tJ\u001e\u0010Y\u001a\u00020@2\u0006\u0010C\u001a\u00020\u00112\u0006\u0010R\u001a\u00020\u00192\u0006\u0010S\u001a\u00020\u000bR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u000e0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u000e0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00110\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\t0 8F\u00a2\u0006\u0006\u001a\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0019\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0 \u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\"R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\t0 \u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\"R\u001d\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0 \u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\"R\u0019\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110 \u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\"R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130 \u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\"R\u0019\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0 \u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010\"R\u0019\u00101\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160 \u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010\"R\u001d\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0 \u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010\"R\u001d\u00105\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u000e0 8F\u00a2\u0006\u0006\u001a\u0004\b6\u0010\"R\u001d\u00107\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u000e0 8F\u00a2\u0006\u0006\u001a\u0004\b8\u0010\"R\u0019\u00109\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0 \u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010\"R\u0017\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00110 \u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010\"R\u0019\u0010=\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0 \u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010\"\u00a8\u0006Z"}, d2 = {"Lcom/example/project/viewmodels/StoreViewModel;", "Landroidx/lifecycle/ViewModel;", "service", "Lcom/example/project/api/StoreService;", "sharedPreferencesUtil", "Lcom/example/project/sharedpreferences/SharedPreferencesUtil;", "(Lcom/example/project/api/StoreService;Lcom/example/project/sharedpreferences/SharedPreferencesUtil;)V", "_cancelTradeSuccessful", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_error", "", "_isLoading", "_myStoreItems", "", "Lcom/example/project/viewmodels/StoreItemData;", "_navigateToStoreDetail", "", "_storeConfirm", "Lcom/example/project/api/StoreConfirmPageResponseDTO;", "_storeConfirmNavi", "_storeDetail", "Lcom/example/project/api/StoreDetailResponseDTO;", "_storeItems", "_storeMainResponse", "", "_storeSubResponse", "_storeTrade", "Lcom/example/project/api/StoreTradeResponseDTO;", "_totalItems", "_updateStoreNavi", "cancelTradeSuccessful", "Lkotlinx/coroutines/flow/StateFlow;", "getCancelTradeSuccessful", "()Lkotlinx/coroutines/flow/StateFlow;", "currentFilter", "Lcom/example/project/api/StoreFilterDTO;", "currentPage", "error", "getError", "isLoading", "myStoreItems", "getMyStoreItems", "navigateToStoreDetail", "getNavigateToStoreDetail", "storeConfirm", "getStoreConfirm", "storeConfirmNavi", "getStoreConfirmNavi", "storeDetail", "getStoreDetail", "storeItems", "getStoreItems", "storeMainResponse", "getStoreMainResponse", "storeSubResponse", "getStoreSubResponse", "storeTrades", "getStoreTrades", "totalItems", "getTotalItems", "updateStoreNavi", "getUpdateStoreNavi", "applyFilter", "", "filter", "cancelStoreTrade", "storeIdx", "changePage", "page", "completeStorePayment", "deleteStoreItem", "fetchAccountDetails", "fetchMyStoreItems", "userIdx", "fetchPopularStoreMain", "fetchPopularStoreSub", "fetchStoreConfirmItems", "fetchStoreDetail", "fetchStoreItems", "getUserIdFromPreference", "registerStoreItem", "storePrice", "storeText", "gifticonIdx", "resetNavigation", "searchItems", "query", "confirm", "updateStoreItem", "app_debug"})
public final class StoreViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.project.api.StoreService service = null;
    private final com.example.project.sharedpreferences.SharedPreferencesUtil sharedPreferencesUtil = null;
    private int currentPage = 1;
    private com.example.project.api.StoreFilterDTO currentFilter;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.project.viewmodels.StoreItemData>> _storeItems = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.project.viewmodels.StoreItemData>> storeItems = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Long> _totalItems = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Long> totalItems = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _error = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> error = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.project.api.StoreDetailResponseDTO> _storeDetail = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.example.project.api.StoreDetailResponseDTO> storeDetail = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.project.viewmodels.StoreItemData>> _myStoreItems = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.project.viewmodels.StoreItemData>> myStoreItems = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Long> _navigateToStoreDetail = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Long> navigateToStoreDetail = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _updateStoreNavi = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> updateStoreNavi = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.project.api.StoreTradeResponseDTO> _storeTrade = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.example.project.api.StoreTradeResponseDTO> storeTrades = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _cancelTradeSuccessful = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.project.api.StoreConfirmPageResponseDTO> _storeConfirm = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.example.project.api.StoreConfirmPageResponseDTO> storeConfirm = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _storeConfirmNavi = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> storeConfirmNavi = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<java.lang.Integer>> _storeMainResponse = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<java.lang.Integer>> _storeSubResponse = null;
    
    @javax.inject.Inject
    public StoreViewModel(@org.jetbrains.annotations.NotNull
    com.example.project.api.StoreService service, @org.jetbrains.annotations.NotNull
    com.example.project.sharedpreferences.SharedPreferencesUtil sharedPreferencesUtil) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.project.viewmodels.StoreItemData>> getStoreItems() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Long> getTotalItems() {
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
    public final kotlinx.coroutines.flow.StateFlow<com.example.project.api.StoreDetailResponseDTO> getStoreDetail() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.project.viewmodels.StoreItemData>> getMyStoreItems() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Long> getNavigateToStoreDetail() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getUpdateStoreNavi() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.project.api.StoreTradeResponseDTO> getStoreTrades() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getCancelTradeSuccessful() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.project.api.StoreConfirmPageResponseDTO> getStoreConfirm() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getStoreConfirmNavi() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.Integer>> getStoreMainResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.Integer>> getStoreSubResponse() {
        return null;
    }
    
    public final void resetNavigation() {
    }
    
    public final void changePage(int page) {
    }
    
    public final void applyFilter(@org.jetbrains.annotations.NotNull
    com.example.project.api.StoreFilterDTO filter) {
    }
    
    public final void searchItems(@org.jetbrains.annotations.NotNull
    java.lang.String query) {
    }
    
    public final void fetchStoreItems() {
    }
    
    public final void fetchPopularStoreMain() {
    }
    
    public final void fetchPopularStoreSub() {
    }
    
    public final void registerStoreItem(int storePrice, @org.jetbrains.annotations.NotNull
    java.lang.String storeText, long gifticonIdx) {
    }
    
    public final void updateStoreItem(long storeIdx, int storePrice, @org.jetbrains.annotations.NotNull
    java.lang.String storeText) {
    }
    
    public final void deleteStoreItem(long storeIdx) {
    }
    
    public final void fetchStoreDetail(long storeIdx) {
    }
    
    public final void fetchMyStoreItems(long userIdx) {
    }
    
    public final void fetchAccountDetails(long storeIdx) {
    }
    
    public final void cancelStoreTrade(long storeIdx) {
    }
    
    public final void completeStorePayment(long storeIdx) {
    }
    
    public final void fetchStoreConfirmItems(long storeIdx) {
    }
    
    public final void storeConfirm(long storeIdx, boolean confirm) {
    }
}