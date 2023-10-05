package com.example.project.di;

import com.example.project.api.BarterService;
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
public final class NetworkModule_ProvideBarterServiceFactory implements Factory<BarterService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideBarterServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public BarterService get() {
    return provideBarterService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideBarterServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideBarterServiceFactory(retrofitProvider);
  }

  public static BarterService provideBarterService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideBarterService(retrofit));
  }
}
