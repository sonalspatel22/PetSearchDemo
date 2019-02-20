package com.example.petsearchdemo.source;

import android.content.ContentResolver;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.example.petsearchdemo.content.MovieContract.MovieEntry;
import com.example.petsearchdemo.data.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


@Singleton
public class MovieRepository implements MovieDataSource {

    @NonNull
    private final MovieDataSource mMovieRemoteDataSource;

    @Inject
    public MovieRepository(@NonNull @Remote MovieDataSource movieRemoteDataSource) {
        this.mMovieRemoteDataSource = movieRemoteDataSource;
    }

    @Override
    public Observable<List<MovieViewModel>> getPopularMovies() {
        return mMovieRemoteDataSource.getPopularMovies();
    }

}
