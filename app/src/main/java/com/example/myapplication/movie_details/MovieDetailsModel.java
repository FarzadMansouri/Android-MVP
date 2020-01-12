package com.example.myapplication.movie_details;

import android.util.Log;

import com.example.myapplication.model.Credits;
import com.example.myapplication.model.ResCat;
import com.example.myapplication.remote.ApiRequest;
import com.example.myapplication.remote.RetroClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsModel implements ContractMovieDetails.Remote {

    private ApiRequest apiRequest;

    @Override
    public void getListDetails(final OnFinishListener onFinishListener, int movieId, String credits) {
        apiRequest = RetroClass.getmRetrofit(RetroClass.BaseUrl).create(ApiRequest.class);

        Call<ResCat> movieDetailsCall = apiRequest.getMovieDetails(movieId, RetroClass.Api_key, credits);

        movieDetailsCall.enqueue(new Callback<ResCat>() {
            @Override
            public void onResponse(Call<ResCat> call, Response<ResCat> response) {

                Log.e(RetroClass.TAG, "onResponse movie Dettails message: " + response.message());
                Log.e(RetroClass.TAG, "onResponse movie Dettails request: " + response.raw().request().url());

                Log.e(RetroClass.TAG, "onResponse movie Dettails Size: " + response.body().getCredits().getCast().size());
                onFinishListener.onSuccess(response.body().getCredits().getCast());

            }


            @Override
            public void onFailure(Call<ResCat> call, Throwable t) {

                Log.e(RetroClass.TAG, "onFailure Movie Details : " + t.getMessage());
                onFinishListener.onFailure(t);

            }
        });

    }
}
