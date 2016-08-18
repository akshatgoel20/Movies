package com.starksky.movies.model;

import java.util.ArrayList;

/**
 * Created by akshat on 24/07/16.
 */
public class ArrayMovieDetails {

    static ArrayList<MovieDetailsModel> arrayList = new ArrayList<>();
    static ArrayList<TrailerDetails> trailerDetailsArrayList = new ArrayList<>();
    static ArrayList<ReviewDetails> reviewDetailsArrayList = new ArrayList<>();

    public static ArrayList<ReviewDetails> getReviewDetailsArrayList() {
        return reviewDetailsArrayList;
    }

    public static void setReviewDetailsArrayList(ArrayList<ReviewDetails> reviewDetailsArrayList) {
        ArrayMovieDetails.reviewDetailsArrayList = reviewDetailsArrayList;
    }

    public static ArrayList<TrailerDetails> getTrailerDetailsArrayList() {

        return trailerDetailsArrayList;
    }

    public static void setTrailerDetailsArrayList(ArrayList<TrailerDetails> trailerDetailsArrayList) {
        ArrayMovieDetails.trailerDetailsArrayList = trailerDetailsArrayList;
    }

    public static ArrayList<MovieDetailsModel> getArrayList() {
        return arrayList;
    }

    public static void setArrayList(ArrayList<MovieDetailsModel> arrayList1) {
        arrayList = arrayList1;
    }
}
