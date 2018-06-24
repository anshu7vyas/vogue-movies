package com.anshulvyas.android.voguemovies.movies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.anshulvyas.android.voguemovies.R;
import com.anshulvyas.android.voguemovies.data.model.Movie;
import com.anshulvyas.android.voguemovies.data.source.remote.MoviesApiClient;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder>{

    private static final String LOG_TAG = MoviesAdapter.class.getSimpleName();

    private Context mContext;
    private List<Movie> mMoviesList;

    private final MoviesAdapterOnClickHandler mMoviesClickHandler;

    public MoviesAdapter(Context context, MoviesAdapterOnClickHandler moviesAdapterOnClickHandler) {
        this.mContext = context;
        this.mMoviesClickHandler = moviesAdapterOnClickHandler;
    }

    public interface MoviesAdapterOnClickHandler {
        void onClick(Movie movie);
    }

    @NonNull
    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.rv_movies_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);

        return new MoviesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapterViewHolder holder, int position) {
        String imageUrl = MoviesApiClient.BASE_URL_POSTER_IMAGE + mMoviesList.get(position).getPosterPath();

        Picasso.with(mContext)
                .load(imageUrl)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.mMovieImageView);

    }

    @Override
    public int getItemCount() {
        if (null == mMoviesList) return 0;
        return mMoviesList.size();
    }

    class MoviesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView mMovieImageView;

        public MoviesAdapterViewHolder(View itemView) {
            super(itemView);
            mMovieImageView = itemView.findViewById(R.id.iv_movies_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Log.d(LOG_TAG, "Inside onClick(View v) of MoviesAdapterViewHolder");
            Movie movieDetails = mMoviesList.get(adapterPosition);
            mMoviesClickHandler.onClick(movieDetails);
        }
    }

    public void setMoviesData(List<Movie> moviesData) {
        mMoviesList = moviesData;
        notifyDataSetChanged();
    }
}
