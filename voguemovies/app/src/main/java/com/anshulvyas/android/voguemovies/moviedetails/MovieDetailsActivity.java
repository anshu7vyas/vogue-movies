package com.anshulvyas.android.voguemovies.moviedetails;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.LinearLayoutManager;

import com.anshulvyas.android.voguemovies.MoviesApplication;
import com.anshulvyas.android.voguemovies.R;
import com.anshulvyas.android.voguemovies.data.model.Movie;
import com.anshulvyas.android.voguemovies.databinding.ActivityMovieDetailsBinding;
import com.squareup.picasso.Picasso;

/**
 * Shows details of a selected Movie - Title, Overview, Rating, Release Date
 */
public class MovieDetailsActivity extends AppCompatActivity {

    ActivityMovieDetailsBinding mMoviesDetailBinding;
    private Movie mMovie;
    private MovieDetailsViewModel mMovieDetailsViewModel;
    private MovieVideosAdapter mMovieVideoAdapter;
    private MovieReviewsAdapter mMovieReviewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        mMovieVideoAdapter = new MovieVideosAdapter(this);
        mMovieReviewsAdapter = new MovieReviewsAdapter(this);

        Intent intentFromMoviesActivity = getIntent();

        if (intentFromMoviesActivity != null) {
            if (intentFromMoviesActivity.hasExtra(Intent.EXTRA_TEXT)) {
                mMovie = getIntent().getParcelableExtra(Intent.EXTRA_TEXT);
            }
        }

        mMoviesDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);

        MovieDetailsViewModelFactory movieDetailsViewModelFactory = new MovieDetailsViewModelFactory(
                getApplication(), mMovie);

        mMovieDetailsViewModel = ViewModelProviders.of(this, movieDetailsViewModelFactory).get(MovieDetailsViewModel.class);

        populateUI(mMovie);

        mMovieDetailsViewModel.getAllVideos(mMovie.getMovieId()).observe(this, v -> {
            if (v != null) {
                mMovieVideoAdapter.setMovieVideosData(v);
            }
        });

        mMovieDetailsViewModel.getAllReviews(mMovie.getMovieId()).observe(this, v -> {
            if (v != null) {
                mMovieReviewsAdapter.setMovieReviewsData(v);
            }
        });
    }

    /**
     * Populates the detail movies screen from the mMovie object received via MainActivity intent
     *
     * @param movie Movie Object
     */
    private void populateUI(Movie movie) {
        String imageBackdropUrl = MoviesApplication.BASE_URL_BACKDROP_IMAGE + movie.getBackdropPath();
        String imagePosterUrl = MoviesApplication.BASE_URL_POSTER_IMAGE + movie.getPosterPath();

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

        favoritesObserver();

        mMoviesDetailBinding.fabFavorite.setOnClickListener(v -> {
            mMovieDetailsViewModel.toggleIsFavoriteMovie(movie);
        });

        populateVideosList();
        populateReviewsList();
    }

    private void favoritesObserver() {
        final Observer<Movie> favoriteMovieObserver = movie -> {
            if (movie != null) {
                mMoviesDetailBinding.fabFavorite.setImageResource(R.drawable.ic_favorite_red);
            } else {
                mMoviesDetailBinding.fabFavorite.setImageResource(R.drawable.ic_favorite_border_red);
            }
        };

        mMovieDetailsViewModel.getFavoriteMovie().observe(this, favoriteMovieObserver);
    }

    private void populateVideosList() {
        mMoviesDetailBinding.rvVideos.setAdapter(mMovieVideoAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mMoviesDetailBinding.rvVideos.setLayoutManager(layoutManager);
    }

    private void populateReviewsList() {
        mMoviesDetailBinding.rvReviews.setAdapter(mMovieReviewsAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mMoviesDetailBinding.rvReviews.setLayoutManager(layoutManager);
    }

}
