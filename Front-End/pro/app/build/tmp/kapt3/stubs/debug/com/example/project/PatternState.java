package com.example.project;

import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.navigation.NavHostController;
import com.example.project.sharedpreferences.SharedPreferencesUtil;
import com.example.project.viewmodels.AuthenticationState;
import com.example.project.viewmodels.BiometricViewModel;
import com.mrhwsn.composelock.ComposeLockCallback;
import com.mrhwsn.composelock.Dot;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lcom/example/project/PatternState;", "", "()V", "Confirm", "Initial", "Matched", "Mismatch", "Lcom/example/project/PatternState$Confirm;", "Lcom/example/project/PatternState$Initial;", "Lcom/example/project/PatternState$Matched;", "Lcom/example/project/PatternState$Mismatch;", "app_debug"})
public abstract class PatternState {
    
    private PatternState() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/project/PatternState$Initial;", "Lcom/example/project/PatternState;", "()V", "app_debug"})
    public static final class Initial extends com.example.project.PatternState {
        @org.jetbrains.annotations.NotNull
        public static final com.example.project.PatternState.Initial INSTANCE = null;
        
        private Initial() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/project/PatternState$Confirm;", "Lcom/example/project/PatternState;", "()V", "app_debug"})
    public static final class Confirm extends com.example.project.PatternState {
        @org.jetbrains.annotations.NotNull
        public static final com.example.project.PatternState.Confirm INSTANCE = null;
        
        private Confirm() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/project/PatternState$Mismatch;", "Lcom/example/project/PatternState;", "()V", "app_debug"})
    public static final class Mismatch extends com.example.project.PatternState {
        @org.jetbrains.annotations.NotNull
        public static final com.example.project.PatternState.Mismatch INSTANCE = null;
        
        private Mismatch() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/project/PatternState$Matched;", "Lcom/example/project/PatternState;", "()V", "app_debug"})
    public static final class Matched extends com.example.project.PatternState {
        @org.jetbrains.annotations.NotNull
        public static final com.example.project.PatternState.Matched INSTANCE = null;
        
        private Matched() {
            super();
        }
    }
}