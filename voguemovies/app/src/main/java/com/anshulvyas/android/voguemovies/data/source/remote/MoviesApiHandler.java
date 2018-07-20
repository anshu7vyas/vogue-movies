package com.anshulvyas.android.voguemovies.data.source.remote;

import com.anshulvyas.android.voguemovies.BuildConfig;
import com.anshulvyas.android.voguemovies.data.model.Movie;
import com.anshulvyas.android.voguemovies.data.model.MovieReviewsResponse;
import com.anshulvyas.android.voguemovies.data.model.MovieVideosResponse;
import com.anshulvyas.android.voguemovies.data.model.MoviesResponse;

import retrofit2.Call;

/**
 * Adds the query parameters to the GET query
 */
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

    public Call<MovieVideosResponse> getVideosFromMovieId (int movieId) {
        return moviesApiService.getVideosOfMovie(movieId, API_KEY);
    }

    public Call<MovieReviewsResponse> getReviewsFromMovieId (int movieId) {
        return moviesApiService.getReviewsOfMovie(movieId, API_KEY);
    }

//    public Call<Movie> getMovieFromId (int movieId) {
//        return moviesApiService.getMovie(movieId, API_KEY);
//    }

}
