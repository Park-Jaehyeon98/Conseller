package com.example.project.viewmodels;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.example.project.api.AuctionBidRequestDTO;
import com.example.project.api.AuctionConfirmBuyPageResponseDTO;
import com.example.project.api.AuctionConfirmPageResponseDTO;
import com.example.project.api.AuctionConfirmRequestDTO;
import com.example.project.api.AuctionDetailResponseDTO;
import com.example.project.api.AuctionFilterDTO;
import com.example.project.api.AuctionService;
import com.example.project.api.AuctionTradeResponseDTO;
import com.example.project.api.RegisterAuctionDTO;
import com.example.project.api.StoreConfirmPageResponseDTO;
import com.example.project.api.StoreConfirmRequestDTO;
import com.example.project.api.UpdateAuctionDTO;
import com.example.project.di.CustomException;
import com.example.project.sharedpreferences.SharedPreferencesUtil;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import java.io.IOException;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u001e\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020<J\u0016\u0010#\u001a\u00020J2\u0006\u0010L\u001a\u00020 2\u0006\u0010M\u001a\u00020\rJ\u0016\u0010N\u001a\u00020J2\u0006\u0010L\u001a\u00020 2\u0006\u0010O\u001a\u00020\u0015J\u000e\u0010P\u001a\u00020J2\u0006\u0010L\u001a\u00020 J\u000e\u0010Q\u001a\u00020J2\u0006\u0010R\u001a\u00020\u0015J\u000e\u0010S\u001a\u00020J2\u0006\u0010L\u001a\u00020 J\u000e\u0010T\u001a\u00020J2\u0006\u0010L\u001a\u00020 J\u000e\u0010U\u001a\u00020J2\u0006\u0010L\u001a\u00020 J\u000e\u0010V\u001a\u00020J2\u0006\u0010L\u001a\u00020 J\u000e\u0010W\u001a\u00020J2\u0006\u0010L\u001a\u00020 J\u000e\u0010X\u001a\u00020J2\u0006\u0010L\u001a\u00020 J\u0006\u0010Y\u001a\u00020JJ\u0006\u0010Z\u001a\u00020JJ\u0006\u0010[\u001a\u00020JJ\u0006\u0010\\\u001a\u00020JJ\u0006\u0010]\u001a\u00020 J&\u0010^\u001a\u00020J2\u0006\u0010_\u001a\u00020\u00152\u0006\u0010`\u001a\u00020\u00152\u0006\u0010a\u001a\u00020\u001c2\u0006\u0010b\u001a\u00020 J\u0006\u0010c\u001a\u00020JJ\u000e\u0010d\u001a\u00020J2\u0006\u0010e\u001a\u00020\u001cJ\u001e\u0010f\u001a\u00020J2\u0006\u0010L\u001a\u00020 2\u0006\u0010g\u001a\u00020\u001c2\u0006\u0010a\u001a\u00020\u001cR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00120\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00120\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\r0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\r0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010 0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00150\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\t0$\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0019\u0010\'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0$\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010&R\u0019\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0$\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010&R\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\r0$\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010&R\u0019\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100$\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010&R\u001d\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120$\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010&R\u001d\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00120$8F\u00a2\u0006\u0006\u001a\u0004\b2\u0010&R\u001d\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120$\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010&R\u001d\u00105\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00120$8F\u00a2\u0006\u0006\u001a\u0004\b6\u0010&R\u0019\u00107\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190$\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010&R\u0017\u00109\u001a\b\u0012\u0004\u0012\u00020\r0$8F\u00a2\u0006\u0006\u001a\u0004\b:\u0010&R\u000e\u0010;\u001a\u00020<X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0019\u0010>\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0$\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010&R\u0017\u0010@\u001a\b\u0012\u0004\u0012\u00020\r0$\u00a2\u0006\b\n\u0000\u001a\u0004\b@\u0010&R\u001d\u0010A\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120$\u00a2\u0006\b\n\u0000\u001a\u0004\bB\u0010&R\u0019\u0010C\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010 0$\u00a2\u0006\b\n\u0000\u001a\u0004\bD\u0010&R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00150$\u00a2\u0006\b\n\u0000\u001a\u0004\bF\u0010&R\u0019\u0010G\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0$\u00a2\u0006\b\n\u0000\u001a\u0004\bH\u0010&\u00a8\u0006h"}, d2 = {"Lcom/example/project/viewmodels/AuctionViewModel;", "Landroidx/lifecycle/ViewModel;", "service", "Lcom/example/project/api/AuctionService;", "sharedPreferencesUtil", "Lcom/example/project/sharedpreferences/SharedPreferencesUtil;", "(Lcom/example/project/api/AuctionService;Lcom/example/project/sharedpreferences/SharedPreferencesUtil;)V", "_auctionConfirm", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/project/api/AuctionConfirmPageResponseDTO;", "_auctionConfirmBuy", "Lcom/example/project/api/AuctionConfirmBuyPageResponseDTO;", "_auctionConfirmBuyNavi", "", "_auctionConfirmNavi", "_auctionDetail", "Lcom/example/project/api/AuctionDetailResponseDTO;", "_auctionItems", "", "Lcom/example/project/viewmodels/AuctionItemData;", "_auctionMainResponse", "", "_auctionPopular", "_auctionSubResponse", "_auctionTrade", "Lcom/example/project/api/AuctionTradeResponseDTO;", "_cancelTradeSuccessful", "_error", "", "_isLoading", "_myAuctionItems", "_navigateToAuctionDetail", "", "_totalItems", "_updateAuctionNavi", "auctionConfirm", "Lkotlinx/coroutines/flow/StateFlow;", "getAuctionConfirm", "()Lkotlinx/coroutines/flow/StateFlow;", "auctionConfirmBuy", "getAuctionConfirmBuy", "auctionConfirmBuyNavi", "getAuctionConfirmBuyNavi", "auctionConfirmNavi", "getAuctionConfirmNavi", "auctionDetail", "getAuctionDetail", "auctionItems", "getAuctionItems", "auctionMainResponse", "getAuctionMainResponse", "auctionPopular", "getAuctionPopular", "auctionSubResponse", "getAuctionSubResponse", "auctionTrades", "getAuctionTrades", "cancelTradeSuccessful", "getCancelTradeSuccessful", "currentFilter", "Lcom/example/project/api/AuctionFilterDTO;", "currentPage", "error", "getError", "isLoading", "myAuctionItems", "getMyAuctionItems", "navigateToAuctionDetail", "getNavigateToAuctionDetail", "totalItems", "getTotalItems", "updateAuctionNavi", "getUpdateAuctionNavi", "applyFilter", "", "filter", "auctionIdx", "confirm", "bidOnAuction", "bidPrice", "cancelAuctionTrade", "changePage", "page", "completeAuctionPayment", "deleteAuctionItem", "fetchAccountDetails", "fetchAuctionConfirmBuyItems", "fetchAuctionConfirmItems", "fetchAuctionDetail", "fetchAuctionItems", "fetchPopularAuctionMain", "fetchPopularAuctionSub", "fetchPopularAuctionitems", "getUserIdFromPreference", "registerAuctionItem", "upperLimit", "lowerLimit", "postContent", "gifticonIdx", "resetNavigation", "searchItems", "query", "updateAuctionItem", "endDate", "app_debug"})
public final class AuctionViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.project.api.AuctionService service = null;
    private final com.example.project.sharedpreferences.SharedPreferencesUtil sharedPreferencesUtil = null;
    private int currentPage = 1;
    private com.example.project.api.AuctionFilterDTO currentFilter;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.project.viewmodels.AuctionItemData>> _auctionItems = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.project.viewmodels.AuctionItemData>> auctionItems = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _totalItems = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> totalItems = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _error = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> error = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.project.api.AuctionDetailResponseDTO> _auctionDetail = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.example.project.api.AuctionDetailResponseDTO> auctionDetail = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.project.viewmodels.AuctionItemData>> _myAuctionItems = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.project.viewmodels.AuctionItemData>> myAuctionItems = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Long> _navigateToAuctionDetail = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Long> navigateToAuctionDetail = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _updateAuctionNavi = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> updateAuctionNavi = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.project.api.AuctionTradeResponseDTO> _auctionTrade = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.example.project.api.AuctionTradeResponseDTO> auctionTrades = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _cancelTradeSuccessful = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<java.lang.Integer>> _auctionMainResponse = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<java.lang.Integer>> _auctionSubResponse = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.project.viewmodels.AuctionItemData>> _auctionPopular = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.project.viewmodels.AuctionItemData>> auctionPopular = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.project.api.AuctionConfirmPageResponseDTO> _auctionConfirm = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.example.project.api.AuctionConfirmPageResponseDTO> auctionConfirm = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _auctionConfirmNavi = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> auctionConfirmNavi = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.project.api.AuctionConfirmBuyPageResponseDTO> _auctionConfirmBuy = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.example.project.api.AuctionConfirmBuyPageResponseDTO> auctionConfirmBuy = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _auctionConfirmBuyNavi = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> auctionConfirmBuyNavi = null;
    
    @javax.inject.Inject
    public AuctionViewModel(@org.jetbrains.annotations.NotNull
    com.example.project.api.AuctionService service, @org.jetbrains.annotations.NotNull
    com.example.project.sharedpreferences.SharedPreferencesUtil sharedPreferencesUtil) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.project.viewmodels.AuctionItemData>> getAuctionItems() {
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
    public final kotlinx.coroutines.flow.StateFlow<com.example.project.api.AuctionDetailResponseDTO> getAuctionDetail() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.project.viewmodels.AuctionItemData>> getMyAuctionItems() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Long> getNavigateToAuctionDetail() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getUpdateAuctionNavi() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.project.api.AuctionTradeResponseDTO> getAuctionTrades() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getCancelTradeSuccessful() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.Integer>> getAuctionMainResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.Integer>> getAuctionSubResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.project.viewmodels.AuctionItemData>> getAuctionPopular() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.project.api.AuctionConfirmPageResponseDTO> getAuctionConfirm() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getAuctionConfirmNavi() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.project.api.AuctionConfirmBuyPageResponseDTO> getAuctionConfirmBuy() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getAuctionConfirmBuyNavi() {
        return null;
    }
    
    public final void resetNavigation() {
    }
    
    public final void changePage(int page) {
    }
    
    public final void applyFilter(@org.jetbrains.annotations.NotNull
    com.example.project.api.AuctionFilterDTO filter) {
    }
    
    public final void searchItems(@org.jetbrains.annotations.NotNull
    java.lang.String query) {
    }
    
    public final void fetchPopularAuctionMain() {
    }
    
    public final void fetchPopularAuctionSub() {
    }
    
    public final void fetchPopularAuctionitems() {
    }
    
    public final void fetchAuctionItems() {
    }
    
    public final void registerAuctionItem(int upperLimit, int lowerLimit, @org.jetbrains.annotations.NotNull
    java.lang.String postContent, long gifticonIdx) {
    }
    
    public final void updateAuctionItem(long auctionIdx, @org.jetbrains.annotations.NotNull
    java.lang.String endDate, @org.jetbrains.annotations.NotNull
    java.lang.String postContent) {
    }
    
    public final void deleteAuctionItem(long auctionIdx) {
    }
    
    public final void fetchAuctionDetail(long auctionIdx) {
    }
    
    public final void bidOnAuction(long auctionIdx, int bidPrice) {
    }
    
    public final void fetchAccountDetails(long auctionIdx) {
    }
    
    public final void cancelAuctionTrade(long auctionIdx) {
    }
    
    public final void completeAuctionPayment(long auctionIdx) {
    }
    
    public final void fetchAuctionConfirmItems(long auctionIdx) {
    }
    
    public final void auctionConfirm(long auctionIdx, boolean confirm) {
    }
    
    public final void fetchAuctionConfirmBuyItems(long auctionIdx) {
    }
}