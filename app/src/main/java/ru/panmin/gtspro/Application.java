package ru.panmin.gtspro;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.multidex.MultiDexApplication;

import javax.inject.Inject;

import ru.panmin.gtspro.data.local.PreferencesHelper;
import ru.panmin.gtspro.data.remote.SocketHelper;
import ru.panmin.gtspro.injection.component.ApplicationComponent;
import ru.panmin.gtspro.injection.component.DaggerApplicationComponent;
import ru.panmin.gtspro.injection.module.ApplicationModule;
import ru.panmin.gtspro.utils.LocaleManager;
import timber.log.Timber;

public class Application extends MultiDexApplication {

    @Inject SocketHelper socketHelper;

    ApplicationComponent applicationComponent;

    public static Application get(Context context) {
        return (Application) context.getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context context) {
        new PreferencesHelper(context);
        super.attachBaseContext(LocaleManager.setLocale(context));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.setLocale(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    public void onTerminate() {
        socketHelper.close();
        super.onTerminate();
    }

    public ApplicationComponent getComponent() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return applicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm != null ? cm.getActiveNetworkInfo() : null;
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}