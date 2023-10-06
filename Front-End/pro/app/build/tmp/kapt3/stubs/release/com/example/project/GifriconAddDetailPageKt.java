package com.example.project;

import android.net.Uri;
import android.util.Base64;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.CardDefaults;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.style.TextAlign;
import androidx.navigation.NavHostController;
import com.example.project.api.UploadGifticonResponse;
import com.example.project.api.userUploadGifticonResponse;
import com.example.project.viewmodels.MyPageViewModel;
import com.example.project.viewmodels.OcrViewModel;
import com.google.gson.Gson;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@kotlin.Metadata(mv = {1, 8, 0}, k = 2, d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a@\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00032\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\t\u001a\u00020\u00032\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a,\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a\u0010\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0013H\u0007\u001a\u0012\u0010\u0014\u001a\u00020\u00012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0007\u001a\u0010\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\rH\u0007\u001a0\u0010\u0019\u001a\u00020\u00012\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a0\u0010\u001d\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u00032\u000e\b\u0002\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00010!H\u0007\u001a$\u0010\"\u001a\u00020\u00012\u0006\u0010#\u001a\u00020\u00032\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u00a8\u0006%"}, d2 = {"ChoiceCategory", "", "category", "", "onCategorySet", "Lkotlin/Function1;", "ChoiceMainCategory", "MainCategory", "onMainCategorySet", "subCategory", "onSubCategorySet", "CustomGiftTextField", "label", "", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "GifticonAddDetailPage", "navController", "Landroidx/navigation/NavHostController;", "GifticonCheck", "imageView", "Landroid/net/Uri;", "GifticonRegistrationCompleteMessage", "gifticonName", "GifticonUpload", "onImageSelected", "setSendState", "", "NormalButton", "buttonSize", "textSize", "onClick", "Lkotlin/Function0;", "pagechanger", "currentPage", "onSetPage", "app_release"})
public final class GifriconAddDetailPageKt {
    
    @androidx.compose.runtime.Composable
    public static final void GifticonAddDetailPage(@org.jetbrains.annotations.NotNull
    androidx.navigation.NavHostController navController) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void CustomGiftTextField(@org.jetbrains.annotations.NotNull
    java.lang.String label, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.text.input.TextFieldValue value, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.input.TextFieldValue, kotlin.Unit> onValueChange) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void NormalButton(@org.jetbrains.annotations.NotNull
    java.lang.String label, int buttonSize, int textSize, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void ChoiceMainCategory(int MainCategory, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onMainCategorySet, int subCategory, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onSubCategorySet) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void ChoiceCategory(int category, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onCategorySet) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void GifticonUpload(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super android.net.Uri, kotlin.Unit> onImageSelected, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> setSendState) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void GifticonCheck(@org.jetbrains.annotations.Nullable
    android.net.Uri imageView) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void pagechanger(int currentPage, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onSetPage) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void GifticonRegistrationCompleteMessage(@org.jetbrains.annotations.NotNull
    java.lang.String gifticonName) {
    }
}