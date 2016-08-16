package com.starksky.movies.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.starksky.movies.BuildConfig;
import com.starksky.movies.common.AppUrl;
import com.starksky.movies.model.ArrayMovieDetails;
import com.starksky.movies.model.MovieDetailsModel;
import com.starksky.movies.view.fragment.GridPosterFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by akshat on 23/07/16.
 */
public class FetchPopularMovie extends AsyncTask<Context, Void, Void> {

    Context context;

    @Override
    protected Void doInBackground(Context... objects) {
        context = objects[0];
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String format = "json";
        String movieJsonString = null;
          String MOVIE_BASE_URL ="";

        try {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            String movieSharedPref = sharedPreferences.getString("sort","mpop");
            if(movieSharedPref.equals("mpop")){
                  MOVIE_BASE_URL = AppUrl.BASE_URL_POPULAR;
            }else if(movieSharedPref.equals("hrate")){
                MOVIE_BASE_URL=AppUrl.BASE_URL_TOPRATED;
            }

            Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                    .appendQueryParameter(AppUrl.API_KEY_PARAM, BuildConfig.MOVIE_API_KEY)
                    .build();

            URL url = new URL(builtUri.toString());


            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {

                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {

                return null;
            }
            movieJsonString = buffer.toString();
            getMoviewDataFromJson(movieJsonString);
            loadImages();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }



    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
       CommonUtils.stopDialog();
       GridPosterFragment.updateGridView();

    }

    private void loadImages() {
        for (int i = 0; i < ArrayMovieDetails.getArrayList().size(); i++) {
            String url = AppUrl.BASE_URL_IMAGE.concat(ArrayMovieDetails.getArrayList().get(i).getPoster_path());
            Picasso.with(context)
                    .load(url);

        }
    }

    private String[] getMoviewDataFromJson(String str) throws JSONException {
        final String OWM_RESULT = "results";

        JSONObject jsonObject = new JSONObject(str);
        JSONArray jsonArray = jsonObject.getJSONArray(OWM_RESULT);

        ArrayList<MovieDetailsModel> list = new ArrayList<>();

        Type listType = new TypeToken<ArrayList<MovieDetailsModel>>() {
        }.getType();

        list = new GsonBuilder().create().fromJson(String.valueOf(jsonArray), listType);
        Log.d("hello", list.get(0).getPoster_path());
        new ArrayMovieDetails().setArrayList(list);

        return null;

    }
}
