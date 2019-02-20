package com.example.petsearchdemo.data.model;

import android.support.annotation.NonNull;

import com.example.petsearchdemo.data.viewmodel.MovieViewModel;

public class Movie implements IViewModel<MovieViewModel> {

    private static final String PHOTO_SMALL_SIZE = "w185";

    private long id;
    private String title;
    private String poster_path;
    private String overview;
    private double vote_average;
    private String release_date;
    private String lang;
    private int budget;
    private String moreInfo;
    private int revenue;
    private int totaltime;
    private String geners;

    @NonNull
    @Override
    public MovieViewModel toViewModel() {
        return new MovieViewModel(id, title, "http://image.tmdb.org/t/p/" + PHOTO_SMALL_SIZE + poster_path, overview, vote_average, release_date, lang, moreInfo, budget, revenue, totaltime, geners);
    }
}
