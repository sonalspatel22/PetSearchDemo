package com.example.petsearchdemo.source.remote;

import android.support.annotation.NonNull;


import com.example.petsearchdemo.data.model.PaginatedList;
import com.example.petsearchdemo.data.viewmodel.MovieViewModel;
import com.example.petsearchdemo.http.api.MovieApi;
import com.example.petsearchdemo.source.MovieDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MovieRemoteDataSource implements MovieDataSource {

    @NonNull
    private final MovieApi mMovieApi;

    @Inject
    public MovieRemoteDataSource(@NonNull MovieApi mMovieApi) {
        this.mMovieApi = mMovieApi;
    }

    @Override
    public Observable<List<MovieViewModel>> getPopularMovies() {
        return mMovieApi.getPopularMovies().map(PaginatedList::toListViewModel);
    }
}
