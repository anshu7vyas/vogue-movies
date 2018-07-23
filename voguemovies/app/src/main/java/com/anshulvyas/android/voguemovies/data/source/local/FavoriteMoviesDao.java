package com.anshulvyas.android.voguemovies.data.source.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.anshulvyas.android.voguemovies.data.model.Movie;

import java.util.List;

@Dao
public interface FavoriteMoviesDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertMovie (Movie movie);

    @Delete
    void deleteMovie (Movie movie);

    @Query("SELECT * FROM movie WHERE movie_id == :movieId")
    LiveData<Movie> getMovieById (int movieId);

    @Query("SELECT * FROM movie ORDER BY original_title ASC")
    LiveData<List<Movie>> getFavoriteMovies();

    @Query("SELECT count(*) from movie WHERE movie_id == :movieId")
    boolean isFavoriteMovie (int movieId);
}
