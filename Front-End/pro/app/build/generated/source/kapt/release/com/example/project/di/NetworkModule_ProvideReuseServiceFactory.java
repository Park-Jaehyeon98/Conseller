package com.example.project.di;

import com.example.project.api.ReuseService;
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
public final class NetworkModule_ProvideReuseServiceFactory implements Factory<ReuseService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideReuseServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public ReuseService get() {
    return provideReuseService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideReuseServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideReuseServiceFactory(retrofitProvider);
  }

  public static ReuseService provideReuseService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideReuseService(retrofit));
  }
}
