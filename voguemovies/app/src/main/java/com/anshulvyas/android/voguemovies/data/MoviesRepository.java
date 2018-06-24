package com.anshulvyas.android.voguemovies.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.anshulvyas.android.voguemovies.data.model.Movie;
import com.anshulvyas.android.voguemovies.data.model.MoviesResponse;
import com.anshulvyas.android.voguemovies.data.source.remote.MoviesApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {

    private static final String LOG_TAG = MoviesRepository.class.getSimpleName();

    private static MoviesRepository mMoviesRepositoryInstance = null;

    public static MoviesRepository getInstance() {
        if (mMoviesRepositoryInstance  == null) {
            mMoviesRepositoryInstance  = new MoviesRepository();
        }
        return mMoviesRepositoryInstance;
    }

    public LiveData<List<Movie>> getPopularMoviesLiveData() {
        final MutableLiveData<List<Movie>> popularMoviesMutableLiveData = new MutableLiveData<>();

        MoviesApiClient.getInstance().getMoviesApiHandler().getPopularMovies().enqueue(new Callback<MoviesResponse>() {
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
        MoviesResponse popularMovies = popularMoviesResponse.body();
        return popularMovies.getResults();
    }

    public LiveData<List<Movie>> getTopRatedMoviesLiveData() {
        final MutableLiveData<List<Movie>> topRatedMoviesMutableLiveData = new MutableLiveData<>();

        MoviesApiClient.getInstance().getMoviesApiHandler().getTopRatedMovies().enqueue(new Callback<MoviesResponse>() {
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
        MoviesResponse topRatedMovies = topRatedMoviesResponse.body();
        return topRatedMovies.getResults();
    }

    public LiveData<Movie> getMovieDetails(int movieId) {
        final MutableLiveData<Movie> movieDetailsMutableLiveData = new MutableLiveData<>();

        MoviesApiClient.getInstance().getMoviesApiHandler().getMovieDetails(movieId).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                movieDetailsMutableLiveData.setValue(response.body());
                Log.d(LOG_TAG, movieDetailsMutableLiveData.toString());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e(LOG_TAG, t.toString());
            }
        });

        return movieDetailsMutableLiveData;
    }


}
