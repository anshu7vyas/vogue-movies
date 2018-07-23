package com.anshulvyas.android.voguemovies.movies;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anshulvyas.android.voguemovies.MoviesApplication;
import com.anshulvyas.android.voguemovies.R;
import com.anshulvyas.android.voguemovies.data.model.Movie;
import com.anshulvyas.android.voguemovies.databinding.RvMoviesListItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Recycler view Adapter for populating movies in the Main Activity.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder> {

    private static final String LOG_TAG = MoviesAdapter.class.getSimpleName();

    private Context mContext;
    private List<Movie> mMoviesList;

    private final MoviesAdapterOnClickHandler mMoviesClickHandler;

    MoviesAdapter(Context context, MoviesAdapterOnClickHandler moviesAdapterOnClickHandler) {
        this.mContext = context;
        this.mMoviesClickHandler = moviesAdapterOnClickHandler;
    }

    public interface MoviesAdapterOnClickHandler {
        void onClick(Movie movie);
    }

    @NonNull
    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.rv_movies_list_item,
                parent,
                false);

        return new MoviesAdapterViewHolder(viewDataBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapterViewHolder holder, int position) {
        Movie movie = mMoviesList.get(position);
        String imageUrl = MoviesApplication.BASE_URL_POSTER_IMAGE + movie.getPosterPath();

        Picasso.with(mContext)
                .load(imageUrl)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.moviesListItemBinding.ivMoviesItem);
    }

    @Override
    public int getItemCount() {
        if (null == mMoviesList) return 0;
        return mMoviesList.size();
    }

    class MoviesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RvMoviesListItemBinding moviesListItemBinding;


        MoviesAdapterViewHolder(View itemView) {
            super(itemView);
            moviesListItemBinding = DataBindingUtil.bind(itemView);
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
