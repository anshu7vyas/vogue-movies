package com.anshulvyas.android.voguemovies.movies;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.anshulvyas.android.voguemovies.R;
import com.anshulvyas.android.voguemovies.data.model.Movie;
import com.anshulvyas.android.voguemovies.databinding.ActivityMoviesBinding;
import com.anshulvyas.android.voguemovies.moviedetails.MovieDetailsActivity;
import com.google.gson.Gson;

import java.util.List;

public class MoviesActivity extends AppCompatActivity implements MoviesAdapter.MoviesAdapterOnClickHandler {

    private MoviesAdapter mMoviesAdapter;
    private ActivityMoviesBinding mBinding;
    private MoviesActivityViewModel mMoviesViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movies);

        mMoviesAdapter = new MoviesAdapter(this, this);
        mMoviesViewModel = ViewModelProviders.of(this).get(MoviesActivityViewModel.class);


        populateMoviesList(mMoviesAdapter);
        categorySort();
    }

    /**
     * Observes changes in the LiveData and updates the recycler view according to the Spinner category
     * categoryPosition - 0 -> Popular
     *                  - 1 -> Top-Rated
     * @param categoryPosition
     */
    public void subscribe(int categoryPosition) {
        if (categoryPosition == 0) {
            mMoviesViewModel.getPopularMoviesList().observe(this, new Observer<List<Movie>>() {
                @Override
                public void onChanged(@Nullable List<Movie> movies) {
                    if (movies != null) {
                        mMoviesAdapter.setMoviesData(movies);
                    }
                }
            });
        } else {
            mMoviesViewModel.getTopRatedMoviesList().observe(this, new Observer<List<Movie>>() {
                @Override
                public void onChanged(@Nullable List<Movie> movies) {
                    if (movies != null) {
                        mMoviesAdapter.setMoviesData(movies);
                    }
                }
            });
        }

    }

    @Override
    public void onClick(Movie movie) {
        Context context = this;
        Gson gson = new Gson();
        Intent openMovieDetailsActivity = new Intent(context, MovieDetailsActivity.class);
        openMovieDetailsActivity.putExtra(Intent.EXTRA_TEXT, gson.toJson(movie));
        startActivity(openMovieDetailsActivity);
    }

    /**
     * Handles the spinner item selection in the Main Screen
     */
    private void categorySort() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_sort, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mBinding.spinnerCategory.setAdapter(adapter);

        mBinding.spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0 && isOnline()) {
                    connected();
                    subscribe(position);
                } else if (position == 1 && isOnline()){
                    connected();
                    subscribe(position);
                } else {
                    disconnected();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Sets up the recycler view and populates with the data received
     * @param adapter
     */
    private void populateMoviesList(MoviesAdapter adapter) {
        mBinding.rvMovies.setAdapter(adapter);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        mBinding.rvMovies.setLayoutManager(layoutManager);
    }

    /**
     * Checks for internet connectivity
     * @return
     */
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    /**
     * If internet is available, hides the connection error message
     */
    private void connected() {
        mBinding.rvMovies.setVisibility(View.VISIBLE);
        mBinding.ivNoInternet.setVisibility(View.INVISIBLE);
        mBinding.tvNoInternet.setVisibility(View.INVISIBLE);
    }

    /**
     * If internet is unavailable, shows the connection error message
     */
    private void disconnected() {
        mBinding.rvMovies.setVisibility(View.INVISIBLE);
        mBinding.ivNoInternet.setVisibility(View.VISIBLE);
        mBinding.tvNoInternet.setVisibility(View.VISIBLE);
    }

}
