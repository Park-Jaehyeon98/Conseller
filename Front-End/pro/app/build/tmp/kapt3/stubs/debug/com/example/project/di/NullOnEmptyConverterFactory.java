package com.example.project.di;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import java.lang.reflect.Type;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J7\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0002\b\u0003\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016\u00a2\u0006\u0002\u0010\r\u00a8\u0006\u000e"}, d2 = {"Lcom/example/project/di/NullOnEmptyConverterFactory;", "Lretrofit2/Converter$Factory;", "()V", "responseBodyConverter", "Lretrofit2/Converter;", "Lokhttp3/ResponseBody;", "type", "Ljava/lang/reflect/Type;", "annotations", "", "", "retrofit", "Lretrofit2/Retrofit;", "(Ljava/lang/reflect/Type;[Ljava/lang/annotation/Annotation;Lretrofit2/Retrofit;)Lretrofit2/Converter;", "app_debug"})
public final class NullOnEmptyConverterFactory extends retrofit2.Converter.Factory {
    @org.jetbrains.annotations.NotNull
    public static final com.example.project.di.NullOnEmptyConverterFactory INSTANCE = null;
    
    private NullOnEmptyConverterFactory() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public retrofit2.Converter<okhttp3.ResponseBody, ?> responseBodyConverter(@org.jetbrains.annotations.NotNull
    java.lang.reflect.Type type, @org.jetbrains.annotations.NotNull
    java.lang.annotation.Annotation[] annotations, @org.jetbrains.annotations.NotNull
    retrofit2.Retrofit retrofit) {
        return null;
    }
}