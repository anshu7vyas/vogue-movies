package com.anshulvyas.android.voguemovies.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieReviews {
    /**
     * author : Screen-Space
     * content : "It is a bold undertaking, to readjust what is expected of the MCU/Avengers formula, and there are moments when the sheer scale and momentum match the narrative ambition..."

     Read the full review here: http://screen-space.squarespace.com/reviews/2018/4/25/avengers-infinity-war.html
     * id : 5adff809c3a3683daa00ad3d
     * url : https://www.themoviedb.org/review/5adff809c3a3683daa00ad3d
     */

    @SerializedName("author")
    private String reviewAuthor;

    @SerializedName("content")
    private String reviewContent;

    @SerializedName("id")
    private String reviewId;

    @SerializedName("url")
    private String reviewUrl;

    public String getReviewAuthor() {
        return reviewAuthor;
    }

    public void setReviewAuthor(String reviewAuthor) {
        this.reviewAuthor = reviewAuthor;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewUrl() {
        return reviewUrl;
    }

    public void setReviewUrl(String reviewUrl) {
        this.reviewUrl = reviewUrl;
    }
}
