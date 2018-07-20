package com.anshulvyas.android.voguemovies.moviedetails;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anshulvyas.android.voguemovies.R;
import com.anshulvyas.android.voguemovies.data.model.MovieReviews;
import com.anshulvyas.android.voguemovies.data.model.MovieVideos;
import com.anshulvyas.android.voguemovies.databinding.RvMovieVideosListItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieVideosAdapter extends RecyclerView.Adapter<MovieVideosAdapter.MovieVideosViewHolder> {
    private final Context mContext;
    private final List<MovieVideos> mMovieVideosList;

    public MovieVideosAdapter (Context context, List<MovieVideos> movieVideosList) {
        this.mContext = context;
        this.mMovieVideosList = movieVideosList;
    }

    @NonNull
    @Override
    public MovieVideosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_movie_videos_list_item, parent, false);

        return new MovieVideosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieVideosViewHolder holder, int position) {
        final MovieVideos movieVideos = mMovieVideosList.get(position);

//        Picasso.with(mContext)
//                .load(movieVideos.getTrailerThumbnailUrl())
//                .placeholder(android.R.drawable.sym_def_app_icon)
//                .error(android.R.drawable.sym_def_app_icon)
//                .into(holder.);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MovieVideosViewHolder extends RecyclerView.ViewHolder {
        RvMovieVideosListItemBinding rvMovieVideosListItemBinding;



        MovieVideosViewHolder(View itemView) {
            super(itemView);
            rvMovieVideosListItemBinding = DataBindingUtil.bind(itemView);
        }
    }
}
