package com.anshulvyas.android.voguemovies.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieVideosResponse {
    /**
     * id : 299536
     * results : [{"id":"5a200baa925141033608f5f0","iso_639_1":"en","iso_3166_1":"US","key":"6ZfuNTqbHE8","name":"Official Trailer","site":"YouTube","size":1080,"type":"Trailer"},{"id":"5a200bcc925141032408d21b","iso_639_1":"en","iso_3166_1":"US","key":"sAOzrChqmd0","name":"Action...Avengers: Infinity War","site":"YouTube","size":720,"type":"Clip"},{"id":"5a200bdd0e0a264cca08d39f","iso_639_1":"en","iso_3166_1":"US","key":"3VbHg5fqBYw","name":"Trailer Tease","site":"YouTube","size":720,"type":"Teaser"},{"id":"5a7833440e0a26597f010849","iso_639_1":"en","iso_3166_1":"US","key":"pVxOVlm_lE8","name":"Big Game Spot","site":"YouTube","size":1080,"type":"Teaser"},{"id":"5aabd7e69251413feb011276","iso_639_1":"en","iso_3166_1":"US","key":"QwievZ1Tx-8","name":"Official Trailer #2","site":"YouTube","size":1080,"type":"Trailer"},{"id":"5aea2ed2c3a3682bf7001205","iso_639_1":"en","iso_3166_1":"US","key":"LXPaDL_oILs","name":"\"Legacy\" TV Spot","site":"YouTube","size":1080,"type":"Teaser"},{"id":"5aea2f3e92514172a7001672","iso_639_1":"en","iso_3166_1":"US","key":"PbRmbhdHDDM","name":"\"Family\" Featurette","site":"YouTube","size":1080,"type":"Featurette"}]
     */

    @SerializedName("id")
    private int movieId;

    @SerializedName("results")
    private List<MovieVideos> results;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public List<MovieVideos> getResults() {
        return results;
    }

    public void setResults(List<MovieVideos> results) {
        this.results = results;
    }

}
