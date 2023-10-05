package com.example.project.viewmodels;

import com.example.project.api.MyPageService;
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
public final class MyPageViewModel_Factory implements Factory<MyPageViewModel> {
  private final Provider<MyPageService> serviceProvider;

  private final Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider;

  public MyPageViewModel_Factory(Provider<MyPageService> serviceProvider,
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    this.serviceProvider = serviceProvider;
    this.sharedPreferencesUtilProvider = sharedPreferencesUtilProvider;
  }

  @Override
  public MyPageViewModel get() {
    return newInstance(serviceProvider.get(), sharedPreferencesUtilProvider.get());
  }

  public static MyPageViewModel_Factory create(Provider<MyPageService> serviceProvider,
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    return new MyPageViewModel_Factory(serviceProvider, sharedPreferencesUtilProvider);
  }

  public static MyPageViewModel newInstance(MyPageService service,
      SharedPreferencesUtil sharedPreferencesUtil) {
    return new MyPageViewModel(service, sharedPreferencesUtil);
  }
}
