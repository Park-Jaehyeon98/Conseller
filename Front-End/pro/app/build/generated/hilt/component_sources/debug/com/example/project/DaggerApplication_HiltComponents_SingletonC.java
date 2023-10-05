package com.example.project;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.example.project.api.AuctionService;
import com.example.project.api.BarterService;
import com.example.project.api.LoginService;
import com.example.project.api.MyPageService;
import com.example.project.api.MyService;
import com.example.project.api.OcrService;
import com.example.project.api.SignupService;
import com.example.project.api.StoreService;
import com.example.project.di.NetworkModule;
import com.example.project.di.NetworkModule_ProvideAuctionServiceFactory;
import com.example.project.di.NetworkModule_ProvideBarterServiceFactory;
import com.example.project.di.NetworkModule_ProvideLoginServiceFactory;
import com.example.project.di.NetworkModule_ProvideMyServiceFactory;
import com.example.project.di.NetworkModule_ProvideOcrServiceFactory;
import com.example.project.di.NetworkModule_ProvideOkHttpClientFactory;
import com.example.project.di.NetworkModule_ProvideRetrofitFactory;
import com.example.project.di.NetworkModule_ProvideStoreServiceFactory;
import com.example.project.di.NetworkModule_ProviderMyPageServiceFactory;
import com.example.project.di.NetworkModule_ProviderSignupServiceFactory;
import com.example.project.di.SharedPreferencesModule;
import com.example.project.di.SharedPreferencesModule_ProvideSharedPreferencesUtilFactory;
import com.example.project.sharedpreferences.SharedPreferencesUtil;
import com.example.project.viewmodels.AuctionViewModel;
import com.example.project.viewmodels.AuctionViewModel_HiltModules_KeyModule_ProvideFactory;
import com.example.project.viewmodels.BarterViewModel;
import com.example.project.viewmodels.BarterViewModel_HiltModules_KeyModule_ProvideFactory;
import com.example.project.viewmodels.BiometricViewModel;
import com.example.project.viewmodels.BiometricViewModel_HiltModules_KeyModule_ProvideFactory;
import com.example.project.viewmodels.FireBaseViewModel;
import com.example.project.viewmodels.FireBaseViewModel_HiltModules_KeyModule_ProvideFactory;
import com.example.project.viewmodels.MyAuctionViewModel;
import com.example.project.viewmodels.MyAuctionViewModel_HiltModules_KeyModule_ProvideFactory;
import com.example.project.viewmodels.MyPageViewModel;
import com.example.project.viewmodels.MyPageViewModel_HiltModules_KeyModule_ProvideFactory;
import com.example.project.viewmodels.MygifticonViewModel;
import com.example.project.viewmodels.MygifticonViewModel_HiltModules_KeyModule_ProvideFactory;
import com.example.project.viewmodels.OcrViewModel;
import com.example.project.viewmodels.OcrViewModel_HiltModules_KeyModule_ProvideFactory;
import com.example.project.viewmodels.SignupViewModel;
import com.example.project.viewmodels.SignupViewModel_HiltModules_KeyModule_ProvideFactory;
import com.example.project.viewmodels.StoreViewModel;
import com.example.project.viewmodels.StoreViewModel_HiltModules_KeyModule_ProvideFactory;
import com.example.project.viewmodels.TextloginViewModel;
import com.example.project.viewmodels.TextloginViewModel_HiltModules_KeyModule_ProvideFactory;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.flags.HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.SetBuilder;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerApplication_HiltComponents_SingletonC {
  private DaggerApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder hiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule(
        HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule hiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule) {
      Preconditions.checkNotNull(hiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder networkModule(NetworkModule networkModule) {
      Preconditions.checkNotNull(networkModule);
      return this;
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder sharedPreferencesModule(SharedPreferencesModule sharedPreferencesModule) {
      Preconditions.checkNotNull(sharedPreferencesModule);
      return this;
    }

    public Application_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements Application_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public Application_HiltComponents.ActivityRetainedC build() {
      return new ActivityRetainedCImpl(singletonCImpl);
    }
  }

  private static final class ActivityCBuilder implements Application_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public Application_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements Application_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public Application_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements Application_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public Application_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements Application_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public Application_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements Application_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public Application_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements Application_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public Application_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends Application_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends Application_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends Application_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends Application_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity arg0) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Set<String> getViewModelKeys() {
      return SetBuilder.<String>newSetBuilder(11).add(AuctionViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(BarterViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(BiometricViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(FireBaseViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(MyAuctionViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(MyPageViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(MygifticonViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(OcrViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(SignupViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(StoreViewModel_HiltModules_KeyModule_ProvideFactory.provide()).add(TextloginViewModel_HiltModules_KeyModule_ProvideFactory.provide()).build();
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }
  }

  private static final class ViewModelCImpl extends Application_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AuctionViewModel> auctionViewModelProvider;

    private Provider<BarterViewModel> barterViewModelProvider;

    private Provider<BiometricViewModel> biometricViewModelProvider;

    private Provider<FireBaseViewModel> fireBaseViewModelProvider;

    private Provider<MyAuctionViewModel> myAuctionViewModelProvider;

    private Provider<MyPageViewModel> myPageViewModelProvider;

    private Provider<MygifticonViewModel> mygifticonViewModelProvider;

    private Provider<OcrViewModel> ocrViewModelProvider;

    private Provider<SignupViewModel> signupViewModelProvider;

    private Provider<StoreViewModel> storeViewModelProvider;

    private Provider<TextloginViewModel> textloginViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.auctionViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.barterViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.biometricViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.fireBaseViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.myAuctionViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.myPageViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.mygifticonViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.ocrViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
      this.signupViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 8);
      this.storeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 9);
      this.textloginViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 10);
    }

    @Override
    public Map<String, Provider<ViewModel>> getHiltViewModelMap() {
      return MapBuilder.<String, Provider<ViewModel>>newMapBuilder(11).put("com.example.project.viewmodels.AuctionViewModel", ((Provider) auctionViewModelProvider)).put("com.example.project.viewmodels.BarterViewModel", ((Provider) barterViewModelProvider)).put("com.example.project.viewmodels.BiometricViewModel", ((Provider) biometricViewModelProvider)).put("com.example.project.viewmodels.FireBaseViewModel", ((Provider) fireBaseViewModelProvider)).put("com.example.project.viewmodels.MyAuctionViewModel", ((Provider) myAuctionViewModelProvider)).put("com.example.project.viewmodels.MyPageViewModel", ((Provider) myPageViewModelProvider)).put("com.example.project.viewmodels.MygifticonViewModel", ((Provider) mygifticonViewModelProvider)).put("com.example.project.viewmodels.OcrViewModel", ((Provider) ocrViewModelProvider)).put("com.example.project.viewmodels.SignupViewModel", ((Provider) signupViewModelProvider)).put("com.example.project.viewmodels.StoreViewModel", ((Provider) storeViewModelProvider)).put("com.example.project.viewmodels.TextloginViewModel", ((Provider) textloginViewModelProvider)).build();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.example.project.viewmodels.AuctionViewModel 
          return (T) new AuctionViewModel(singletonCImpl.provideAuctionServiceProvider.get(), singletonCImpl.provideSharedPreferencesUtilProvider.get());

          case 1: // com.example.project.viewmodels.BarterViewModel 
          return (T) new BarterViewModel(singletonCImpl.provideBarterServiceProvider.get(), singletonCImpl.provideSharedPreferencesUtilProvider.get());

          case 2: // com.example.project.viewmodels.BiometricViewModel 
          return (T) new BiometricViewModel(singletonCImpl.provideLoginServiceProvider.get(), singletonCImpl.provideSharedPreferencesUtilProvider.get());

          case 3: // com.example.project.viewmodels.FireBaseViewModel 
          return (T) new FireBaseViewModel(singletonCImpl.provideLoginServiceProvider.get(), singletonCImpl.provideSharedPreferencesUtilProvider.get());

          case 4: // com.example.project.viewmodels.MyAuctionViewModel 
          return (T) new MyAuctionViewModel(singletonCImpl.provideMyServiceProvider.get(), singletonCImpl.provideSharedPreferencesUtilProvider.get());

          case 5: // com.example.project.viewmodels.MyPageViewModel 
          return (T) new MyPageViewModel(singletonCImpl.providerMyPageServiceProvider.get(), singletonCImpl.provideSharedPreferencesUtilProvider.get());

          case 6: // com.example.project.viewmodels.MygifticonViewModel 
          return (T) new MygifticonViewModel(singletonCImpl.provideMyServiceProvider.get(), singletonCImpl.provideSharedPreferencesUtilProvider.get());

          case 7: // com.example.project.viewmodels.OcrViewModel 
          return (T) new OcrViewModel(singletonCImpl.provideOcrServiceProvider.get(), singletonCImpl.provideSharedPreferencesUtilProvider.get());

          case 8: // com.example.project.viewmodels.SignupViewModel 
          return (T) new SignupViewModel(singletonCImpl.providerSignupServiceProvider.get(), singletonCImpl.provideSharedPreferencesUtilProvider.get());

          case 9: // com.example.project.viewmodels.StoreViewModel 
          return (T) new StoreViewModel(singletonCImpl.provideStoreServiceProvider.get(), singletonCImpl.provideSharedPreferencesUtilProvider.get());

          case 10: // com.example.project.viewmodels.TextloginViewModel 
          return (T) new TextloginViewModel(singletonCImpl.provideLoginServiceProvider.get(), singletonCImpl.provideSharedPreferencesUtilProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends Application_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;

      initialize();

    }

    @SuppressWarnings("unchecked")
    private void initialize() {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends Application_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends Application_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<SharedPreferencesUtil> provideSharedPreferencesUtilProvider;

    private Provider<OkHttpClient> provideOkHttpClientProvider;

    private Provider<Retrofit> provideRetrofitProvider;

    private Provider<AuctionService> provideAuctionServiceProvider;

    private Provider<BarterService> provideBarterServiceProvider;

    private Provider<LoginService> provideLoginServiceProvider;

    private Provider<MyService> provideMyServiceProvider;

    private Provider<MyPageService> providerMyPageServiceProvider;

    private Provider<OcrService> provideOcrServiceProvider;

    private Provider<SignupService> providerSignupServiceProvider;

    private Provider<StoreService> provideStoreServiceProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideSharedPreferencesUtilProvider = DoubleCheck.provider(new SwitchingProvider<SharedPreferencesUtil>(singletonCImpl, 3));
      this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 2));
      this.provideRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 1));
      this.provideAuctionServiceProvider = DoubleCheck.provider(new SwitchingProvider<AuctionService>(singletonCImpl, 0));
      this.provideBarterServiceProvider = DoubleCheck.provider(new SwitchingProvider<BarterService>(singletonCImpl, 4));
      this.provideLoginServiceProvider = DoubleCheck.provider(new SwitchingProvider<LoginService>(singletonCImpl, 5));
      this.provideMyServiceProvider = DoubleCheck.provider(new SwitchingProvider<MyService>(singletonCImpl, 6));
      this.providerMyPageServiceProvider = DoubleCheck.provider(new SwitchingProvider<MyPageService>(singletonCImpl, 7));
      this.provideOcrServiceProvider = DoubleCheck.provider(new SwitchingProvider<OcrService>(singletonCImpl, 8));
      this.providerSignupServiceProvider = DoubleCheck.provider(new SwitchingProvider<SignupService>(singletonCImpl, 9));
      this.provideStoreServiceProvider = DoubleCheck.provider(new SwitchingProvider<StoreService>(singletonCImpl, 10));
    }

    @Override
    public void injectApplication(Application application) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.example.project.api.AuctionService 
          return (T) NetworkModule_ProvideAuctionServiceFactory.provideAuctionService(singletonCImpl.provideRetrofitProvider.get());

          case 1: // retrofit2.Retrofit 
          return (T) NetworkModule_ProvideRetrofitFactory.provideRetrofit(singletonCImpl.provideOkHttpClientProvider.get());

          case 2: // okhttp3.OkHttpClient 
          return (T) NetworkModule_ProvideOkHttpClientFactory.provideOkHttpClient(singletonCImpl.provideSharedPreferencesUtilProvider.get());

          case 3: // com.example.project.sharedpreferences.SharedPreferencesUtil 
          return (T) SharedPreferencesModule_ProvideSharedPreferencesUtilFactory.provideSharedPreferencesUtil(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 4: // com.example.project.api.BarterService 
          return (T) NetworkModule_ProvideBarterServiceFactory.provideBarterService(singletonCImpl.provideRetrofitProvider.get());

          case 5: // com.example.project.api.LoginService 
          return (T) NetworkModule_ProvideLoginServiceFactory.provideLoginService(singletonCImpl.provideRetrofitProvider.get());

          case 6: // com.example.project.api.MyService 
          return (T) NetworkModule_ProvideMyServiceFactory.provideMyService(singletonCImpl.provideRetrofitProvider.get());

          case 7: // com.example.project.api.MyPageService 
          return (T) NetworkModule_ProviderMyPageServiceFactory.providerMyPageService(singletonCImpl.provideRetrofitProvider.get());

          case 8: // com.example.project.api.OcrService 
          return (T) NetworkModule_ProvideOcrServiceFactory.provideOcrService(singletonCImpl.provideRetrofitProvider.get());

          case 9: // com.example.project.api.SignupService 
          return (T) NetworkModule_ProviderSignupServiceFactory.providerSignupService(singletonCImpl.provideRetrofitProvider.get());

          case 10: // com.example.project.api.StoreService 
          return (T) NetworkModule_ProvideStoreServiceFactory.provideStoreService(singletonCImpl.provideRetrofitProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
