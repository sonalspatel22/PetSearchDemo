package com.example.petsearchdemo.movielist;

import com.example.petsearchdemo.BasePresenter;
import com.example.petsearchdemo.BaseView;
import com.example.petsearchdemo.data.viewmodel.MovieViewModel;

import java.util.List;

public interface MovieListContract {

    interface View extends BaseView {
        void clearMovieList();

        void showMovieList(final List<MovieViewModel> movieViewModels);

        void showError();
    }

    interface Presenter extends BasePresenter {

        enum SortOrder {
            BY_POPULARITY
        }

        void loadMovies(boolean refresh, SortOrder sortOrder);
    }
}
