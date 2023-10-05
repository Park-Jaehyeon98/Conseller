package com.example.project.viewmodels;

import com.example.project.api.StoreService;
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
public final class StoreViewModel_Factory implements Factory<StoreViewModel> {
  private final Provider<StoreService> serviceProvider;

  private final Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider;

  public StoreViewModel_Factory(Provider<StoreService> serviceProvider,
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    this.serviceProvider = serviceProvider;
    this.sharedPreferencesUtilProvider = sharedPreferencesUtilProvider;
  }

  @Override
  public StoreViewModel get() {
    return newInstance(serviceProvider.get(), sharedPreferencesUtilProvider.get());
  }

  public static StoreViewModel_Factory create(Provider<StoreService> serviceProvider,
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    return new StoreViewModel_Factory(serviceProvider, sharedPreferencesUtilProvider);
  }

  public static StoreViewModel newInstance(StoreService service,
      SharedPreferencesUtil sharedPreferencesUtil) {
    return new StoreViewModel(service, sharedPreferencesUtil);
  }
}
