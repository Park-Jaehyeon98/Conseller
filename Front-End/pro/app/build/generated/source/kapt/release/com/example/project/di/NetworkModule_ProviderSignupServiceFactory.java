package com.example.project.di;

import com.example.project.api.SignupService;
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
public final class NetworkModule_ProviderSignupServiceFactory implements Factory<SignupService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProviderSignupServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public SignupService get() {
    return providerSignupService(retrofitProvider.get());
  }

  public static NetworkModule_ProviderSignupServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProviderSignupServiceFactory(retrofitProvider);
  }

  public static SignupService providerSignupService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.providerSignupService(retrofit));
  }
}
