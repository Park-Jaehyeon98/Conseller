package com.example.project.viewmodels;

import com.example.project.api.AuctionService;
import com.example.project.sharedpreferences.SharedPreferencesUtil;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class AuctionViewModel_Factory implements Factory<AuctionViewModel> {
  private final Provider<AuctionService> serviceProvider;

  private final Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider;

  public AuctionViewModel_Factory(Provider<AuctionService> serviceProvider,
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    this.serviceProvider = serviceProvider;
    this.sharedPreferencesUtilProvider = sharedPreferencesUtilProvider;
  }

  @Override
  public AuctionViewModel get() {
    return newInstance(serviceProvider.get(), sharedPreferencesUtilProvider.get());
  }

  public static AuctionViewModel_Factory create(Provider<AuctionService> serviceProvider,
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    return new AuctionViewModel_Factory(serviceProvider, sharedPreferencesUtilProvider);
  }

  public static AuctionViewModel newInstance(AuctionService service,
      SharedPreferencesUtil sharedPreferencesUtil) {
    return new AuctionViewModel(service, sharedPreferencesUtil);
  }
}
