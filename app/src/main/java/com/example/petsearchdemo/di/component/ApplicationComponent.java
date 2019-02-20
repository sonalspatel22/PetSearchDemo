package com.example.petsearchdemo.di.component;

import com.example.petsearchdemo.di.module.ApplicationModule;
import com.example.petsearchdemo.di.module.RepositoryModule;
import com.example.petsearchdemo.source.MovieRepository;
import com.example.petsearchdemo.util.scheduler.BaseSchedulerProvider;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, RepositoryModule.class})
public interface ApplicationComponent {

    MovieRepository getMovieRepository();

    BaseSchedulerProvider getSchedulerProvider();
}
