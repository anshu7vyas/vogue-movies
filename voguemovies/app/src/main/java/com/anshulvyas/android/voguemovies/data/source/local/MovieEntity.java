package com.anshulvyas.android.voguemovies.data.source.local;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "movies")
public class MovieEntity {

    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    private int movieId;

    @ColumnInfo(name = "original_title")
    private String originalTitle;

    @ColumnInfo(name="poster_path")
    private String posterPath;

    @ColumnInfo(name = "backdrop_path")
    private String backdropPath;

    private String overview;
    @ColumnInfo(name = "release_date")

    private String releaseDate;

    @ColumnInfo(name = "vote_average",typeAffinity = 4)
    private double voteAverage;

    @ColumnInfo(name = "movie_popular")
    private boolean moviePopular;

    @ColumnInfo(name = "movie_top_rated")
    private boolean movieTopRated;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public boolean isMoviePopular() {
        return moviePopular;
    }

    public void setMoviePopular(boolean moviePopular) {
        this.moviePopular = moviePopular;
    }

    public boolean isMovieTopRated() {
        return movieTopRated;
    }

    public void setMovieTopRated(boolean movieTopRated) {
        this.movieTopRated = movieTopRated;
    }
}
