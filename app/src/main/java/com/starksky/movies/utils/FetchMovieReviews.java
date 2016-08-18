package com.starksky.movies.utils;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.starksky.movies.BuildConfig;
import com.starksky.movies.common.AppUrl;
import com.starksky.movies.model.ArrayMovieDetails;
import com.starksky.movies.model.ReviewDetails;
import com.starksky.movies.network.APIErrorException;
import com.starksky.movies.network.APIListner;
import com.starksky.movies.network.VolleyRequestManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by NTQK0716 on 2016-08-16.
 */
public class FetchMovieReviews extends AsyncTask<Void, Void, Void> {
    private static final String TAG = FetchMovieReviews.class.getSimpleName();
    String MOVIE_BASE_TRAILERS_URL = AppUrl.BASE_URL_MOVIE;
    private Context context;
    String id;

    public FetchMovieReviews(Context context, int position) {
        this.context = context;
        id = ArrayMovieDetails.getArrayList().get(position).getMovie_id();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Uri builtUri = Uri.parse(MOVIE_BASE_TRAILERS_URL).buildUpon()
                .appendPath(id)
                .appendPath("reviews")
                .appendQueryParameter(AppUrl.API_KEY_PARAM, BuildConfig.MOVIE_API_KEY)
                .build();

        try {
            URL url = new URL(builtUri.toString());
            VolleyRequestManager.getInstance(context).getMovieData(url, new APIListner.Success<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    final String OWM_RESULT = "results";
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = response.getJSONArray(OWM_RESULT);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ArrayList<ReviewDetails> list = new ArrayList<ReviewDetails>();
                    Type listType = new TypeToken<ArrayList<ReviewDetails>>() {
                    }.getType();
                    list = new GsonBuilder().create().fromJson(String.valueOf(jsonArray), listType);
                    ArrayMovieDetails.setReviewDetailsArrayList(list);


                }
            }, new APIListner.Error() {
                @Override
                public void onErrorResponse(APIErrorException error) {
                    Log.i(TAG, error.toString());
                }
            });
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
