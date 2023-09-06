package com.example.project.di

import android.content.Context
import com.example.project.api.AuctionService
import com.example.project.api.BarterService
import com.example.project.api.EventService
import com.example.project.api.LoginService
import com.example.project.api.MainService
import com.example.project.api.MyService
import com.example.project.api.ReuseService
import com.example.project.api.StoreService
import com.example.project.sharedpreferences.SharedPreferencesUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://your.api.url")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideLoginService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuctionService(retrofit: Retrofit): AuctionService {
        return retrofit.create(AuctionService::class.java)
    }

    @Provides
    @Singleton
    fun provideBarterService(retrofit: Retrofit): BarterService {
        return retrofit.create(BarterService::class.java)
    }

    @Provides
    @Singleton
    fun provideEventService(retrofit: Retrofit): EventService {
        return retrofit.create(EventService::class.java)
    }

    @Provides
    @Singleton
    fun provideMainService(retrofit: Retrofit): MainService {
        return retrofit.create(MainService::class.java)
    }

    @Provides
    @Singleton
    fun provideMyService(retrofit: Retrofit): MyService {
        return retrofit.create(MyService::class.java)
    }

    @Provides
    @Singleton
    fun provideStoreService(retrofit: Retrofit): StoreService {
        return retrofit.create(StoreService::class.java)
    }

    @Provides
    @Singleton
    fun provideReuseService(retrofit: Retrofit): ReuseService {
        return retrofit.create(ReuseService::class.java)
    }

}

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreferencesUtil(@ApplicationContext context: Context): SharedPreferencesUtil {
        return SharedPreferencesUtil(context)
    }
}
