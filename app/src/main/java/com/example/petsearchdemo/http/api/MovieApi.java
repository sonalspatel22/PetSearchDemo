package com.example.petsearchdemo.http.api;


import com.example.petsearchdemo.data.model.Movie;
import com.example.petsearchdemo.data.model.PaginatedList;
import com.example.petsearchdemo.data.viewmodel.MovieViewModel;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface MovieApi {

    @GET("/3/movie/popular")
    Observable<PaginatedList<Movie, MovieViewModel>> getPopularMovies();

}
