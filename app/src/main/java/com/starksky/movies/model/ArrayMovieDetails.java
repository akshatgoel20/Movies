package com.starksky.movies.model;

import java.util.ArrayList;

/**
 * Created by akshat on 24/07/16.
 */
public class ArrayMovieDetails {

    static ArrayList<MovieDetailsModel> arrayList = new ArrayList<>();


    public static ArrayList<MovieDetailsModel> getArrayList() {
        return arrayList;
    }

    public static void setArrayList(ArrayList<MovieDetailsModel> arrayList1) {
        arrayList = arrayList1;
    }
}
