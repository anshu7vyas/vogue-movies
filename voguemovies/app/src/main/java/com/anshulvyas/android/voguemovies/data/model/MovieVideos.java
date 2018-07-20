package com.anshulvyas.android.voguemovies.data.model;

import com.google.gson.annotations.SerializedName;

public class MovieVideos {
    /**
     * id : 5a200baa925141033608f5f0
     * iso_639_1 : en
     * iso_3166_1 : US
     * key : 6ZfuNTqbHE8
     * name : Official Trailer
     * site : YouTube
     * size : 1080
     * type : Trailer
     */

    private static final String VIDEO_BASE_URL = "https://www.youtube.com/watch?v=";
    private static final String VIDEO_THUMBNAIL_BASE_URL = "https://img.youtube.com/vi/";
    private static final String VIDEO_THUMBNAIL_END_PATH = "/default.jpg";

    @SerializedName("id")
    private String videoId;

    @SerializedName("iso_639_1")
    private String iso6391;

    @SerializedName("iso_3166_1")
    private String iso31661;

    @SerializedName("key")
    private String videoKey;

    @SerializedName("name")
    private String videoName;

    @SerializedName("site")
    private String videoSite;

    @SerializedName("size")
    private int videoSize;

    @SerializedName("type")
    private String videoType;

    public String getVideoUrl(){
        return VIDEO_BASE_URL + videoKey;
    }

    public String getVideoThumbnailUrl(){
        return VIDEO_THUMBNAIL_BASE_URL + videoKey + VIDEO_THUMBNAIL_END_PATH;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getIso31661() {
        return iso31661;
    }

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public String getVideoKey() {
        return videoKey;
    }

    public void setVideoKey(String videoKey) {
        this.videoKey = videoKey;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoSite() {
        return videoSite;
    }

    public void setVideoSite(String videoSite) {
        this.videoSite = videoSite;
    }

    public int getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(int videoSize) {
        this.videoSize = videoSize;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }
}