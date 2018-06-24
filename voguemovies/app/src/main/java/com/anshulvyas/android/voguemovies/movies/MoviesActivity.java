package com.anshulvyas.android.voguemovies.movies;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.anshulvyas.android.voguemovies.R;
import com.anshulvyas.android.voguemovies.data.model.Movie;
import com.anshulvyas.android.voguemovies.moviedetails.MovieDetailsActivity;
import com.google.gson.Gson;

import java.util.List;

public class MoviesActivity extends AppCompatActivity implements MoviesAdapter.MoviesAdapterOnClickHandler {

    private MoviesAdapter mMoviesAdapter;
    private RecyclerView mMoviesRecyclerView;

    private MoviesActivityViewModel mMoviesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        mMoviesAdapter = new MoviesAdapter(this, this);
        mMoviesViewModel = ViewModelProviders.of(this).get(MoviesActivityViewModel.class);

        mMoviesViewModel.getPopularMoviesList().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                if (movies != null) {
                    mMoviesAdapter.setMoviesData(movies);
                }
            }
        });

        mMoviesRecyclerView = findViewById(R.id.rv_movies);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        mMoviesRecyclerView.setLayoutManager(layoutManager);

        mMoviesRecyclerView.setAdapter(mMoviesAdapter);

    }


    @Override
    public void onClick(Movie movie) {
        Context context = this;
        Gson gson = new Gson();
        Intent openMovieDetailsActivity = new Intent(context, MovieDetailsActivity.class);
        openMovieDetailsActivity.putExtra(Intent.EXTRA_TEXT, gson.toJson(movie));
        startActivity(openMovieDetailsActivity);
    }
}
