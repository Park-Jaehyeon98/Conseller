package com.example.project.di;

import com.example.project.api.StoreService;
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
public final class NetworkModule_ProvideStoreServiceFactory implements Factory<StoreService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideStoreServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public StoreService get() {
    return provideStoreService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideStoreServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideStoreServiceFactory(retrofitProvider);
  }

  public static StoreService provideStoreService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideStoreService(retrofit));
  }
}
