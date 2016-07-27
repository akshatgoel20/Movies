package com.starksky.movies.view.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.starksky.movies.R;
import com.starksky.movies.utils.CommonUtils;
import com.starksky.movies.view.fragment.GridPosterFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null && CommonUtils.isNetworkAvailable(this)){
          getSupportFragmentManager().beginTransaction()
                  .add(R.id.container,new  GridPosterFragment())
                  .commit();
        }else{
            CommonUtils.showDialog(this, "Loading... Please connect to internet");
            return;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_refresh && CommonUtils.isNetworkAvailable(this)){
            startActivity(new Intent(this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
