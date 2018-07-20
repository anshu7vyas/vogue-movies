package com.anshulvyas.android.voguemovies.moviedetails;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anshulvyas.android.voguemovies.R;
import com.anshulvyas.android.voguemovies.data.model.MovieReviews;
import com.anshulvyas.android.voguemovies.databinding.RvMovieReviewsListItemBinding;

import java.util.List;

public class MovieReviewsAdapter extends RecyclerView.Adapter<MovieReviewsAdapter.MovieReviewsViewHolder> {
    private Context mContext;
    private List<MovieReviews> mMovieReviewsList;

    MovieReviewsAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public MovieReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.rv_movie_reviews_list_item,
                parent,
                false);

        return new MovieReviewsViewHolder(viewDataBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MovieReviewsViewHolder holder, int position) {
        final MovieReviews movieReviews = mMovieReviewsList.get(position);

        holder.rvMovieReviewsListItemBinding.tvReviewAuthor
                .setText(movieReviews.getReviewAuthor());
        holder.rvMovieReviewsListItemBinding.tvReviewContent
                .setText(movieReviews.getReviewContent());

    }

    @Override
    public int getItemCount() {
        if (null == mMovieReviewsList) return 0;
        return mMovieReviewsList.size();
    }

    class MovieReviewsViewHolder extends RecyclerView.ViewHolder {
        RvMovieReviewsListItemBinding rvMovieReviewsListItemBinding;

        MovieReviewsViewHolder(View itemView) {
            super(itemView);
            rvMovieReviewsListItemBinding = DataBindingUtil.bind(itemView);
        }

    }

    public void setMovieReviewsData(List<MovieReviews> moviesData) {
        mMovieReviewsList = moviesData;
        notifyDataSetChanged();
    }

}
