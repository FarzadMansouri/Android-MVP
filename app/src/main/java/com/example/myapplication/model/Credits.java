package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Credits {

    @SerializedName("cast")
    private List<CastAct> cast;

    public List<CastAct> getCast() {
        return cast;
    }

    public void setCast(List<CastAct> cast) {
        this.cast = cast;
    }
}