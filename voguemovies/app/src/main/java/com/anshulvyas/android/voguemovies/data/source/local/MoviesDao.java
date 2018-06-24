package com.anshulvyas.android.voguemovies.data.source.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MoviesDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertMovie (MovieEntity movieEntity);

    @Query("SELECT * FROM movies WHERE movie_popular = 1")
    LiveData<List<MovieEntity>> loadAllPopularMovies();

    @Query("SELECT * FROM movies WHERE movie_top_rated = 1")
    LiveData<List<MovieEntity>> loadAllTopRatedMovies();

    @Query("SELECT * FROM movies WHERE movie_id = :id")
    LiveData<MovieEntity> loadMovieById(int id);
}
