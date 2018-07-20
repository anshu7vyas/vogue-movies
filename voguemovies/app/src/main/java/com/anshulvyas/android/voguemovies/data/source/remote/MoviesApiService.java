package com.anshulvyas.android.voguemovies.data.source.remote;

import com.anshulvyas.android.voguemovies.data.model.Movie;
import com.anshulvyas.android.voguemovies.data.model.MovieReviewsResponse;
import com.anshulvyas.android.voguemovies.data.model.MovieVideosResponse;
import com.anshulvyas.android.voguemovies.data.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by av7 on 5/7/18.
 */

public interface MoviesApiService {

    @GET("movie/{category}")
    Call<MoviesResponse> getMovies(
            @Path("category") String category,
            @Query("api_key") String API_KEY,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("movie/{movie_id}/reviews")
    Call<MovieReviewsResponse> getReviewsOfMovie (
            @Path("movie_id") int movieId,
            @Query("api_key") String API_KEY
    );

    @GET("movie/{movie_id}/videos")
    Call<MovieVideosResponse> getVideosOfMovie (
            @Path("movie_id") int movieId,
            @Query("api_key") String API_KEY
    );

//    @GET("movie/{movie_id}")
//    Call<Movie> getMovie(
//            @Path("movie_id") int movieId,
//            @Query("api_key") String API_KEY
//    );

}
