package com.anshulvyas.android.voguemovies.moviedetails;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.anshulvyas.android.voguemovies.data.model.MovieReviews;
import com.anshulvyas.android.voguemovies.databinding.RvMovieReviewsListItemBinding;

import java.util.List;

public class MovieReviewsAdapter extends RecyclerView.Adapter<MovieReviewsAdapter.MovieReviewsViewHolder> {
    private final Context mContext;
    private final List<MovieReviews> mMovieReviewsList;

    public MovieReviewsAdapter (Context context, List<MovieReviews> movieReviewsList) {
        this.mContext = context;
        this.mMovieReviewsList = movieReviewsList;
    }

    @NonNull
    @Override
    public MovieReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieReviewsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MovieReviewsViewHolder extends RecyclerView.ViewHolder {
        RvMovieReviewsListItemBinding rvMovieReviewsListItemBinding;

        MovieReviewsViewHolder (View itemView) {
            super (itemView);
            rvMovieReviewsListItemBinding = DataBindingUtil.bind(itemView);
        }

    }
}
