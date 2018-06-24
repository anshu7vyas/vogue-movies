package com.anshulvyas.android.voguemovies.moviedetails;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRatingBar;
import android.widget.ImageView;

import com.anshulvyas.android.voguemovies.R;
import com.anshulvyas.android.voguemovies.data.model.Movie;
import com.anshulvyas.android.voguemovies.data.source.remote.MoviesApiClient;
import com.anshulvyas.android.voguemovies.databinding.ActivityMovieDetailsBinding;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    ActivityMovieDetailsBinding mMoviesDetailBinding;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intentFromMoviesActivity = getIntent();

        if (intentFromMoviesActivity != null) {
            if (intentFromMoviesActivity.hasExtra(Intent.EXTRA_TEXT)) {
                Gson gson = new Gson();
                String movieString = getIntent().getStringExtra(Intent.EXTRA_TEXT);
                movie = gson.fromJson(movieString, Movie.class);
            }
        }

        mMoviesDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);
        ImageView movieBackdropIv = findViewById(R.id.iv_movie_backdrop);
        ImageView moviePosterIv = findViewById(R.id.iv_movie_poster);

        String imageBackdropUrl = MoviesApiClient.BASE_URL_BACKDROP_IMAGE + movie.getBackdropPath();
        String imagePosterUrl = MoviesApiClient.BASE_URL_POSTER_IMAGE + movie.getPosterPath();

        populateUI(movie);
        Picasso.with(this)
                .load(imageBackdropUrl)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(movieBackdropIv);

        Picasso.with(this)
                .load(imagePosterUrl)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(moviePosterIv);
    }

    private void populateUI(Movie movie) {
       mMoviesDetailBinding.tvOriginalTitle.setText(movie.getOriginalTitle());

       float voteRating = (float) movie.getVoteAverage() / 2;
       AppCompatRatingBar ratingBar = mMoviesDetailBinding.rbMovieDetails;
       ratingBar.setIsIndicator(true);
       ratingBar.setStepSize(0.1f);
       ratingBar.setRating(voteRating);

        mMoviesDetailBinding.tvMovieOverview.setText(movie.getMovieOverview());
        mMoviesDetailBinding.tvReleaseDateLabel.setText(movie.getMovieReleaseDate());
    }
}
