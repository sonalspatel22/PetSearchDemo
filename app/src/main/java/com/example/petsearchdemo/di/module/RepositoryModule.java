package com.example.petsearchdemo.di.module;

import com.example.petsearchdemo.http.api.MovieApi;
import com.example.petsearchdemo.source.MovieDataSource;
import com.example.petsearchdemo.source.Remote;
import com.example.petsearchdemo.source.remote.MovieRemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ApplicationModule.class)
public class RepositoryModule {

    @Provides
    @Singleton
    @Remote
    MovieDataSource providesMovieRemoteDataSource(MovieApi movieApi) {
        return new MovieRemoteDataSource(movieApi);
    }
}
