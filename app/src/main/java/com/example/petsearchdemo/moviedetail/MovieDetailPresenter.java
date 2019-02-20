package com.example.petsearchdemo.moviedetail;


import com.example.petsearchdemo.data.viewmodel.MovieViewModel;
import com.example.petsearchdemo.source.MovieRepository;
import com.example.petsearchdemo.util.scheduler.BaseSchedulerProvider;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;

class MovieDetailPresenter {

    private final long mMovieId;
    private final MovieRepository mMovieRepository;
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private CompositeDisposable mComposite;

    @Inject
    MovieDetailPresenter(@NonNull long movieId, MovieRepository movieRepository, BaseSchedulerProvider schedulerProvider) {
        this.mMovieId = movieId;
        this.mMovieRepository = movieRepository;
        this.mSchedulerProvider = schedulerProvider;
        this.mComposite = new CompositeDisposable();
    }


}
