package com.example.project.viewmodels;

import com.example.project.api.ApiService;
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
public final class BiometricViewModel_Factory implements Factory<BiometricViewModel> {
  private final Provider<ApiService> apiServiceProvider;

  private final Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider;

  public BiometricViewModel_Factory(Provider<ApiService> apiServiceProvider,
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.sharedPreferencesUtilProvider = sharedPreferencesUtilProvider;
  }

  @Override
  public BiometricViewModel get() {
    return newInstance(apiServiceProvider.get(), sharedPreferencesUtilProvider.get());
  }

  public static BiometricViewModel_Factory create(Provider<ApiService> apiServiceProvider,
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    return new BiometricViewModel_Factory(apiServiceProvider, sharedPreferencesUtilProvider);
  }

  public static BiometricViewModel newInstance(ApiService apiService,
      SharedPreferencesUtil sharedPreferencesUtil) {
    return new BiometricViewModel(apiService, sharedPreferencesUtil);
  }
}
