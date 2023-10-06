package com.example.project.viewmodels;

import com.example.project.api.SignupService;
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
public final class SignupViewModel_Factory implements Factory<SignupViewModel> {
  private final Provider<SignupService> serviceProvider;

  private final Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider;

  public SignupViewModel_Factory(Provider<SignupService> serviceProvider,
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    this.serviceProvider = serviceProvider;
    this.sharedPreferencesUtilProvider = sharedPreferencesUtilProvider;
  }

  @Override
  public SignupViewModel get() {
    return newInstance(serviceProvider.get(), sharedPreferencesUtilProvider.get());
  }

  public static SignupViewModel_Factory create(Provider<SignupService> serviceProvider,
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    return new SignupViewModel_Factory(serviceProvider, sharedPreferencesUtilProvider);
  }

  public static SignupViewModel newInstance(SignupService service,
      SharedPreferencesUtil sharedPreferencesUtil) {
    return new SignupViewModel(service, sharedPreferencesUtil);
  }
}
