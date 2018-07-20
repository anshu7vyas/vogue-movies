package com.anshulvyas.android.voguemovies.moviedetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.anshulvyas.android.voguemovies.data.MoviesRepository;
import com.anshulvyas.android.voguemovies.data.model.Movie;
import com.anshulvyas.android.voguemovies.data.model.MovieReviews;
import com.anshulvyas.android.voguemovies.data.model.MovieVideos;
import com.anshulvyas.android.voguemovies.movies.MoviesAdapter;

import java.util.List;

public class MovieDetailsViewModel extends ViewModel {

    public LiveData<List<MovieVideos>> getAllVideos (int movieId) {
        return MoviesRepository.getInstance().getVideosFromMovieId(movieId);
    }

    public LiveData<List<MovieReviews>> getAllReviews (int movieId) {
        return MoviesRepository.getInstance().getReviewsFromMovieId(movieId);
    }

}
