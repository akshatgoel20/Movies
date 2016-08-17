package com.starksky.movies.utils;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import com.android.volley.toolbox.Volley;
import com.starksky.movies.BuildConfig;
import com.starksky.movies.common.AppUrl;
import com.starksky.movies.model.ArrayMovieDetails;
import com.starksky.movies.network.APIErrorException;
import com.starksky.movies.network.APIListner;
import com.starksky.movies.network.VolleyRequestManager;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by NTQK0716 on 2016-08-16.
 */
public class FetchMovieTrailers extends AsyncTask<Void, Void, Void> {
    String MOVIE_BASE_TRAILERS_URL = AppUrl.BASE_URL_MOVIE;
    private Context context;
    String id  ;

    public FetchMovieTrailers(Context context, int position) {
        this.context = context ;

        id = ArrayMovieDetails.getArrayList().get(position).getMovie_id();
    }


    @Override
    protected Void doInBackground(Void... voids) {
        Uri builtUri = Uri.parse(MOVIE_BASE_TRAILERS_URL).buildUpon()
                .appendPath(id)
                .appendPath("trailers")
                .appendQueryParameter(AppUrl.API_KEY_PARAM, BuildConfig.MOVIE_API_KEY)
                .build();

        try {
            URL url = new URL(builtUri.toString());
            VolleyRequestManager.getInstance(context).getMovieData(url, new APIListner.Success<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, new APIListner.Error() {
                @Override
                public void onErrorResponse(APIErrorException error) {

                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
