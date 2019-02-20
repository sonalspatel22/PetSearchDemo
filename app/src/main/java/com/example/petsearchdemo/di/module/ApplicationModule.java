package com.example.petsearchdemo.di.module;

import android.content.ContentResolver;
import android.content.Context;

import com.example.petsearchdemo.http.api.ApiFactory;
import com.example.petsearchdemo.http.api.MovieApi;
import com.example.petsearchdemo.util.scheduler.BaseSchedulerProvider;
import com.example.petsearchdemo.util.scheduler.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {

    private final Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    ApiFactory providesApiFactory() {
        return new ApiFactory("http://api.themoviedb.org");
    }

    @Provides
    @Singleton
    MovieApi providesMovieApi(final ApiFactory apiFactory) { return apiFactory.create(MovieApi.class); }

    @Provides
    @Singleton
    BaseSchedulerProvider providesSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }

    @Provides
    @Singleton
    ContentResolver providesContentResolver() {
        return mContext.getContentResolver();
    }
}
