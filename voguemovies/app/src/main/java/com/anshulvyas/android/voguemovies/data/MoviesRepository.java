package com.anshulvyas.android.voguemovies.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.anshulvyas.android.voguemovies.data.model.Movie;
import com.anshulvyas.android.voguemovies.data.model.MoviesResponse;
import com.anshulvyas.android.voguemovies.data.source.remote.MoviesApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Movies Repository, provides abstraction to the data layer
 */
public class MoviesRepository {

    private static final String LOG_TAG = MoviesRepository.class.getSimpleName();
    private static MoviesRepository mMoviesRepositoryInstance = null;
    private final MoviesApiClient mMoviesApiClient = MoviesApiClient.getInstance();

    public static MoviesRepository getInstance() {
        if (mMoviesRepositoryInstance == null) {
            mMoviesRepositoryInstance = new MoviesRepository();
        }
        return mMoviesRepositoryInstance;
    }

    /**
     * Fetches the Popular Movies data from the MoviesApiService
     *
     * @return List<Movie>
     */
    public LiveData<List<Movie>> getPopularMoviesLiveData() {
        final MutableLiveData<List<Movie>> popularMoviesMutableLiveData = new MutableLiveData<>();

        mMoviesApiClient.getMoviesApiHandler().getPopularMovies().enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                popularMoviesMutableLiveData.setValue(fetchPopularMovies(response));
                Log.d(LOG_TAG, popularMoviesMutableLiveData.toString());
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e(LOG_TAG, t.toString());
            }
        });

        return popularMoviesMutableLiveData;
    }

    private List<Movie> fetchPopularMovies(Response<MoviesResponse> popularMoviesResponse) {
        List<Movie> popularMovies = new ArrayList<>();
        if (popularMoviesResponse.body() != null && popularMoviesResponse.body().getResults() != null) {
            popularMovies = popularMoviesResponse.body().getResults();
        }

        return popularMovies;
    }

    /**
     * Fetches the Top-Rated Movies data from the MoviesApiService
     *
     * @return List<Movie>
     */
    public LiveData<List<Movie>> getTopRatedMoviesLiveData() {
        final MutableLiveData<List<Movie>> topRatedMoviesMutableLiveData = new MutableLiveData<>();

        mMoviesApiClient.getMoviesApiHandler().getTopRatedMovies().enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                topRatedMoviesMutableLiveData.setValue(fetchTopRatedMovies(response));
                Log.d(LOG_TAG, topRatedMoviesMutableLiveData.toString());
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e(LOG_TAG, t.toString());
            }
        });

        return topRatedMoviesMutableLiveData;
    }

    private List<Movie> fetchTopRatedMovies(Response<MoviesResponse> topRatedMoviesResponse) {
        List<Movie> topRatedMovies = new ArrayList<>();
        if (topRatedMoviesResponse.body() != null && topRatedMoviesResponse.body().getResults() != null) {
            topRatedMovies = topRatedMoviesResponse.body().getResults();
        }

        return topRatedMovies;
    }

}
