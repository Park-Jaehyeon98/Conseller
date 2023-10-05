package com.example.project.viewmodels;

import com.example.project.api.MyService;
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
public final class MyAuctionViewModel_Factory implements Factory<MyAuctionViewModel> {
  private final Provider<MyService> serviceProvider;

  private final Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider;

  public MyAuctionViewModel_Factory(Provider<MyService> serviceProvider,
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    this.serviceProvider = serviceProvider;
    this.sharedPreferencesUtilProvider = sharedPreferencesUtilProvider;
  }

  @Override
  public MyAuctionViewModel get() {
    return newInstance(serviceProvider.get(), sharedPreferencesUtilProvider.get());
  }

  public static MyAuctionViewModel_Factory create(Provider<MyService> serviceProvider,
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    return new MyAuctionViewModel_Factory(serviceProvider, sharedPreferencesUtilProvider);
  }

  public static MyAuctionViewModel newInstance(MyService service,
      SharedPreferencesUtil sharedPreferencesUtil) {
    return new MyAuctionViewModel(service, sharedPreferencesUtil);
  }
}
