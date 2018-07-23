package com.anshulvyas.android.voguemovies.movies;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.anshulvyas.android.voguemovies.data.MoviesRepository;
import com.anshulvyas.android.voguemovies.data.model.Movie;

import java.util.List;

/**
 * Viewmodel for the MoviesActivity, handles the change in data using LiveData
 */
public class MoviesActivityViewModel extends AndroidViewModel {

    private final LiveData<List<Movie>> popularMoviesResponseObservable;
    private final LiveData<List<Movie>> topRatedMoviesResponseObservable;
    private final LiveData<List<Movie>> favoriteMoviesResponseObservable;

    public MoviesActivityViewModel(Application application) {
        super(application);
        popularMoviesResponseObservable = MoviesRepository.getInstance(application).getPopularMoviesLiveData();
        topRatedMoviesResponseObservable = MoviesRepository.getInstance(application).getTopRatedMoviesLiveData();
        favoriteMoviesResponseObservable = MoviesRepository.getInstance(application).getFavoriteMoviesLiveData();
    }

    public LiveData<List<Movie>> getPopularMoviesList() {
        return popularMoviesResponseObservable;
    }

    public LiveData<List<Movie>> getTopRatedMoviesList() {
        return topRatedMoviesResponseObservable;
    }

    public LiveData<List<Movie>> getFavoriteMoviesList() {
        return favoriteMoviesResponseObservable;
    }

}
