package com.anshulvyas.android.voguemovies.moviedetails;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.anshulvyas.android.voguemovies.data.model.Movie;

/**
 * Source: Google CodeLabs: Architecture Components for Sunshine App
 */
public class MovieDetailsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Application mApplication;
    private final Movie mMovie;

    public MovieDetailsViewModelFactory (Application application, Movie movie) {
        this.mApplication = application;
        this.mMovie = movie;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MovieDetailsViewModel( mApplication, mMovie);
    }
}
