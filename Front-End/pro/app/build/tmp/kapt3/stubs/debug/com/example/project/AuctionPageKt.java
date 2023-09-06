package com.example.project;

import androidx.compose.foundation.layout.*;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ExperimentalMaterial3Api;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ExperimentalComposeUiApi;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.LocalSoftwareKeyboardController;
import androidx.compose.ui.text.input.ImeAction;
import androidx.navigation.NavHostController;
import com.example.project.api.AuctionFilterDTO;
import com.example.project.viewmodels.AuctionViewModel;

@kotlin.Metadata(mv = {1, 8, 0}, k = 2, d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\u001aN\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0007\u001a\u0010\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0007\u001a2\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00032\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u0014H\u0007\u00a8\u0006\u0015"}, d2 = {"AuctionItem", "", "image", "", "name", "gifticonTime", "auctionTime", "popular", "upperprice", "nowprice", "onItemClick", "Lkotlin/Function0;", "AuctionPage", "navController", "Landroidx/navigation/NavHostController;", "FilterButton", "selectedOption", "options", "", "onOptionSelected", "Lkotlin/Function1;", "app_debug"})
public final class AuctionPageKt {
    
    @androidx.compose.runtime.Composable
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class, androidx.compose.ui.ExperimentalComposeUiApi.class})
    public static final void AuctionPage(@org.jetbrains.annotations.NotNull
    androidx.navigation.NavHostController navController) {
    }
    
    @androidx.compose.runtime.Composable
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    public static final void FilterButton(@org.jetbrains.annotations.NotNull
    java.lang.String selectedOption, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> options, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onOptionSelected) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void AuctionItem(@org.jetbrains.annotations.NotNull
    java.lang.String image, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String gifticonTime, @org.jetbrains.annotations.NotNull
    java.lang.String auctionTime, @org.jetbrains.annotations.NotNull
    java.lang.String popular, @org.jetbrains.annotations.NotNull
    java.lang.String upperprice, @org.jetbrains.annotations.NotNull
    java.lang.String nowprice, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onItemClick) {
    }
}