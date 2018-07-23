package com.anshulvyas.android.voguemovies.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.anshulvyas.android.voguemovies.data.model.Movie;

@Database(entities = {Movie.class}, version = 2, exportSchema = false)
public abstract class FavoriteMoviesDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "movies.db";
    private static final Object LOCK = new Object();
    private static FavoriteMoviesDatabase sInstance = null;

    public abstract FavoriteMoviesDao favoriteMoviesDao();

    public static FavoriteMoviesDatabase getInstance (final Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        FavoriteMoviesDatabase.class, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
                }
            }
        return sInstance;
    }
}
