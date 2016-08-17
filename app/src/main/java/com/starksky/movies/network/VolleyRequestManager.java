package com.starksky.movies.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.starksky.movies.app.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class VolleyRequestManager {

    protected static final String TAG = VolleyRequestManager.class.getSimpleName();
    private static VolleyRequestManager singleInstance = new VolleyRequestManager();
    static Context mContext;

    protected VolleyRequestManager() {
    }

    public static VolleyRequestManager getInstance(Context context) {
        mContext = context;
        if (singleInstance == null) {
            singleInstance = new VolleyRequestManager();
        }
        return singleInstance;
    }

    public void getMovieData(URL url, final APIListner.Success<JSONObject> success, final APIListner.Error error) {
        String gurl = url.toString();
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, gurl, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", response.toString());
                success.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("errorresponse", error.toString());
                error.onErrorResponse(new APIErrorException(volleyError));
            }


        });
        Log.d("json", jsObjRequest.toString());
        MyApplication.getInstance().addToRequestQueue(jsObjRequest);
    }

    protected void errorHandler(VolleyError error) {
        NetworkResponse response = error.networkResponse;
        if (response != null && response.data != null) {
            String json = null;
            switch (response.statusCode) {
                case 500:
                case 400:
                    json = new String(response.data);
                    // Utils.generateNoteOnSD("ChangeOrder", json);
                    json = trimMessage(json, "message");
                    if (json != null)
                        Log.i(TAG, "Error: " + json);
                    if (json != null) {
                        String errorType = trimMessage(json, "error");
                        String errorDesc = trimMessage(json, "error_description");
                        Log.i(TAG, "Error: " + errorType + " Description: " + errorDesc);
                    }
                    break;
            }
        } else {
            VolleyLog.d(TAG, "In else ----- " + error.getMessage());
        }
    }

    protected String trimMessage(String json, String key) {
        String trimmedString = null;

        try {
            JSONObject obj = new JSONObject(json);
            trimmedString = obj.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return trimmedString;
    }
}
