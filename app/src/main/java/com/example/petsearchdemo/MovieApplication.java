package com.example.petsearchdemo;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.petsearchdemo.di.component.ApplicationComponent;
import com.example.petsearchdemo.di.component.DaggerApplicationComponent;
import com.example.petsearchdemo.di.module.ApplicationModule;

public class MovieApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    @NonNull
    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
