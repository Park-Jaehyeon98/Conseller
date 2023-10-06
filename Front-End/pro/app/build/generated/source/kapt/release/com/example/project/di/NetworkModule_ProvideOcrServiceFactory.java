package com.example.project.di;

import com.example.project.api.OcrService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class NetworkModule_ProvideOcrServiceFactory implements Factory<OcrService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideOcrServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public OcrService get() {
    return provideOcrService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideOcrServiceFactory create(Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideOcrServiceFactory(retrofitProvider);
  }

  public static OcrService provideOcrService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOcrService(retrofit));
  }
}
