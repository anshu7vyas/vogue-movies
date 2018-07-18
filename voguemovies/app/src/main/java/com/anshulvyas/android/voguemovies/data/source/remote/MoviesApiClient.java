package com.anshulvyas.android.voguemovies.data.source.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit client holder class
 */
public class MoviesApiClient {

    private static MoviesApiClient clientInstance = null;
    private MoviesApiHandler moviesApiHandler;

    private static final String BASE_URL = "https://api.themoviedb.org/3/";


    private MoviesApiClient() {

        Retrofit moviesRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        moviesApiHandler = new MoviesApiHandler(moviesRetrofit.create(MoviesApiService.class));
    }

    public static MoviesApiClient getInstance() {
        if (clientInstance == null) {
            clientInstance = new MoviesApiClient();
        }
        return clientInstance;
    }

    public MoviesApiHandler getMoviesApiHandler() {
        return moviesApiHandler;
    }

}
