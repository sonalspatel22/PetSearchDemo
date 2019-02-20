package com.example.petsearchdemo.moviedetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petsearchdemo.R;
import com.example.petsearchdemo.data.viewmodel.MovieViewModel;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String EXTRA_MOVIE = MovieDetailActivity.class.getName() + ".EXTRA_MOVIE";
    private MovieViewModel mMovieViewModel;


    @NonNull
    public static Intent newIntent(@NonNull Context context, @NonNull MovieViewModel movie) {
        return new Intent(context, MovieDetailActivity.class).putExtra(EXTRA_MOVIE, movie);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        mMovieViewModel = getIntent().getParcelableExtra(EXTRA_MOVIE);
        bindViews(mMovieViewModel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void bindViews(@NonNull MovieViewModel moviewViewModel) {
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle(moviewViewModel.getTitle());
        ((TextView) findViewById(R.id.text_rating)).setText(String.valueOf(moviewViewModel.getUserRating()));
        ((TextView) findViewById(R.id.text_release_date)).setText(formateBirthdayDate(String.valueOf(moviewViewModel.getReleaseDate())));

        ((TextView) findViewById(R.id.text_synopsis)).setText(moviewViewModel.getPlotSynopsis());
        ((TextView) findViewById(R.id.budgetAmountTextView)).setText("$ " + String.valueOf(moviewViewModel.getBudget()));
        ((TextView) findViewById(R.id.revenueAmountTextView)).setText("$ " + String.valueOf(moviewViewModel.getRevenue()));
        ((TextView) findViewById(R.id.typeofmovieTextView)).setText(moviewViewModel.getGeners());
        ((TextView) findViewById(R.id.timeTextView)).setText(String.valueOf(moviewViewModel.getTotaltime()) + " minites");
        TextView laung = ((TextView) findViewById(R.id.laungugeTextView));
        String laun = moviewViewModel.getLaungage();
        if(laung != null && laung.length() > 0) {
            laung.setText(laun);
        } else {
            laung.setText(laung.getContext().getString(R.string.defaultLaunguge));
        }
        Picasso.with(this).load(moviewViewModel.getPosterUrl()).fit().into((ImageView) findViewById(R.id.img_poster));

    }

    private String formateBirthdayDate(String date) {
        SimpleDateFormat serverDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        Date serverDate = null;
        try {
            serverDate = serverDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDateFormat.format(serverDate);
    }
}
