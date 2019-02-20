package com.example.petsearchdemo.movielist;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import com.example.petsearchdemo.data.viewmodel.MovieViewModel;
import com.example.petsearchdemo.moviedetail.MovieDetailActivity;
import com.example.petsearchdemo.movielist.MovieListContract.Presenter.SortOrder;
import com.example.petsearchdemo.Constants;
import com.example.petsearchdemo.MovieApplication;
import com.example.petsearchdemo.R;
import com.example.petsearchdemo.util.ViewUtils;
import com.example.petsearchdemo.widget.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MovieListActivity extends AppCompatActivity implements MovieListContract.View, SwipeRefreshLayout.OnRefreshListener, OnItemClickListener {

    static final String STATE_SORT_ORDER = MovieListActivity.class.getName() + "STATE_SORT_ORDER";
    static final String STATE_DATA = MovieListActivity.class.getName() + "STATE_DATA";
    @Inject
    MovieListPresenter mPresenter;
    private SortOrder mSortOrder = Constants.DEFAULT_SORT_ORDER;
    private SwipeRefreshLayout mSwipeRefresh;
    private MovieListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MovieListAdapter(this);
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mSwipeRefresh.setOnRefreshListener(this);

        DaggerMovieListComponent.builder().applicationComponent(((MovieApplication) getApplicationContext()).getApplicationComponent()).movieListModule(new MovieListModule(this)).build().inject(this);

        mPresenter.subscribe();
        if(savedInstanceState == null) {
            mPresenter.loadMovies(true, mSortOrder);
            ViewUtils.setSwipeRefreshing(mSwipeRefresh, true);
        } else {
            mSortOrder = MovieListContract.Presenter.SortOrder.values()[savedInstanceState.getInt(STATE_SORT_ORDER)];
            mAdapter.addData(savedInstanceState.getParcelableArrayList(STATE_DATA));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadMovies(true, mSortOrder);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SORT_ORDER, mSortOrder.ordinal());
        outState.putParcelableArrayList(STATE_DATA, new ArrayList<>(mAdapter.getData()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void clearMovieList() {
        mAdapter.clearData();
    }

    @Override
    public void showMovieList(List<MovieViewModel> movieViewModels) {
        mAdapter.addData(movieViewModels);
        ViewUtils.setSwipeRefreshing(mSwipeRefresh, false);
    }

    @Override
    public void showError() {
        Snackbar.make(mSwipeRefresh, R.string.error_load_movies, Snackbar.LENGTH_LONG).show();
        ViewUtils.setSwipeRefreshing(mSwipeRefresh, false);
    }

    @Override
    public void onRefresh() {
        mPresenter.loadMovies(true, mSortOrder);
        ViewUtils.setSwipeRefreshing(mSwipeRefresh, true);
    }

    @Override
    public void onItemClicked(int position) {
        startActivity(MovieDetailActivity.newIntent(this, mAdapter.getData().get(position)));
    }
}
