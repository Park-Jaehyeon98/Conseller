package com.example.project.sharedpreferences;

import android.content.Context;
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
public final class SharedPreferencesUtil_Factory implements Factory<SharedPreferencesUtil> {
  private final Provider<Context> contextProvider;

  public SharedPreferencesUtil_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SharedPreferencesUtil get() {
    return newInstance(contextProvider.get());
  }

  public static SharedPreferencesUtil_Factory create(Provider<Context> contextProvider) {
    return new SharedPreferencesUtil_Factory(contextProvider);
  }

  public static SharedPreferencesUtil newInstance(Context context) {
    return new SharedPreferencesUtil(context);
  }
}
