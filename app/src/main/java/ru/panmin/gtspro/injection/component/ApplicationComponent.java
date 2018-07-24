package ru.panmin.gtspro.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ru.panmin.gtspro.data.DataManager;
import ru.panmin.gtspro.data.local.PreferencesHelper;
import ru.panmin.gtspro.data.remote.ApiService;
import ru.panmin.gtspro.injection.ApplicationContext;
import ru.panmin.gtspro.injection.module.ApplicationModule;
import ru.panmin.gtspro.utils.RxEventBus;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();
    Application application();
    DataManager dataManager();
    PreferencesHelper preferencesHelper();
    ApiService apiService();
    RxEventBus eventBus();

}