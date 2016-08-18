package com.starksky.movies.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshat on 18/08/16.
 */
public class ReviewDetails {
    @SerializedName("author")
    private String author;

    @SerializedName("content")
    private String content = "content";

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
