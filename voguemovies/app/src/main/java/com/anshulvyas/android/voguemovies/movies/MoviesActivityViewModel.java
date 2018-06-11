package com.anshulvyas.android.voguemovies.movies;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.anshulvyas.android.voguemovies.data.MoviesRepository;
import com.anshulvyas.android.voguemovies.data.model.Movie;

import java.util.List;

/**
 * Viewmodel for the MoviesActivity, handles the change in data using LiveData
 */
public class MoviesActivityViewModel extends ViewModel {

    private final LiveData<List<Movie>> popularMoviesResponseObservable;
    private final LiveData<List<Movie>> topRatedMoviesResponseObservable;

    public MoviesActivityViewModel() {
        popularMoviesResponseObservable = MoviesRepository.getInstance().getPopularMoviesLiveData();
        topRatedMoviesResponseObservable = MoviesRepository.getInstance().getTopRatedMoviesLiveData();
    }

    public LiveData<List<Movie>> getPopularMoviesList() {
        return popularMoviesResponseObservable;
    }

    public LiveData<List<Movie>> getTopRatedMoviesList() {
        return topRatedMoviesResponseObservable;
    }


}
