package com.starksky.movies.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshat on 24/07/16.
 */
public class MovieDetailsModel {
    @SerializedName("poster_path")
    private String poster_path ;

    @SerializedName("overview")
    private String synopsis = "overview";

    @SerializedName("title")
    private String title = "title";

    @SerializedName("release_date")
    private String release_date = "release_date";

    @SerializedName("vote_average")
    private String user_rating = "vote_average" ;

    public MovieDetailsModel(String poster_path, String synopsis,String title, String release_date, String user_rating){
        this.poster_path=poster_path;
        this.synopsis=synopsis;
        this.title=title;
        this.release_date=release_date;
        this.user_rating=user_rating;
    }


    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(String user_rating) {
        this.user_rating = user_rating;
    }
}
