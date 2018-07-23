package com.anshulvyas.android.voguemovies.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.anshulvyas.android.voguemovies.AppExecutors;
import com.anshulvyas.android.voguemovies.data.model.Movie;
import com.anshulvyas.android.voguemovies.data.model.MovieReviews;
import com.anshulvyas.android.voguemovies.data.model.MovieReviewsResponse;
import com.anshulvyas.android.voguemovies.data.model.MovieVideos;
import com.anshulvyas.android.voguemovies.data.model.MovieVideosResponse;
import com.anshulvyas.android.voguemovies.data.model.MoviesResponse;
import com.anshulvyas.android.voguemovies.data.source.local.FavoriteMoviesDao;
import com.anshulvyas.android.voguemovies.data.source.local.FavoriteMoviesDatabase;
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
    private final MoviesApiClient mMoviesApiClient;
    private final FavoriteMoviesDao mFavoriteMoviesDao;

    public static MoviesRepository getInstance(Application application) {
        if (mMoviesRepositoryInstance == null) {
            mMoviesRepositoryInstance = new MoviesRepository(application);
        }
        return mMoviesRepositoryInstance;
    }

    private MoviesRepository(Application application) {
        FavoriteMoviesDatabase database = FavoriteMoviesDatabase.getInstance(application);
        mFavoriteMoviesDao = database.favoriteMoviesDao();
        mMoviesApiClient = MoviesApiClient.getInstance();
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

    public LiveData<List<MovieVideos>> getVideosFromMovieId (int movieId) {
        final MutableLiveData<List<MovieVideos>> movieVideosMutableLiveData = new MutableLiveData<>();

        mMoviesApiClient.getMoviesApiHandler().getVideosFromMovieId(movieId).enqueue(new Callback<MovieVideosResponse>() {
            @Override
            public void onResponse(Call<MovieVideosResponse> call, Response<MovieVideosResponse> response) {
                movieVideosMutableLiveData.setValue(fetchMovieVideos(response));
                Log.d(LOG_TAG, movieVideosMutableLiveData.toString());
            }

            @Override
            public void onFailure(Call<MovieVideosResponse> call, Throwable t) {
                Log.d(LOG_TAG, t.toString());
            }
        });
        return movieVideosMutableLiveData;
    }

    private List<MovieVideos> fetchMovieVideos(Response<MovieVideosResponse> movieVideosResponse) {
        List<MovieVideos> movieVideosList = new ArrayList<>();

        if (movieVideosResponse.body() != null && movieVideosResponse.body().getResults() != null) {
            movieVideosList = movieVideosResponse.body().getResults();
        }

        return movieVideosList;
    }

    public LiveData<List<MovieReviews>> getReviewsFromMovieId (int movieId) {
        final MutableLiveData<List<MovieReviews>> movieReviewsMutableLiveData = new MutableLiveData<>();

        mMoviesApiClient.getMoviesApiHandler().getReviewsFromMovieId(movieId).enqueue(new Callback<MovieReviewsResponse>() {
            @Override
            public void onResponse(Call<MovieReviewsResponse> call, Response<MovieReviewsResponse> response) {
                movieReviewsMutableLiveData.setValue(fetchMovieReviews(response));
                Log.d(LOG_TAG, movieReviewsMutableLiveData.toString());
            }

            @Override
            public void onFailure(Call<MovieReviewsResponse> call, Throwable t) {
                Log.d(LOG_TAG, t.toString());
            }
        });
        return movieReviewsMutableLiveData;
    }

    private List<MovieReviews> fetchMovieReviews (Response<MovieReviewsResponse> movieReviewsResponse) {
        List<MovieReviews> movieReviewsList = new ArrayList<>();

        if (movieReviewsResponse.body() != null && movieReviewsResponse.body().getResults() != null) {
            movieReviewsList = movieReviewsResponse.body().getResults();
        }

        return movieReviewsList;
    }

//    public LiveData<Movie> getMovieFromMovieId (int movieId) {
//        final MutableLiveData<Movie> movieDetailsMutableLiveData = new MutableLiveData<>();
//
//        mMoviesApiClient.getMoviesApiHandler().getMovieFromId(movieId).enqueue(new Callback<Movie>() {
//            @Override
//            public void onResponse(Call<Movie> call, Response<Movie> response) {
//                movieDetailsMutableLiveData.setValue(fetchMovieDetails(response));
//                Log.d(LOG_TAG, movieDetailsMutableLiveData.toString());
//            }
//
//            @Override
//            public void onFailure(Call<Movie> call, Throwable t) {
//                Log.d(LOG_TAG, t.toString());
//            }
//        });
//        return movieDetailsMutableLiveData;
//    }
//
//    private Movie fetchMovieDetails (Response<Movie> movieDetailsResponse) {
//        Movie movieDetails= new Movie();
//
//        if (movieDetailsResponse.body() != null) {
//            movieDetails = movieDetailsResponse.body();
//        }
//
//        return movieDetails;
//    }

    public LiveData<List<Movie>> getFavoriteMoviesLiveData() {
        LiveData<List<Movie>> mFavoriteMoviesList = mFavoriteMoviesDao.getFavoriteMovies();
        return mFavoriteMoviesList;
    }

    public LiveData<Movie> getFavoriteMovieById (int movieId) {
        return mFavoriteMoviesDao.getMovieById(movieId);
    }

    public void toggleFavoriteMovie (Movie movie) {
        AppExecutors.getInstance().diskIO().execute(() -> {
                boolean isFavorite = mFavoriteMoviesDao.isFavoriteMovie(movie.getMovieId());

                if (isFavorite) {
                    deleteFavoriteMovie(movie);
                } else {
                    insertFavoriteMovie(movie);
                }
        });
    }

    private void insertFavoriteMovie (Movie movie) {
        AppExecutors.getInstance().diskIO().execute(() -> mFavoriteMoviesDao.insertMovie(movie));
    }

    private void deleteFavoriteMovie (Movie movie) {
        AppExecutors.getInstance().diskIO().execute(() -> mFavoriteMoviesDao.deleteMovie(movie));
    }







}
