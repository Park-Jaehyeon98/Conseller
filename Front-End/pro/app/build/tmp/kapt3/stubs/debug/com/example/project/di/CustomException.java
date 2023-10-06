package com.example.project.di;

import android.content.Context;
import android.util.Log;
import com.example.project.api.AuctionService;
import com.example.project.api.BarterService;
import com.example.project.api.EventService;
import com.example.project.api.LoginService;
import com.example.project.api.MainService;
import com.example.project.api.MyPageService;
import com.example.project.api.MyService;
import com.example.project.api.OcrService;
import com.example.project.api.ReuseService;
import com.example.project.api.SignupService;
import com.example.project.api.StoreService;
import com.example.project.sharedpreferences.SharedPreferencesUtil;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;
import javax.inject.Singleton;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/example/project/di/CustomException;", "Ljava/io/IOException;", "message", "", "(Ljava/lang/String;)V", "app_debug"})
public final class CustomException extends java.io.IOException {
    
    public CustomException(@org.jetbrains.annotations.NotNull
    java.lang.String message) {
        super();
    }
}