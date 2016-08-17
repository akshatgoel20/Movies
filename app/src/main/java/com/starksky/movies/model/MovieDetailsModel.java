package com.starksky.movies.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshat on 24/07/16.
 */
public class MovieDetailsModel {
    @SerializedName("poster_path")
    private String poster_path;

    @SerializedName("overview")
    private String synopsis = "overview";

    @SerializedName("title")
    private String title = "title";

    @SerializedName("release_date")
    private String release_date = "release_date";

    @SerializedName("vote_average")
    private String user_rating = "vote_average";

    @SerializedName("id")
    private String movie_id = "id";



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

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }
}
