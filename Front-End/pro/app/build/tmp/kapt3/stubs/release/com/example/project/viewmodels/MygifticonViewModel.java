package com.example.project.viewmodels;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.example.project.api.GifticonRequestDTO;
import com.example.project.api.MyService;
import com.example.project.api.OcrService;
import com.example.project.api.UploadGifticonResponse;
import com.example.project.api.uploadImageResponse;
import com.example.project.di.CustomException;
import com.example.project.sharedpreferences.SharedPreferencesUtil;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import okhttp3.MultipartBody;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0012J\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00100\u000bJ\u000e\u0010$\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0012J\u0006\u0010%\u001a\u00020 J\u000e\u0010&\u001a\u00020 2\u0006\u0010\'\u001a\u00020\u0010R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u00158F\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000b0\u00158F\u00a2\u0006\u0006\u001a\u0004\b\u001c\u0010\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00120\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017\u00a8\u0006("}, d2 = {"Lcom/example/project/viewmodels/MygifticonViewModel;", "Landroidx/lifecycle/ViewModel;", "myService", "Lcom/example/project/api/MyService;", "sharedPreferencesUtil", "Lcom/example/project/sharedpreferences/SharedPreferencesUtil;", "(Lcom/example/project/api/MyService;Lcom/example/project/sharedpreferences/SharedPreferencesUtil;)V", "_error", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_gifticonItems", "", "Lcom/example/project/viewmodels/GifticonData;", "_isLoading", "", "_selectedItemIndices", "", "_totalItems", "", "currentPage", "error", "Lkotlinx/coroutines/flow/StateFlow;", "getError", "()Lkotlinx/coroutines/flow/StateFlow;", "gifticonItems", "getGifticonItems", "isLoading", "selectedItemIndices", "getSelectedItemIndices", "totalItems", "getTotalItems", "changePage", "", "page", "getSelectedItems", "indices", "getUserGifticons", "resetSelectedItemIndices", "toggleSelection", "itemId", "app_release"})
public final class MygifticonViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.project.api.MyService myService = null;
    private final com.example.project.sharedpreferences.SharedPreferencesUtil sharedPreferencesUtil = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.example.project.viewmodels.GifticonData>> _gifticonItems = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _totalItems = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> totalItems = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _error = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> error = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<java.lang.Long>> _selectedItemIndices = null;
    private int currentPage = 1;
    
    @javax.inject.Inject
    public MygifticonViewModel(@org.jetbrains.annotations.NotNull
    com.example.project.api.MyService myService, @org.jetbrains.annotations.NotNull
    com.example.project.sharedpreferences.SharedPreferencesUtil sharedPreferencesUtil) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.project.viewmodels.GifticonData>> getGifticonItems() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getTotalItems() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getError() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.Long>> getSelectedItemIndices() {
        return null;
    }
    
    public final void resetSelectedItemIndices() {
    }
    
    public final void changePage(int page) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.project.viewmodels.GifticonData> getSelectedItems(@org.jetbrains.annotations.NotNull
    java.util.List<java.lang.Long> indices) {
        return null;
    }
    
    public final void getUserGifticons(int page) {
    }
    
    public final void toggleSelection(long itemId) {
    }
}