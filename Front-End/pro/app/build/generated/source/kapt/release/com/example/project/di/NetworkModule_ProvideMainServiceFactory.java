package com.example.project.di;

import com.example.project.api.MainService;
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
public final class NetworkModule_ProvideMainServiceFactory implements Factory<MainService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideMainServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public MainService get() {
    return provideMainService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideMainServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideMainServiceFactory(retrofitProvider);
  }

  public static MainService provideMainService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideMainService(retrofit));
  }
}
