package com.example.generateprojection.helper;

import com.google.gson.annotations.SerializedName;

public class ZHiHuDetail_DB {


    @SerializedName("long_comments")
    private Integer longComments;
    @SerializedName("popularity")
    private Integer popularity;
    @SerializedName("short_comments")
    private Integer shortComments;
    @SerializedName("comments")
    private Integer comments;

    public Integer getLongComments() {
        return longComments;
    }

    public void setLongComments(Integer longComments) {
        this.longComments = longComments;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public Integer getShortComments() {
        return shortComments;
    }

    public void setShortComments(Integer shortComments) {
        this.shortComments = shortComments;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }
}
