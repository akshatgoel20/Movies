package com.starksky.movies.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.starksky.movies.R;
import com.starksky.movies.iface.ResponseListener;
import com.starksky.movies.utils.CommonUtils;
import com.starksky.movies.utils.FetchMovieReviews;
import com.starksky.movies.utils.FetchMovieTrailers;
import com.starksky.movies.utils.FetchPopularMovie;
import com.starksky.movies.view.fragment.GridPosterFragment;
import com.starksky.movies.view.fragment.MovieDetailFragment;

public class MainActivity extends AppCompatActivity implements ResponseListener {

    private static final String DETAILFRAGMENT_TAG = "DFTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (!CommonUtils.isNetworkAvailable(this)) {
            CommonUtils.showDialog(this, "Loading... Please connect to internet");
            return;
        }
        new FetchPopularMovie(this).execute(this);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

            if (isTab()) {
                new FetchMovieTrailers(this, 0).execute();
                new FetchMovieReviews(this, 0).execute();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_detail_container, new MovieDetailFragment(), DETAILFRAGMENT_TAG)
                        .commit();

            } else {
                setContentView(R.layout.activity_main);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new GridPosterFragment())
                        .commit();
            }
        }
    }

    public boolean isTab() {
        if (findViewById(R.id.movie_detail_container) != null) {
            CommonUtils.setIsTb(true);
            return true;
        } else {
            CommonUtils.setIsTb(false);
            return false;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh && CommonUtils.isNetworkAvailable(this)) {
            startActivity(new Intent(this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void update() {
        //
    }
}
