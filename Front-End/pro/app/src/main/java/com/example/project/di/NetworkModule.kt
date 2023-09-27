package com.example.project.di

import android.content.Context
import android.util.Log
import com.example.project.api.AuctionService
import com.example.project.api.BarterService
import com.example.project.api.EventService
import com.example.project.api.LoginService
import com.example.project.api.MainService
import com.example.project.api.MyPageService
import com.example.project.api.MyService
import com.example.project.api.OcrService
import com.example.project.api.ReuseService
import com.example.project.api.SignupService
import com.example.project.api.StoreService
import com.example.project.sharedpreferences.SharedPreferencesUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // 토큰이 생기면 밑에 provideRetrofit를 지우고 이거를 사용하면됨.
    // Retrofit 인스턴스에 OkHttpClient를 포함시켜 인터셉터를 추가하는 방식
    // 각 요청에 알아서 추가함.
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(sharedPreferencesUtil: SharedPreferencesUtil): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ResponseInterceptor())  //예외처리 인터셉터
            .addInterceptor { chain ->
                val token = sharedPreferencesUtil.getUserToken() // 이거 get이름바뀌면 바꾸고
                val request = chain.request().newBuilder()       // 토큰 형식 맞는지 확인하고
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(request)
            }
            .build()
    }
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://j9b207.p.ssafy.io/")
            .client(okHttpClient)
            .addConverterFactory(NullOnEmptyConverterFactory)
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

    @Provides
    @Singleton
    fun providerSignupService(retrofit: Retrofit): SignupService {
        return retrofit.create(SignupService::class.java)
    }

    @Provides
    @Singleton
    fun providerMyPageService(retrofit: Retrofit) : MyPageService{
        return retrofit.create(MyPageService::class.java)
    }

    @Provides
    @Singleton
    fun provideOcrService(retrofit: Retrofit): OcrService {
        return retrofit.create(OcrService::class.java)
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

class CustomException(message: String) : IOException(message)
class ResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        Log.d("@@@@@@@@@@@@@@@@1","$response")
        Log.d("@@@@@@@@@@@@@@@@2","${response.body}")
        Log.d("@@@@@@@@@@@@@@@@3","${response.code}")
        Log.d("@@@@@@@@@@@@@@@@4","${response.headers}")
        if (!response.isSuccessful) {
            when (response.code) {
                400 -> throw CustomException("잘못된 요청입니다.")
                401 -> throw CustomException("권한이 없습니다. 로그인 후 다시 시도해주세요.")
                403 -> throw CustomException("접근 권한이 제한되었습니다.")
                404 -> throw CustomException("요청하신 페이지를 찾을 수 없습니다.")
                405 -> throw CustomException("허용되지 않는 메서드입니다.")
                406 -> throw CustomException("허용되지 않는 요청입니다.")
                408 -> throw CustomException("요청 시간이 초과되었습니다.")
                413 -> throw CustomException("요청이 너무 큽니다.")
                415 -> throw CustomException("지원되지 않는 형식입니다.")
                429 -> throw CustomException("요청이 너무 많습니다. 잠시 후 다시 시도해주세요.")
                500 -> throw CustomException("서버 내부 오류가 발생했습니다.")
                502 -> throw CustomException("인터넷 연결이 원활하지 않습니다. 잠시 후 다시 시도해주세요.")
                503 -> throw CustomException("서비스를 사용할 수 없습니다. 잠시 후 다시 시도해주세요.")
                504 -> throw CustomException("인터넷 연결을 확인해주세요")
                507 -> throw CustomException("저장 공간이 부족합니다.")
                else -> throw CustomException("${response.code} : 알 수 없는 오류가 발생했습니다.")
            }
        }
        return response
    }
}
