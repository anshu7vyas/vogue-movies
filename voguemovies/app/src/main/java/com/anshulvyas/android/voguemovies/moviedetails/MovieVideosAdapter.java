package com.anshulvyas.android.voguemovies.moviedetails;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anshulvyas.android.voguemovies.R;
import com.anshulvyas.android.voguemovies.data.model.MovieVideos;
import com.anshulvyas.android.voguemovies.databinding.RvMovieVideosListItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieVideosAdapter extends RecyclerView.Adapter<MovieVideosAdapter.MovieVideosViewHolder> {
    private final Context mContext;
    private List<MovieVideos> mMovieVideosList;

    MovieVideosAdapter (Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public MovieVideosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.rv_movie_videos_list_item,
                parent,
                false);

        return new MovieVideosViewHolder(viewDataBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MovieVideosViewHolder holder, int position) {
        final MovieVideos movieVideos = mMovieVideosList.get(position);
        String thumbnailUrl = movieVideos.getVideoThumbnailUrl();

        Picasso.with(mContext)
                .load(thumbnailUrl)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.rvMovieVideosListItemBinding.ivVideoThumbnail);

        holder.rvMovieVideosListItemBinding.tvVideoName.setText(movieVideos.getVideoName());
        holder.rvMovieVideosListItemBinding.tvVideoType.setText(movieVideos.getVideoType());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(movieVideos.getVideoUrl()));
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (null == mMovieVideosList) return 0;
        return mMovieVideosList.size();
    }

    class MovieVideosViewHolder extends RecyclerView.ViewHolder {
        RvMovieVideosListItemBinding rvMovieVideosListItemBinding;

        MovieVideosViewHolder(View itemView) {
            super(itemView);
            rvMovieVideosListItemBinding = DataBindingUtil.bind(itemView);
        }
    }

    public void setMovieVideosData(List<MovieVideos> moviesData) {
        mMovieVideosList = moviesData;
        notifyDataSetChanged();
    }
}
