package com.starksky.movies.utils;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import com.starksky.movies.BuildConfig;
import com.starksky.movies.common.AppUrl;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by NTQK0716 on 2016-08-16.
 */
public class FetchMovieTrailers extends AsyncTask<Context, Void, Void> {
    String MOVIE_BASE_TRAILERS_URL = AppUrl.BASE_URL_MOVIE_TRAILERS ;


    @Override
    protected Void doInBackground(Context... contexts) {
        Uri builtUri = Uri.parse(MOVIE_BASE_TRAILERS_URL).buildUpon()
                .appendPath("hello")
                .appendPath("trailers")
                .appendQueryParameter(AppUrl.API_KEY_PARAM, BuildConfig.MOVIE_API_KEY)
                .build();

        try {
            URL url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
