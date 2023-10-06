package com.example.project.di;

import com.example.project.api.EventService;
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
public final class NetworkModule_ProvideEventServiceFactory implements Factory<EventService> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideEventServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public EventService get() {
    return provideEventService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideEventServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideEventServiceFactory(retrofitProvider);
  }

  public static EventService provideEventService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideEventService(retrofit));
  }
}
