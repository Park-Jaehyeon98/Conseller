package com.example.project.di;

import com.example.project.api.MyPageService;
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
public final class NetworkModule_ProviderMyPageServiceFactory implements Factory<MyPageService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProviderMyPageServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public MyPageService get() {
    return providerMyPageService(retrofitProvider.get());
  }

  public static NetworkModule_ProviderMyPageServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProviderMyPageServiceFactory(retrofitProvider);
  }

  public static MyPageService providerMyPageService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.providerMyPageService(retrofit));
  }
}
