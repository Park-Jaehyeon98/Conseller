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
public final class MygifticonViewModel_Factory implements Factory<MygifticonViewModel> {
  private final Provider<MyService> myServiceProvider;

  private final Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider;

  public MygifticonViewModel_Factory(Provider<MyService> myServiceProvider,
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    this.myServiceProvider = myServiceProvider;
    this.sharedPreferencesUtilProvider = sharedPreferencesUtilProvider;
  }

  @Override
  public MygifticonViewModel get() {
    return newInstance(myServiceProvider.get(), sharedPreferencesUtilProvider.get());
  }

  public static MygifticonViewModel_Factory create(Provider<MyService> myServiceProvider,
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    return new MygifticonViewModel_Factory(myServiceProvider, sharedPreferencesUtilProvider);
  }

  public static MygifticonViewModel newInstance(MyService myService,
      SharedPreferencesUtil sharedPreferencesUtil) {
    return new MygifticonViewModel(myService, sharedPreferencesUtil);
  }
}
