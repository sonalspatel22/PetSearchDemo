package com.example.petsearchdemo.movielist;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieListModule {

    private final MovieListContract.View mView;

    public MovieListModule(MovieListContract.View mView) {
        this.mView = mView;
    }

    @Provides
    MovieListContract.View providesView() {
        return mView;
    }
}
