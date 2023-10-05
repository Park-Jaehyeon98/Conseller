package com.example.project.di;

import com.example.project.api.MyService;
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
public final class NetworkModule_ProvideMyServiceFactory implements Factory<MyService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideMyServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public MyService get() {
    return provideMyService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideMyServiceFactory create(Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideMyServiceFactory(retrofitProvider);
  }

  public static MyService provideMyService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideMyService(retrofit));
  }
}
