package com.example.project.di;

import com.example.project.api.AuctionService;
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
public final class NetworkModule_ProvideAuctionServiceFactory implements Factory<AuctionService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideAuctionServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public AuctionService get() {
    return provideAuctionService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideAuctionServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideAuctionServiceFactory(retrofitProvider);
  }

  public static AuctionService provideAuctionService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideAuctionService(retrofit));
  }
}
