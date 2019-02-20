package com.example.petsearchdemo.movielist;


import com.example.petsearchdemo.di.component.ApplicationComponent;
import com.example.petsearchdemo.util.ActivityScoped;
import dagger.Component;

@ActivityScoped
@Component(modules = MovieListModule.class, dependencies = ApplicationComponent.class)
public interface MovieListComponent {
    void inject(MovieListActivity activity);
}
