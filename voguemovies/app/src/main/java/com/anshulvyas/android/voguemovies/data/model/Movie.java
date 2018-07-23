package com.anshulvyas.android.voguemovies.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Movie JSON response POJO
 */
@Entity(tableName = "movie")
public class Movie implements Parcelable {

    /**
     * vote_count : 3705
     * id : 299536
     * video : false
     * vote_average : 8.5
     * title : Avengers: Infinity War
     * popularity : 608.963529
     * poster_path : /7WsyChQLEftFiDOVTGkv3hFpyyt.jpg
     * original_language : en
     * original_title : Avengers: Infinity War
     * genre_ids : [12,878,14,28]
     * backdrop_path : /bOGkgRGdhrBYJSLpXaxhXVstddV.jpg
     * adult : false
     * overview : As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.
     * release_date : 2018-04-25
     */
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    @SerializedName("id")
    private Integer movieId;

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    private Double voteAverage;

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    private String posterPath;

    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    private String originalTitle;

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    private String backdropPath;

    @ColumnInfo(name = "movie_overview")
    @SerializedName("overview")
    private String movieOverview;

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    private String movieReleaseDate;

    @ColumnInfo(name = "favorite")
    private boolean isFavorite;

    @Ignore
    Movie() {

    }

    public Movie(Integer movieId, String originalTitle, String movieOverview, String posterPath, String backdropPath,
                 String movieReleaseDate, Double voteAverage, boolean isFavorite) {
        this.movieId = movieId;
        this.originalTitle = originalTitle;
        this.movieOverview = movieOverview;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.movieReleaseDate = movieReleaseDate;
        this.voteAverage = voteAverage;
        this.isFavorite = isFavorite;
    }

    @Ignore
    @SerializedName("vote_count")
    private Integer voteCount;

    @Ignore
    @SerializedName("video")
    private Boolean movieVideo;

    @Ignore
    @SerializedName("title")
    private String movieTitle;

    @Ignore
    @SerializedName("popularity")
    private Double moviePopularity;

    @Ignore
    @SerializedName("original_language")
    private String originalLanguage;

    @Ignore
    @SerializedName("adult")
    private Boolean isAdult;

    @Ignore
    @SerializedName("genre_ids")
    private List<Integer> movieGenreIds;

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Boolean isMovieVideo() {
        return movieVideo;
    }

    public void setMovieVideo(Boolean movieVideo) {
        this.movieVideo = movieVideo;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public Double getMoviePopularity() {
        return moviePopularity;
    }

    public void setMoviePopularity(Double moviePopularity) {
        this.moviePopularity = moviePopularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Boolean getIsAdult() {
        return isAdult;
    }

    public void setIsAdult(Boolean isAdult) {
        this.isAdult = isAdult;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieReleaseDate(String movieReleaseDate) {
        this.movieReleaseDate = movieReleaseDate;
    }

    public List<Integer> getMovieGenreIds() {
        return movieGenreIds;
    }

    public void setMovieGenreIds(List<Integer> movieGenreIds) {
        this.movieGenreIds = movieGenreIds;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean favorite){
        this.isFavorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.movieId);
        dest.writeString(this.originalTitle);
        dest.writeString(this.movieOverview);
        dest.writeString(this.posterPath);
        dest.writeString(this.backdropPath);
        dest.writeString(this.movieReleaseDate);
        dest.writeDouble(this.voteAverage);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    private Movie(Parcel in) {
        this.movieId = in.readInt();
        this.originalTitle = in.readString();
        this.movieOverview = in.readString();
        this.posterPath = in.readString();
        this.backdropPath = in.readString();
        this.movieReleaseDate = in.readString();
        this.voteAverage = in.readDouble();
    }
}
