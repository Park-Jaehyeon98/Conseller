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
public final class FireBaseViewModel_Factory implements Factory<FireBaseViewModel> {
  private final Provider<LoginService> serviceProvider;

  private final Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider;

  public FireBaseViewModel_Factory(Provider<LoginService> serviceProvider,
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    this.serviceProvider = serviceProvider;
    this.sharedPreferencesUtilProvider = sharedPreferencesUtilProvider;
  }

  @Override
  public FireBaseViewModel get() {
    return newInstance(serviceProvider.get(), sharedPreferencesUtilProvider.get());
  }

  public static FireBaseViewModel_Factory create(Provider<LoginService> serviceProvider,
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    return new FireBaseViewModel_Factory(serviceProvider, sharedPreferencesUtilProvider);
  }

  public static FireBaseViewModel newInstance(LoginService service,
      SharedPreferencesUtil sharedPreferencesUtil) {
    return new FireBaseViewModel(service, sharedPreferencesUtil);
  }
}
