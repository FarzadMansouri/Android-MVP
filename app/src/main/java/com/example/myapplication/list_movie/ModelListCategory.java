package com.example.myapplication.list_movie;

import android.util.Log;

import com.example.myapplication.model.ListItemMovies;
import com.example.myapplication.model.ResMovies;
import com.example.myapplication.remote.ApiRequest;
import com.example.myapplication.remote.RetroClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelListCategory implements ContractPosterMovie.Remote {

    private ApiRequest apiRequest;

    @Override
    public void getResult(final FinishListener finishListener) {
        apiRequest = RetroClass.getmRetrofit(RetroClass.BaseUrl).create(ApiRequest.class);

        Call<ResMovies> call = apiRequest.getcat(RetroClass.Api_key);

        call.enqueue(new Callback<ResMovies>() {
            @Override
            public void onResponse(Call<ResMovies> call, Response<ResMovies> response) {


                Log.e(RetroClass.TAG, "onResponse list category : " + response.message());
                Log.e(RetroClass.TAG, "onResponse list category request : " + response.raw().request().url());
                Log.e(RetroClass.TAG, "onResponse list category size : " + response.body().getmData().size());

                List<ListItemMovies> listCategories = response.body().getmData();

                finishListener.onSuccess(listCategories);


            }

            @Override
            public void onFailure(Call<ResMovies> call, Throwable t) {

                finishListener.onFailure(t);
                Log.e(RetroClass.TAG, "onFailure list category : " + t.getMessage());
            }
        });

    }
}
