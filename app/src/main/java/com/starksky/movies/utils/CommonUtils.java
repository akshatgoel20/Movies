package com.starksky.movies.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by akshat on 26/07/16.
 */
public class CommonUtils {

    public static boolean isTb;
    static Dialog progress;

    public static boolean isTb() {
        return isTb;
    }

    public static void setIsTb(boolean isTb) {
        CommonUtils.isTb = isTb;
    }

    public static void showDialog(Context context, String msg) {
        progress = new ProgressDialog(context);

        progress.setTitle(msg);

        progress.show();
    }

    static void stopDialog() {
        progress.dismiss();
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    public static void toast(Context context, String s) {
        Toast toast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
    }
}
