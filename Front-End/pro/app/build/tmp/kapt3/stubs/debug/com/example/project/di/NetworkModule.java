package com.example.project.di;

import android.content.Context;
import com.example.project.api.ApiService;
import com.example.project.sharedpreferences.SharedPreferencesUtil;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import javax.inject.Singleton;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\u0006H\u0007\u00a8\u0006\b"}, d2 = {"Lcom/example/project/di/NetworkModule;", "", "()V", "provideApiService", "Lcom/example/project/api/ApiService;", "retrofit", "Lretrofit2/Retrofit;", "provideRetrofit", "app_debug"})
@dagger.Module
public final class NetworkModule {
    @org.jetbrains.annotations.NotNull
    public static final com.example.project.di.NetworkModule INSTANCE = null;
    
    private NetworkModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @javax.inject.Singleton
    @dagger.Provides
    public final retrofit2.Retrofit provideRetrofit() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @javax.inject.Singleton
    @dagger.Provides
    public final com.example.project.api.ApiService provideApiService(@org.jetbrains.annotations.NotNull
    retrofit2.Retrofit retrofit) {
        return null;
    }
}