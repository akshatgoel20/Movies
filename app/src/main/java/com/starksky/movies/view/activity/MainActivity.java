package com.starksky.movies.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.starksky.movies.R;
import com.starksky.movies.view.fragment.GridPosterFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null){
          getSupportFragmentManager().beginTransaction()
                  .add(R.id.container,new  GridPosterFragment())
                  .commit();
        }
    }
}
