package com.anshulvyas.android.voguemovies.data.source.remote;

import com.anshulvyas.android.voguemovies.BuildConfig;
import com.anshulvyas.android.voguemovies.data.model.Movie;
import com.anshulvyas.android.voguemovies.data.model.MoviesResponse;

import retrofit2.Call;

public class MoviesApiHandler {

    private final static String API_KEY = BuildConfig.API_KEY;
    private final static int PAGE = 1;
    private final static String LANGUAGE = "en-US";

    private MoviesApiService moviesApiService;

    MoviesApiHandler(MoviesApiService moviesApiService) {
        this.moviesApiService = moviesApiService;
    }

    public Call<MoviesResponse> getPopularMovies() {
        final String CATEGORY_POPULAR = "popular";

        return moviesApiService.getMovies(CATEGORY_POPULAR, API_KEY, LANGUAGE, PAGE);
    }

    public Call<MoviesResponse> getTopRatedMovies() {
        final String CATEGORY_TOP_RATED = "top_rated";

        return moviesApiService.getMovies(CATEGORY_TOP_RATED, API_KEY, LANGUAGE, PAGE);
    }

    public Call<Movie> getMovieDetails (int movieId) {
        return moviesApiService.getMovieDetails(movieId);
    }

}
