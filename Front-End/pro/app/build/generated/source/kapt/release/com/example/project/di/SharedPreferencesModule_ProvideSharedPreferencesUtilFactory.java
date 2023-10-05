package com.example.project.di;

import android.content.Context;
import com.example.project.sharedpreferences.SharedPreferencesUtil;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class SharedPreferencesModule_ProvideSharedPreferencesUtilFactory implements Factory<SharedPreferencesUtil> {
  private final Provider<Context> contextProvider;

  public SharedPreferencesModule_ProvideSharedPreferencesUtilFactory(
      Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SharedPreferencesUtil get() {
    return provideSharedPreferencesUtil(contextProvider.get());
  }

  public static SharedPreferencesModule_ProvideSharedPreferencesUtilFactory create(
      Provider<Context> contextProvider) {
    return new SharedPreferencesModule_ProvideSharedPreferencesUtilFactory(contextProvider);
  }

  public static SharedPreferencesUtil provideSharedPreferencesUtil(Context context) {
    return Preconditions.checkNotNullFromProvides(SharedPreferencesModule.INSTANCE.provideSharedPreferencesUtil(context));
  }
}
