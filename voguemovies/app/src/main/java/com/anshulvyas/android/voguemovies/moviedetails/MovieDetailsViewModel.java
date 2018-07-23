package com.anshulvyas.android.voguemovies.moviedetails;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.anshulvyas.android.voguemovies.data.MoviesRepository;
import com.anshulvyas.android.voguemovies.data.model.Movie;
import com.anshulvyas.android.voguemovies.data.model.MovieReviews;
import com.anshulvyas.android.voguemovies.data.model.MovieVideos;
import com.anshulvyas.android.voguemovies.movies.MoviesAdapter;

import java.util.List;

public class MovieDetailsViewModel extends AndroidViewModel {
    private MoviesRepository mMoviesRepository;

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        mMoviesRepository = MoviesRepository.getInstance(application);
    }

    public LiveData<List<MovieVideos>> getAllVideos (int movieId) {
        return mMoviesRepository.getVideosFromMovieId(movieId);
    }

    public LiveData<List<MovieReviews>> getAllReviews (int movieId) {
        return mMoviesRepository.getReviewsFromMovieId(movieId);
    }

    public void toggleIsFavoriteMovie (Movie movie) {
        mMoviesRepository.toggleFavoriteMovie(movie);
    }

}
