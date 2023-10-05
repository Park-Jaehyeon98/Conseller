package com.example.project.di;

import com.example.project.sharedpreferences.SharedPreferencesUtil;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class NetworkModule_ProvideOkHttpClientFactory implements Factory<OkHttpClient> {
  private final Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider;

  public NetworkModule_ProvideOkHttpClientFactory(
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    this.sharedPreferencesUtilProvider = sharedPreferencesUtilProvider;
  }

  @Override
  public OkHttpClient get() {
    return provideOkHttpClient(sharedPreferencesUtilProvider.get());
  }

  public static NetworkModule_ProvideOkHttpClientFactory create(
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    return new NetworkModule_ProvideOkHttpClientFactory(sharedPreferencesUtilProvider);
  }

  public static OkHttpClient provideOkHttpClient(SharedPreferencesUtil sharedPreferencesUtil) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOkHttpClient(sharedPreferencesUtil));
  }
}
