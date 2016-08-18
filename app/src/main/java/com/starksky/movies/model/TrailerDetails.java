package com.starksky.movies.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akshat on 18/08/16.
 */
public class TrailerDetails {

    @SerializedName("name")
    private String name;

    @SerializedName("source")
    private String source = "source";

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
