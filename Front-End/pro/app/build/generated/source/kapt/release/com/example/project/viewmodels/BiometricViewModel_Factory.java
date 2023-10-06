package com.example.project.viewmodels;

import com.example.project.api.LoginService;
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
  private final Provider<LoginService> loginServiceProvider;

  private final Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider;

  public BiometricViewModel_Factory(Provider<LoginService> loginServiceProvider,
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    this.loginServiceProvider = loginServiceProvider;
    this.sharedPreferencesUtilProvider = sharedPreferencesUtilProvider;
  }

  @Override
  public BiometricViewModel get() {
    return newInstance(loginServiceProvider.get(), sharedPreferencesUtilProvider.get());
  }

  public static BiometricViewModel_Factory create(Provider<LoginService> loginServiceProvider,
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    return new BiometricViewModel_Factory(loginServiceProvider, sharedPreferencesUtilProvider);
  }

  public static BiometricViewModel newInstance(LoginService loginService,
      SharedPreferencesUtil sharedPreferencesUtil) {
    return new BiometricViewModel(loginService, sharedPreferencesUtil);
  }
}
