package com.starksky.movies.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.starksky.movies.R;
import com.starksky.movies.adapter.GridPosterAdapter;
import com.starksky.movies.iface.ResponseListener;
import com.starksky.movies.utils.CommonUtils;
import com.starksky.movies.utils.FetchMovieReviews;
import com.starksky.movies.utils.FetchMovieTrailers;
import com.starksky.movies.utils.FetchPopularMovie;
import com.starksky.movies.view.activity.SettingsActivity;

public class GridPosterFragment extends Fragment implements ResponseListener {

    private static final String TAG = GridPosterFragment.class.getSimpleName();
    static GridView gridView;
    static GridPosterAdapter gridPosterAdapter;
    private int mPosition = GridView.INVALID_POSITION;
    private static final String SELECTED_KEY = "selected_position";

    public GridPosterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_grid_poster, container, false);
        gridView = (GridView) rootview.findViewById(R.id.postergrid);
        gridPosterAdapter = new GridPosterAdapter(getActivity());
        gridView.setAdapter(gridPosterAdapter);
        gridPosterAdapter.notifyDataSetChanged();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", i);
                // movieDetailPosition.setPosition(i);
                Fragment fragment = new MovieDetailFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                new FetchMovieTrailers(getActivity(), i).execute();
                new FetchMovieReviews(getActivity(), i).execute();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                if(!isTab()) {
                    fragmentTransaction.replace(R.id.container, fragment).addToBackStack(TAG);
                    //  fragmentTransaction.attach(fragment);

                    fragmentTransaction.commit();
                }else {
                  //  Fragment fragment1 = getActivity().getSupportFragmentManager().findFragmentById(R.id.movie_detail_container);
                 /*   fragmentTransaction.detach(fragment1);
                    fragmentTransaction.attach(fragment1);
                    fragmentTransaction.commit();*/

                    new FetchMovieTrailers(getActivity(), i).execute();
                    new FetchMovieReviews(getActivity(), i).execute();
                 MovieDetailFragment movieDetailFragment =    MovieDetailFragment.newInstance(i);

                 //   fragmentTransaction.detach(fragment1);
                    fragmentTransaction.replace(R.id.movie_detail_container,movieDetailFragment);
                    fragmentTransaction.commit();
                }
            }
        });

        if (savedInstanceState != null && savedInstanceState.containsKey(SELECTED_KEY)) {
            // The gridview probably hasn't even been populated yet.  Actually perform the
            // swapout in onLoadFinished.
            mPosition = savedInstanceState.getInt(SELECTED_KEY);
        }

        return rootview;
    }

    boolean isTab() {
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.movie_detail_container);
      if(fragment != null){
          return  true ;
      }
        return false ;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.gridposter_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getActivity(), SettingsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (CommonUtils.isNetworkAvailable(getActivity())) {
            updateInfo();
        } else {
            CommonUtils.toast(getActivity(), "Please connect to internet");
            return;
        }


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //to save the instance state, gets called when activity gets closed
        mPosition = gridView.getFirstVisiblePosition();
        if (mPosition != GridView.INVALID_POSITION) {
            outState.putInt(SELECTED_KEY, mPosition);
        }
        super.onSaveInstanceState(outState);
    }

    void updateInfo() {
        CommonUtils.showDialog(getActivity(), "Loading...");
        new FetchPopularMovie(this).execute(getActivity());
    }


    @Override
    public void update() {
        gridView.setAdapter(gridPosterAdapter);
        gridView.smoothScrollToPosition(mPosition);
    }
}
