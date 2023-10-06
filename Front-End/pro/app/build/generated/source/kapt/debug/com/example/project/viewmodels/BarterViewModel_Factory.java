package com.example.project.viewmodels;

import com.example.project.api.BarterService;
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
public final class BarterViewModel_Factory implements Factory<BarterViewModel> {
  private final Provider<BarterService> serviceProvider;

  private final Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider;

  public BarterViewModel_Factory(Provider<BarterService> serviceProvider,
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    this.serviceProvider = serviceProvider;
    this.sharedPreferencesUtilProvider = sharedPreferencesUtilProvider;
  }

  @Override
  public BarterViewModel get() {
    return newInstance(serviceProvider.get(), sharedPreferencesUtilProvider.get());
  }

  public static BarterViewModel_Factory create(Provider<BarterService> serviceProvider,
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    return new BarterViewModel_Factory(serviceProvider, sharedPreferencesUtilProvider);
  }

  public static BarterViewModel newInstance(BarterService service,
      SharedPreferencesUtil sharedPreferencesUtil) {
    return new BarterViewModel(service, sharedPreferencesUtil);
  }
}
