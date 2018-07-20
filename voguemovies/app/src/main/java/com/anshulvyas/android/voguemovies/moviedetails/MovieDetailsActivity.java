package com.anshulvyas.android.voguemovies.moviedetails;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.LinearLayoutManager;

import com.anshulvyas.android.voguemovies.R;
import com.anshulvyas.android.voguemovies.data.model.Movie;
import com.anshulvyas.android.voguemovies.data.model.MovieVideos;
import com.anshulvyas.android.voguemovies.databinding.ActivityMovieDetailsBinding;
import com.anshulvyas.android.voguemovies.movies.MoviesAdapter;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Shows details of a selected Movie - Title, Overview, Rating, Release Date
 */
public class MovieDetailsActivity extends AppCompatActivity {

    ActivityMovieDetailsBinding mMoviesDetailBinding;
    private Movie movie;
    private MovieDetailsViewModel mMovieDetailsViewModel;
    private MovieVideosAdapter mMovieVideoAdapter;
    private MovieReviewsAdapter mMovieReviewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        mMovieDetailsViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);

        mMovieVideoAdapter = new MovieVideosAdapter(this);
        mMovieReviewsAdapter = new MovieReviewsAdapter(this);

        Intent intentFromMoviesActivity = getIntent();

        if (intentFromMoviesActivity != null) {
            if (intentFromMoviesActivity.hasExtra(Intent.EXTRA_TEXT)) {
                Gson gson = new Gson();
                String movieString = getIntent().getStringExtra(Intent.EXTRA_TEXT);//getStringExtra(Intent.EXTRA_TEXT);
                movie = gson.fromJson(movieString, Movie.class);
            }
        }

        mMoviesDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);

        populateUI(movie);

        mMovieDetailsViewModel.getAllVideos(movie.getMovieId()).observe(this, v -> {
            if (v != null) {
                mMovieVideoAdapter.setMovieVideosData(v);
            }
        });

        mMovieDetailsViewModel.getAllReviews(movie.getMovieId()).observe(this, v -> {
            if (v != null) {
                mMovieReviewsAdapter.setMovieReviewsData(v);
            }
        });
    }

    /**
     * Populates the detail movies screen from the movie object received via MainActivity intent
     *
     * @param movie Movie Object
     */
    private void populateUI(Movie movie) {
        String imageBackdropUrl = movie.getBackdropPath();
        String imagePosterUrl = movie.getPosterPath();

        mMoviesDetailBinding.tvOriginalTitle.setText((movie.getOriginalTitle().equals("")) ? "N/A" : movie.getOriginalTitle());

        Double voteRating = (movie.getVoteAverage() != null) ? (movie.getVoteAverage() / 2) : 0.0;
        AppCompatRatingBar ratingBar = mMoviesDetailBinding.rbMovieDetails;
        ratingBar.setIsIndicator(true);
        ratingBar.setStepSize(0.1f);
        ratingBar.setRating(voteRating.floatValue());

        mMoviesDetailBinding.tvMovieOverview.setText((movie.getMovieOverview().equals("")) ? "N/A" : movie.getMovieOverview());
        mMoviesDetailBinding.tvReleaseDateLabel.setText((movie.getMovieReleaseDate().equals("")) ? "N/A" : movie.getMovieReleaseDate());

        Picasso.with(this)
                .load(imageBackdropUrl)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(mMoviesDetailBinding.ivMovieBackdrop);

        Picasso.with(this)
                .load(imagePosterUrl)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(mMoviesDetailBinding.ivMoviePoster);

        populateVideosList();
        populateReviewsList();
    }

    private void populateVideosList() {
        mMoviesDetailBinding.rvVideos.setAdapter(mMovieVideoAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mMoviesDetailBinding.rvVideos.setLayoutManager(layoutManager);
    }

    private void populateReviewsList() {
        mMoviesDetailBinding.rvReviews.setAdapter(mMovieReviewsAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mMoviesDetailBinding.rvReviews.setLayoutManager(layoutManager);
    }

}
