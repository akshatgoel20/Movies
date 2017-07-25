package com.starksky.movies.network;

import com.android.volley.VolleyError;

import org.json.JSONObject;


public class APIErrorException extends Exception {
    public int statusCode;

    public String code;

    public String message;

    public String description;

    public APIErrorException() {
        super();
    }

    public APIErrorException(int statusCode, String code, String message, String description) {
        super();
        this.statusCode = statusCode;
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public APIErrorException(VolleyError error) {
        super();

        try {
            if (error != null && error.networkResponse != null) {
                this.statusCode = error.networkResponse.statusCode;
                String str = new String(error.networkResponse.data, "UTF-8");

                JSONObject errorJson = new JSONObject(str);
                if (errorJson != null && errorJson.optJSONObject("error") != null) {
                    // Default JSON error :
                    //       {"error":{"code":40,"message":"Missing credentials","description":"The
                    //          requested service needs credentials, but none were provided."}}

                    this.code = errorJson.optString("error");
                    this.message = errorJson.optString("error");
                    this.description = errorJson.optString("error_description");

                    errorJson = errorJson.optJSONObject("error");

                    this.code = errorJson.optString("code");
                    this.message = errorJson.optString("message");
                    this.description = errorJson.optString("description");
                }
            } else {
                this.message = "";
                this.description = "";
                this.code = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        String str = "statusCode= " + String.valueOf(this.statusCode)
                + "\ncode=" + String.valueOf(this.code)
                + "\nmessage=" + String.valueOf(this.message)
                + "\ndescription=" + String.valueOf(this.description);
        return str;
    }
}
