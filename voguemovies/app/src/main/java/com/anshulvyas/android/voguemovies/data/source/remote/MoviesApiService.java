package com.anshulvyas.android.voguemovies.data.source.remote;

import com.anshulvyas.android.voguemovies.data.model.Movie;
import com.anshulvyas.android.voguemovies.data.model.MoviesResponse;

import java.util.List;

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

}
