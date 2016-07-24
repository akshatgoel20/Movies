package com.starksky.movies.utils;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.starksky.movies.BuildConfig;
import com.starksky.movies.model.ArrayMovieDetails;
import com.starksky.movies.model.MovieDetailsModel;

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
import java.util.List;

/**
 * Created by akshat on 23/07/16.
 */
public class FetchPopularMovie extends AsyncTask<String, Void, Void> {


    @Override
    protected Void doInBackground(String... objects) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String format = "json";
        String movieJsonString = null;
        try {
            final String MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie/popular?";
            final String API_KEY_PARAM = "api_key";
            Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                    .appendQueryParameter(API_KEY_PARAM, BuildConfig.MOVIE_API_KEY)
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


    private String[] getMoviewDataFromJson(String str) throws JSONException {
        final String OWM_RESULT = "results";

        JSONObject jsonObject = new JSONObject(str);
        JSONArray jsonArray = jsonObject.getJSONArray(OWM_RESULT);

        ArrayList<MovieDetailsModel> list = new ArrayList<>();

        Type listType = new TypeToken<ArrayList<MovieDetailsModel>>() {
        }.getType();

        list = new GsonBuilder().create().fromJson(String.valueOf(jsonArray), listType);
        Log.d("hello",list.get(0).getPoster_path());
        new ArrayMovieDetails().setArrayList(list);

        return null ;

    }
}
