package com.anshulvyas.android.voguemovies.movies;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.anshulvyas.android.voguemovies.data.MoviesRepository;
import com.anshulvyas.android.voguemovies.data.model.Movie;

import java.util.List;


public class MoviesActivityViewModel extends AndroidViewModel {

    private final LiveData<List<Movie>> popularMoviesResponseObservable;
    private final LiveData<List<Movie>> topRatedMoviesResponseObservable;

    public MoviesActivityViewModel(Application application) {
        super(application);
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
