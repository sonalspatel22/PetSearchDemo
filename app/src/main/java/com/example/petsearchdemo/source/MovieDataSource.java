package com.example.petsearchdemo.source;


import com.example.petsearchdemo.data.viewmodel.MovieViewModel;

import java.util.List;

import io.reactivex.Observable;

public interface MovieDataSource {

    Observable<List<MovieViewModel>> getPopularMovies();

//    Observable<List<MovieViewModel>> getTopRatedMovies();

//    Observable<List<MovieViewModel>> getFavoriteMovies();

}
