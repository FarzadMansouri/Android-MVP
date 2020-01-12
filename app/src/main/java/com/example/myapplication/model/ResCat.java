package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class ResCat {
    @SerializedName("credits")

    private Credits credits;

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }
}
