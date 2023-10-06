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

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016\u00a8\u0006\u0007"}, d2 = {"Lcom/example/project/di/ResponseInterceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "app_release"})
public final class ResponseInterceptor implements okhttp3.Interceptor {
    
    public ResponseInterceptor() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public okhttp3.Response intercept(@org.jetbrains.annotations.NotNull
    okhttp3.Interceptor.Chain chain) {
        return null;
    }
}