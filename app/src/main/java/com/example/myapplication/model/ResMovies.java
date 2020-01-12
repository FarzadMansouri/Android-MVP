package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResMovies {

    @SerializedName("total_pages")
    private int mCode;

    @SerializedName("results")
    private List<ListItemMovies> mData;

    public int getmCode() {
        return mCode;
    }

    public void setmCode(int mCode) {
        this.mCode = mCode;
    }

    public List<ListItemMovies> getmData() {
        return mData;
    }

    public void setmData(List<ListItemMovies> mData) {
        this.mData = mData;
    }
}
