package com.anshulvyas.android.voguemovies.movies;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PersistableBundle;
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


public class MoviesActivity extends AppCompatActivity implements MoviesAdapter.MoviesAdapterOnClickHandler {

    private static final String SPINNER_CATEGORY_POSITION_KEY = "category_position";

    private MoviesAdapter mMoviesAdapter;
    private ActivityMoviesBinding mBinding;
    private MoviesActivityViewModel mMoviesViewModel;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putInt(SPINNER_CATEGORY_POSITION_KEY, mBinding.spinnerCategory.getSelectedItemPosition());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        int orientation = this.getResources().getConfiguration().orientation;

        mSharedPreferences = getSharedPreferences(SPINNER_CATEGORY_POSITION_KEY, MODE_PRIVATE);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movies);

        mMoviesAdapter = new MoviesAdapter(this, this);
        mMoviesViewModel = ViewModelProviders.of(this).get(MoviesActivityViewModel.class);

        if (savedInstanceState != null) {
            mBinding.spinnerCategory.setSelection(savedInstanceState.getInt(SPINNER_CATEGORY_POSITION_KEY, 0));
        }

        populateMoviesList(mMoviesAdapter, orientation);

        int selectedPosition = mSharedPreferences.getInt(SPINNER_CATEGORY_POSITION_KEY, 0);
        categorySort(selectedPosition);

    }

    /**
     * Observes changes in the LiveData and updates the recycler view according to the Spinner category
     * categoryPosition - 0 -> Popular
     * - 1 -> Top-Rated
     *
     * @param categoryPosition
     */
    private void subscribe(int categoryPosition) {
        if (categoryPosition == 0) {
            mMoviesViewModel.getPopularMoviesList().observe(this, v -> {
                mMoviesAdapter.setMoviesData(v);
            });
        } else if (categoryPosition == 1) {
            mMoviesViewModel.getTopRatedMoviesList().observe(this, v -> {
                mMoviesAdapter.setMoviesData(v);
            });
        } else if (categoryPosition == 2) {
            mMoviesViewModel.getFavoriteMoviesList().observe(this, v -> {
                mMoviesAdapter.setMoviesData(v);
            });
        }

    }

    @Override
    public void onClick(Movie movie) {
        Context context = this;
        Intent openMovieDetailsActivity = new Intent(context, MovieDetailsActivity.class);
        openMovieDetailsActivity.putExtra(Intent.EXTRA_TEXT, movie);
        startActivity(openMovieDetailsActivity);
    }

    /**
     * Handles the spinner item selection in the Main Screen
     */
    private void categorySort(int position) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_sort, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mBinding.spinnerCategory.setAdapter(adapter);
        mBinding.spinnerCategory.setSelection(position);

        mBinding.spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mEditor = mSharedPreferences.edit();

                mEditor.putInt(SPINNER_CATEGORY_POSITION_KEY, position);
                mEditor.commit();

                if (isOnline()) {
                    connected();
                    subscribe(position);
                    mMoviesAdapter.notifyDataSetChanged();
                } else {
                    disconnected();
                }
                mBinding.rvMovies.scrollToPosition(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Sets up the recycler view and populates with the data received
     *
     * @param adapter movies Adapter
     */
    private void populateMoviesList(MoviesAdapter adapter, int orientation) {
        mBinding.rvMovies.setAdapter(adapter);
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            LinearLayoutManager layoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
            mBinding.rvMovies.setLayoutManager(layoutManager);
        } else {
            LinearLayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
            mBinding.rvMovies.setLayoutManager(layoutManager);
        }
    }

    /**
     * Checks for internet connectivity
     *
     * @return whether there is network or not
     */
    private boolean isOnline() {
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

//    private void setupSharedPreferences() {
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putInt(SPINNER_CATEGORY_POSITION_KEY, mBinding.spinnerCategory.getSelectedItemPosition());
//        editor.commit();
//    }



}
