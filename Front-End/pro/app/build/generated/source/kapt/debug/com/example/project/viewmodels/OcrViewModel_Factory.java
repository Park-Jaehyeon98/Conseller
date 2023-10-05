package com.example.project.viewmodels;

import com.example.project.api.OcrService;
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
public final class OcrViewModel_Factory implements Factory<OcrViewModel> {
  private final Provider<OcrService> ocrServiceProvider;

  private final Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider;

  public OcrViewModel_Factory(Provider<OcrService> ocrServiceProvider,
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    this.ocrServiceProvider = ocrServiceProvider;
    this.sharedPreferencesUtilProvider = sharedPreferencesUtilProvider;
  }

  @Override
  public OcrViewModel get() {
    return newInstance(ocrServiceProvider.get(), sharedPreferencesUtilProvider.get());
  }

  public static OcrViewModel_Factory create(Provider<OcrService> ocrServiceProvider,
      Provider<SharedPreferencesUtil> sharedPreferencesUtilProvider) {
    return new OcrViewModel_Factory(ocrServiceProvider, sharedPreferencesUtilProvider);
  }

  public static OcrViewModel newInstance(OcrService OcrService,
      SharedPreferencesUtil sharedPreferencesUtil) {
    return new OcrViewModel(OcrService, sharedPreferencesUtil);
  }
}
