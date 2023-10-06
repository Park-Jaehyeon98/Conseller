package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Modifier;
import androidx.navigation.NavHostController;
import com.example.project.sharedpreferences.SharedPreferencesUtil;
import com.example.project.viewmodels.MygifticonViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\u0012\u0010\u0012\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0015"}, d2 = {"Lcom/example/project/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "myGifticonViewModel", "Lcom/example/project/viewmodels/MygifticonViewModel;", "getMyGifticonViewModel", "()Lcom/example/project/viewmodels/MygifticonViewModel;", "myGifticonViewModel$delegate", "Lkotlin/Lazy;", "sharedPreferencesUtil", "Lcom/example/project/sharedpreferences/SharedPreferencesUtil;", "getSharedPreferencesUtil", "()Lcom/example/project/sharedpreferences/SharedPreferencesUtil;", "sharedPreferencesUtil$delegate", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onNewIntent", "intent", "Landroid/content/Intent;", "app_debug"})
@dagger.hilt.android.AndroidEntryPoint
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private final kotlin.Lazy sharedPreferencesUtil$delegate = null;
    private final kotlin.Lazy myGifticonViewModel$delegate = null;
    
    public MainActivity() {
        super();
    }
    
    private final com.example.project.sharedpreferences.SharedPreferencesUtil getSharedPreferencesUtil() {
        return null;
    }
    
    private final com.example.project.viewmodels.MygifticonViewModel getMyGifticonViewModel() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    protected void onNewIntent(@org.jetbrains.annotations.Nullable
    android.content.Intent intent) {
    }
}