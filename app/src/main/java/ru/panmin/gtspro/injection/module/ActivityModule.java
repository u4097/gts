package ru.panmin.gtspro.injection.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import ru.panmin.gtspro.injection.ActivityContext;

@Module
public class ActivityModule {

    private AppCompatActivity appCompatActivity;

    public ActivityModule(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return appCompatActivity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return appCompatActivity;
    }

}