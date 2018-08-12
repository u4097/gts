package ru.panmin.gtspro.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.panmin.gtspro.data.local.PreferencesHelper;
import ru.panmin.gtspro.data.local.RealmHelper;
import ru.panmin.gtspro.data.remote.ApiService;
import ru.panmin.gtspro.data.remote.SocketHelper;
import ru.panmin.gtspro.injection.ApplicationContext;
import ru.panmin.gtspro.utils.RxEventBus;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    ApiService provideApiService(@ApplicationContext Context context) {
        return ApiService.Creator.newApiService(context);
    }

    @Provides
    @Singleton
    RealmHelper provideRealmHelper(@ApplicationContext Context context) {
        return new RealmHelper(context);
    }

    @Provides
    @Singleton
    SocketHelper provideSocketHelper(@ApplicationContext Context context) {
        return new SocketHelper(new PreferencesHelper(context), new RealmHelper(context), new RxEventBus());
    }

}